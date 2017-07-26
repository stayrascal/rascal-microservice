package com.stayrascal.cloud.bff.order.response;

public class UpdateError {
    private String id;
    private String errorCode;

    public UpdateError(String id, String errorCode) {
        this.id = id;
        this.errorCode = errorCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
