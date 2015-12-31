package org.pan.transport;

/**
 * Created by xiaopan on 2015-12-31.
 */
public class MessageTransportException extends Exception {

    public MessageTransportException() {
        super();
    }

    public MessageTransportException(String message) {
        super(message);
    }

    public MessageTransportException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageTransportException(Throwable cause) {
        super(cause);
    }

    protected MessageTransportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
