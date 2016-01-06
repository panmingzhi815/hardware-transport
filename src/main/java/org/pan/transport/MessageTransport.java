package org.pan.transport;

import java.io.IOException;

/**
 * ��Ϣͨ�Žӿ�
 * Created by xiaopan on 2015-12-31.
 */
public interface MessageTransport extends AutoCloseable {

    boolean isReady();

    void sendMessageNoReturn(byte[] bytes) throws MessageTransportException;

    byte[] sendMessage(byte[] bytes, int returnSize, int timeOut) throws MessageTransportException;

    byte[] sendMessage(byte[] bytes, int returnSize, int timeOut,int sleepTime) throws MessageTransportException;
}
