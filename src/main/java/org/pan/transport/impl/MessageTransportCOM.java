package org.pan.transport.impl;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import org.pan.transport.AbstractMessageTransport;
import org.pan.transport.MessageTransport;
import org.pan.transport.MessageTransportException;

/**
 * COM м╗пе╤к©з
 * Created by xiaopan on 2015-12-31.
 */
public class MessageTransportCOM extends AbstractMessageTransport implements MessageTransport {

    private SerialPort serialPort;

    public MessageTransportCOM(String address) {
        super(address);
    }

    public boolean isOpened(){
        return this.serialPort != null && this.serialPort.isOpened();
    }

    public void open() throws SerialPortException {
        if (isOpened()){
            return;
        }
        if (this.serialPort == null) {
            this.serialPort = new SerialPort(address);
        }
        this.serialPort.openPort();
    }

    @Override
    public void sendMessageNoReturn(byte[] bytes) throws MessageTransportException {
        try {
            open();
            boolean writeSuccess = this.serialPort.writeBytes(bytes);
            if (!writeSuccess){
                throw new MessageTransportException("sendMessageNoReturn method write fail");
            }
        } catch (SerialPortException e) {
            throw new MessageTransportException("sendMessageNoReturn invoke SerialPortException",e);
        }
    }

    @Override
    public byte[] sendMessage(byte[] bytes, int returnSize,int timeOut) throws MessageTransportException {
        try {
            open();
            boolean writeSuccess = this.serialPort.writeBytes(bytes);
            if (!writeSuccess){
                throw new MessageTransportException("sendMessage method write fail");
            }
            return this.serialPort.readBytes(returnSize,timeOut);
        } catch (SerialPortException e) {
            throw new MessageTransportException("sendMessage invoke SerialPortException",e);
        } catch (SerialPortTimeoutException e) {
            throw new MessageTransportException("sendMessageNoReturn invoke SerialPortTimeoutException",e);
        }
    }
}
