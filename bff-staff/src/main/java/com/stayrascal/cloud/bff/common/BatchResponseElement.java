package com.stayrascal.cloud.bff.common;

public class BatchResponseElement {
    private boolean isFailed;
    private Object command;
    private Object response;

    public BatchResponseElement() {
    }

    public BatchResponseElement(boolean isFailed, Object command, Object response) {
        this.isFailed = isFailed;
        this.command = command;
        this.response = response;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed(boolean failed) {
        isFailed = failed;
    }

    public Object getCommand() {
        return command;
    }

    public void setCommand(Object command) {
        this.command = command;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
