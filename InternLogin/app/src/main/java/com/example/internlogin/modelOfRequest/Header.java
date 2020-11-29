package com.example.internlogin.modelOfRequest;


public class Header {
    private String AppKey;
    private String Channel;
    private String TellerName;
    private String CallerId;
    private String ChannelSessionId;
    private String ChannelRequestId;

    public Header() {
        AppKey = "c1c2a508fdf64c14a7b44edc9241c9cd";
        Channel = "API";
        ChannelSessionId = "331eb5f529c74df2b800926b5f34b874";
        ChannelRequestId = "5252012362481156055";
        TellerName = "string";
        CallerId = "string";
    }
// Getter Methods

    public String getAppKey() {
        return AppKey;
    }

    public String getChannel() {
        return Channel;
    }

    public String getTellerName() {
        return TellerName;
    }

    public String getCallerId() {
        return CallerId;
    }

    public String getChannelSessionId() {
        return ChannelSessionId;
    }

    public String getChannelRequestId() {
        return ChannelRequestId;
    }

    // Setter Methods

    public void setAppKey(String AppKey) {
        this.AppKey = AppKey;
    }

    public void setChannel(String Channel) {
        this.Channel = Channel;
    }

    public void setTellerName(String TellerName) {
        this.TellerName = TellerName;
    }

    public void setCallerId(String CallerId) {
        this.CallerId = CallerId;
    }

    public void setChannelSessionId(String ChannelSessionId) {
        this.ChannelSessionId = ChannelSessionId;
    }

    public void setChannelRequestId(String ChannelRequestId) {
        this.ChannelRequestId = ChannelRequestId;
    }
}