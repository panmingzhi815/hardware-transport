package org.pan.transport;

/**
 * Created by xiaopan on 2015-12-31.
 */
public abstract class AbstractMessageTransport {

    protected final String address;

    public AbstractMessageTransport(String address) {
        this.address = address;
    }

}
