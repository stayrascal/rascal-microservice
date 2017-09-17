package com.stayrascal.cloud.admin.controller;

import com.stayrascal.cloud.admin.relation.FeignInfo;
import com.stayrascal.cloud.admin.relation.FeignSchedule;
import com.stayrascal.cloud.admin.relation.InterfaceInfo;
import com.stayrascal.cloud.admin.relation.RelateInstance;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Feigncontroller {

    @Autowired
    private FeignSchedule feignSchedule;

    @GetMapping(value = "/feigns")
    public String feigns() {
        Map<String, Map<String, RelateInstance>> dataMap = feignSchedule.getDataMap();
        Map<String, InterfaceInfo> interfaceInfoMap = new HashMap<>();

        for (Map.Entry<String, Map<String, RelateInstance>> entry : dataMap.entrySet()) {
            String serviceId = entry.getKey();
            Map<String, RelateInstance> relateInstanceMap = entry.getValue();

            for (Map.Entry<String, RelateInstance> instanceEntry : relateInstanceMap.entrySet()) {
                String instanceId = instanceEntry.getKey();
                RelateInstance relateInstance = instanceEntry.getValue();
                InstanceInfo instanceInfo = relateInstance.getInstanceInfo();
                List<FeignInfo> feignInfoList = relateInstance.getFeignInfoList();
                if (feignInfoList != null && feignInfoList.size() > 0) {
                    for (FeignInfo feignInfo : feignInfoList) {
                        String interfaceName = feignInfo.getInterfaceName();
                        if (interfaceName.contains(".")) {
                            interfaceName.substring(interfaceName.indexOf(".") + 1);
                        }
                        InterfaceInfo interfaceInfo = interfaceInfoMap.get(interfaceName);
                        if (interfaceInfo == null) {
                            interfaceInfo = new InterfaceInfo(interfaceName);
                            interfaceInfoMap.put(interfaceName, interfaceInfo);
                        }
                        interfaceInfo.getProviders().addAll(getInstances(feignInfo.getServiceId(), dataMap));
                        String consumer = instanceInfo.getAppName().toLowerCase() + ":" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
                        interfaceInfo.getConsumes().add(consumer);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<style>table{border-right:1px solid gray;border-bottom:1px solid gray;margin:0 auto;} table td{border-left:1px solid gray;border-top:1px solid gray;padding:5px;} </style><body style=\"text-align:center\"><table><tr><td>Consumers</td><td>Interface</td><td>Providers</td></tr>");
        for (InterfaceInfo interfaceInfo : interfaceInfoMap.values()) {
            sb.append("<tr>");
            sb.append("<td>");
            ArrayList<String> consumers = new ArrayList<>(interfaceInfo.getConsumes());
            Collections.sort(consumers);
            for (String str : consumers) {
                sb.append(str).append("<br/>");
            }
            sb.append("</td><td>");
            sb.append(interfaceInfo.getName());
            sb.append("</td><td>");
            List<String> providers = new ArrayList<>(interfaceInfo.getProviders());
            Collections.sort(providers);
            for (String str : providers) {
                sb.append(str).append("<br/>");
            }
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table></body>");
        return sb.toString();
    }

    private List<String> getInstances(String serviceId, Map<String, Map<String, RelateInstance>> dataMap) {
        List<String> instances = new ArrayList<>();

        Map<String, RelateInstance> relateInstanceMap = dataMap.get(serviceId);
        if (relateInstanceMap != null) {
            for (Map.Entry<String, RelateInstance> instanceEntry : relateInstanceMap.entrySet()) {
                RelateInstance relateInstance = instanceEntry.getValue();
                InstanceInfo instanceInfo = relateInstance.getInstanceInfo();

                String instance = instanceInfo.getAppName().toLowerCase() + ":" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
                instances.add(instance);
            }
        }
        return instances;
    }
}
