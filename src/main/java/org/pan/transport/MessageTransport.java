package org.pan.transport;

/**
 * ��Ϣͨ�Žӿ�
 * Created by xiaopan on 2015-12-31.
 */
public interface MessageTransport {

    void sendMessageNoReturn(byte[] bytes) throws MessageTransportException;

    byte[] sendMessage(byte[] bytes, int returnSize, int timeOut) throws MessageTransportException;
}
