package com.statecore.core;

import com.statecore.core.svc.BehaviorContext;
import com.statecore.core.svc.ProcessDefinition;
import com.statecore.core.svc.Serviceability;
import com.statecore.core.svc.Variables;

public class ServiceBuilder {
    private Serviceability ability;

    public ProcessInstance deployProcessDefinition(BehaviorContext bctx, ProcessDefinition def, Variables variables) {
        ProcessInstance ins = new ProcessInstance(def.getGraph(), variables, this.ability);
        ins.start(bctx);
        return ins;
    }
}
