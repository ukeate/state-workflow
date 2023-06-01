package com.statecore.core.obj.element;

import com.statecore.core.obj.element.ElementAbstract;
import com.statecore.core.svc.Serviceability;

public class GraphElement extends ElementAbstract {
    private Serviceability sa;

    public GraphElement(Serviceability sa) {
        super(null, sa);
    }
}
