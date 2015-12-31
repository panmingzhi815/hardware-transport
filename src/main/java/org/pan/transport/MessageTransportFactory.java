package org.pan.transport;

import org.pan.transport.impl.MessageTransportCOM;
import org.pan.transport.impl.MessageTransportTCP;

/**
 * 消息端口工厂
 * Created by xiaopan on 2015-12-31.
 */
public class MessageTransportFactory {

    public static MessageTransport createMessageTransport(MessageAddress messageAddress){
        final MessageAddress.MessageType messageType = messageAddress.getMessageType();
        final String address = messageAddress.getAddress();
        switch (messageType){
            case TCP:
                return new MessageTransportTCP(address);
            case COM:
                return new MessageTransportCOM(address);
            default:
                throw new RuntimeException("createMessageTransport RuntimeException !!!");
        }
    }
}
