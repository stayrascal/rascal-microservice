package com.stayrascal.cloud.bff.order.request;

import com.stayrascal.cloud.bff.common.BatchCommand;

public class BatchUpdateOrderCommand extends BatchCommand<UpdateOrderWithOrderIdCommand> {
    public BatchUpdateOrderCommand() {
        super(UpdateOrderWithOrderIdCommand.class);
    }
}
