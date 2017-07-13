package com.stayrascal.cloud.order.contract.command;

public class WeChatJsPurchaseCommand {
    private String openId;
    private String ip;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
