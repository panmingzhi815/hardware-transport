package org.pan.transport.impl;

import org.pan.transport.AbstractMessageTransport;
import org.pan.transport.MessageTransport;
import org.pan.transport.MessageTransportException;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * TCP м╗пе╤к©з
 * Created by xiaopan on 2015-12-31.
 */
public class MessageTransportTCP extends AbstractMessageTransport implements MessageTransport {

    private Socket socket;

    public MessageTransportTCP(String address) {
        super(address);
    }

    public boolean isOpened(){
        return this.socket != null && this.socket.isConnected();
    }

    public void open() throws IOException {
        if (isOpened()){
            return;
        }
        if (this.socket == null){
            this.socket = new Socket();
        }
        String[] addressSplit = address.split(":");
        String ip = addressSplit[0];
        int port = Integer.valueOf(addressSplit[1]);

        final InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);
        this.socket.connect(inetSocketAddress);
    }

    @Override
    public void sendMessageNoReturn(byte[] bytes) throws MessageTransportException {
        try {
            open();
            this.socket.getOutputStream().write(bytes);
        } catch (IOException e) {
            throw new MessageTransportException("sendMessageNoReturn invoke IOException");
        }

    }

    @Override
    public byte[] sendMessage(byte[] bytes, int returnSize, int timeOut) throws MessageTransportException {
        long start = System.currentTimeMillis();
        try {
            open();
            this.socket.getOutputStream().write(bytes);
            final InputStream inputStream = this.socket.getInputStream();
            while (inputStream.available() < returnSize){
                if (System.currentTimeMillis() - start > timeOut){
                    throw new MessageTransportException("sendMessage timeout");
                }
                TimeUnit.MILLISECONDS.sleep(10);
            }
            byte[] returnBytes = new byte[returnSize];
            inputStream.read(returnBytes);
            return returnBytes;
        } catch (IOException e) {
            throw new MessageTransportException("sendMessage invoke IOException");
        } catch (InterruptedException e) {
            throw new MessageTransportException("sendMessage invoke InterruptedException");
        }
    }

    @Override
    public byte[] sendMessage(byte[] bytes, int returnSize, int timeOut, int sleepTime) throws MessageTransportException {
        byte[] result = sendMessage(bytes, returnSize, timeOut);
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if (this.socket != null && !this.socket.isClosed()){
            this.socket.close();
        }
    }

    public boolean isReady() {
        try {
            open();
            close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
