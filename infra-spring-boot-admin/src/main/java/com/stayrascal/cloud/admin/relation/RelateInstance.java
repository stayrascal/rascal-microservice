package com.stayrascal.cloud.admin.relation;

import com.netflix.appinfo.InstanceInfo;

import java.util.List;

public class RelateInstance {
    private InstanceInfo instanceInfo;
    private List<FeignInfo> feignInfoList;

    public InstanceInfo getInstanceInfo() {
        return instanceInfo;
    }

    public void setInstanceInfo(InstanceInfo instanceInfo) {
        this.instanceInfo = instanceInfo;
    }

    public List<FeignInfo> getFeignInfoList() {
        return feignInfoList;
    }

    public void setFeignInfoList(List<FeignInfo> feignInfoList) {
        this.feignInfoList = feignInfoList;
    }
}
