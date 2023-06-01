package com.statecore.core.svc;

import com.statecore.core.CommandExecutor;
import com.statecore.core.EventQueue;

public class Serviceability {
    private CommandExecutor executor;
    public EventQueue queue;

    public CommandExecutor getExecutor() {
        return this.executor;
    }

    public Serviceability(CommandExecutor executor, EventQueue queue) {
        this.executor = executor;
        this.queue = queue;
    }
}
