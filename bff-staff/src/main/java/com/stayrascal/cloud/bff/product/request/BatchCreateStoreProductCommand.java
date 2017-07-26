package com.stayrascal.cloud.bff.product.request;

import com.stayrascal.cloud.bff.common.BatchCommand;
import com.stayrascal.cloud.product.contract.command.CreateStoreProductCommand;

public class BatchCreateStoreProductCommand extends BatchCommand<CreateStoreProductCommand> {
    public BatchCreateStoreProductCommand() {
        super(CreateStoreProductCommand.class);
    }
}
