package com.statecore.core;

import com.statecore.core.svc.BehaviorContext;

public class CommandContext {
    public ProcessContext pc;
    public BehaviorContext bc;

    public CommandContext(ProcessContext pc, BehaviorContext bc) {
        this.pc = pc;
        this.bc = bc;
    }
}
