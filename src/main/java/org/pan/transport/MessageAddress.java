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

    private MessageAddress(MessageType messageType, String address) {
        this.messageType = messageType;
        this.address = address;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getAddress() {
        return address;
    }


    public static MessageAddress createTcpMessageAddress(String ip,String port){
        return new MessageAddress(MessageType.TCP,ip + ":" + port);
    }

    public static MessageAddress createComMessageAddress(String com){
        return new MessageAddress(MessageType.COM,com);
    }
}
