package com.statecore.core.obj.element;

import com.statecore.core.CommandContext;
import com.statecore.core.svc.Serviceability;

public abstract class TransitionAbstract extends ElementAbstract implements Transition {
    public TransitionAbstract(Long id, Serviceability sa) {
        super(id, sa);
    }

    @Override
    public boolean isEnabled(CommandContext cc) {
        return true;
    }

    @Override
    public void fire(CommandContext cc) {
        Transition.fire(() -> {
        }, () -> {
        });
    }

}
