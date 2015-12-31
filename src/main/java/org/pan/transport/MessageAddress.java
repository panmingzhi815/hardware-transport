package org.pan.transport;

/**
 * Created by xiaopan on 2015-12-31.
 */
public class MessageAddress {
    public enum MessageType{
        TCP,COM
    }

    private MessageType messageType;
    private String address;

    public void setTcpMessageAddress(String ip,String port){
        this.messageType = MessageType.TCP;
        this.address = ip + ":" + port;
    }

    public void setComMessageAddress(String com){
        this.messageType = MessageType.COM;
        this.address = com;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getAddress() {
        return address;
    }
}
