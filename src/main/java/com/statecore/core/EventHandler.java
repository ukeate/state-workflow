package com.statecore.core;

import com.statecore.core.obj.element.Node;
import com.statecore.core.obj.Token;

public interface EventHandler {
    void putToken(Node n, Token t);
    void takeToken(Node n, Token t);
}
