package com.statecore.core.obj.element;

import com.statecore.core.svc.Serviceability;

public class TaskImpl extends TaskAbstract implements Task {

    public TaskImpl(Long id, Serviceability sa) {
        super(id, sa);
    }
}
