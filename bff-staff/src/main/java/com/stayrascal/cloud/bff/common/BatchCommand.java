package com.stayrascal.cloud.bff.common;

import java.util.List;

public abstract class BatchCommand<T> {
    private final Class<T> protoType;

    public BatchCommand(Class<T> protoType) {
        this.protoType = protoType;
    }

    private List<T> commands;

    public List<T> getCommands() {
        return commands;
    }

    public void setCommands(List<T> commands) {
        this.commands = commands;
    }
}
