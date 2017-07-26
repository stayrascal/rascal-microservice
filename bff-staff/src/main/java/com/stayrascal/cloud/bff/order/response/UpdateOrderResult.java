package com.stayrascal.cloud.bff.order.response;

import com.google.common.collect.Lists;

import java.util.List;

public class UpdateOrderResult {
    private List<String> successOrderIds;
    private List<UpdateError> updateErrors;

    public UpdateOrderResult() {
        successOrderIds = Lists.newArrayList();
        updateErrors = Lists.newArrayList();
    }

    public List<String> getSuccessOrderIds() {
        return successOrderIds;
    }

    public void setSuccessOrderIds(List<String> successOrderIds) {
        this.successOrderIds = successOrderIds;
    }

    public List<UpdateError> getUpdateErrors() {
        return updateErrors;
    }

    public void setUpdateErrors(List<UpdateError> updateErrors) {
        this.updateErrors = updateErrors;
    }
}
