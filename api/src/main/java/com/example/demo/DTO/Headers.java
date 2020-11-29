package com.example.demo.DTO;

public class Headers {
    private String AppKey;
    private String Channel;
    private String TellerName;
    private String CallerId;
    private String ChannelSessionId;
    private String ChannelRequestId;

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    public String getTellerName() {
        return TellerName;
    }

    public void setTellerName(String tellerName) {
        TellerName = tellerName;
    }

    public String getCallerId() {
        return CallerId;
    }

    public void setCallerId(String callerId) {
        CallerId = callerId;
    }

    public String getChannelSessionId() {
        return ChannelSessionId;
    }

    public void setChannelSessionId(String channelSessionId) {
        ChannelSessionId = channelSessionId;
    }

    public String getChannelRequestId() {
        return ChannelRequestId;
    }

    public void setChannelRequestId(String channelRequestId) {
        ChannelRequestId = channelRequestId;
    }

    @Override
    public String toString() {
        return "Headers{" +
                "AppKey='" + AppKey + '\'' +
                ", Channel='" + Channel + '\'' +
                ", TellerName='" + TellerName + '\'' +
                ", CallerId='" + CallerId + '\'' +
                ", ChannelSessionId='" + ChannelSessionId + '\'' +
                ", ChannelRequestId='" + ChannelRequestId + '\'' +
                '}';
    }
}
