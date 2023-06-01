package com.statecore.core;

import com.statecore.core.obj.element.Element;
import com.statecore.core.obj.Token;

public class ProcessContext {
    public ProcessInstance pi;
    public Element element;
    public Token token;

    public ProcessContext(ProcessInstance pi) {
        this.pi = pi;
    }
}
