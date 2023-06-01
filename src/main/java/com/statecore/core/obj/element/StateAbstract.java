package com.statecore.core.obj.element;

import com.statecore.core.obj.element.NodeAbstract;
import com.statecore.core.svc.Serviceability;

public class StateAbstract extends NodeAbstract {

    public StateAbstract(){}
    public StateAbstract(Long id, Serviceability sa) {
        super(id, sa);
    }
}
