package com.statecore.core.svc;

import com.statecore.core.ProcessInstance;

public class Service {
    private Serviceability sa;
    private BehaviorContext bc;
    private ProcessDefinition pd;

    private Variables variables;

    public Service(Serviceability sa) {
        this.sa = sa;
    }

    public ProcessInstance DeployProcessDefinition(BehaviorContext bc, ProcessDefinition pd, Variables variables) {
        ProcessInstance pi = new ProcessInstance(pd.getGraph(), variables, this.sa);
        pi.start(bc);
        return pi;
    }
}
