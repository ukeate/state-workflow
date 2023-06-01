package com.statecore.core.obj.element;

import com.statecore.core.CommandContext;

public interface Transition extends Element {
    boolean isEnabled(CommandContext cc);

    void fire(CommandContext cc);

    static void fire(Runnable fnFrom, Runnable fnTo) {
        fnFrom.run();
        fnTo.run();
    }

    static void fireIfEnabled(CommandContext cc, Transition t) {
        if (t.isEnabled(cc)) {
            t.fire(cc);
        }
    }
}
