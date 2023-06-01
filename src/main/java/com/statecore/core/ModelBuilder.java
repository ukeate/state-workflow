package com.statecore.core;

import com.statecore.core.obj.*;
import com.statecore.core.obj.element.*;
import com.statecore.core.obj.tune.State;
import com.statecore.core.svc.Serviceability;

public class ModelBuilder {
    private CommandContext cc;
    private Serviceability sa;

    public ModelBuilder(Serviceability sa) {
        this.sa = sa;
    }

    public Graph newGraph() {
        return new Graph(new GraphElement(this.sa));
    }

    public State newState(Long id, String name) {
        return new State(id, name, this.sa);
    }

    public Task newTask(Long id) {
        return new TaskImpl(id, this.sa);
    }
    public void graphPutElement(Graph g, Element e) {
        g.source.appendToElement(this.cc, e);
    }

    public TransitionColorAnd newTransitionColorAndImpl(Long id) {
        return new TransitionColorAndImpl(id, this.sa);
    }

    public void elementConnectTo(Element e1, Element e2) {
        e1.appendToElement(cc, e2);
        e2.appendFromElement(cc, e1);
    }

}
