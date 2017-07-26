package com.stayrascal.cloud.bff.product.request;

import com.stayrascal.cloud.bff.common.BatchCommand;

public class BatchUpdateStoreProductCommand extends BatchCommand<UpdateStoreProductWithIdCommand> {
    public BatchUpdateStoreProductCommand() {
        super(UpdateStoreProductWithIdCommand.class);
    }
}
