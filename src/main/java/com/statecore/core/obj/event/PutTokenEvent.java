package com.statecore.core.obj.event;

import com.statecore.core.EventHandler;
import com.statecore.core.obj.Token;
import com.statecore.core.obj.element.Node;
import com.statecore.core.obj.event.Event;

public class PutTokenEvent implements Event {
    public Node node;
    public Token token;

    public PutTokenEvent(Node node, Token token) {
        this.node = node;
        this.token = token;
    }

    @Override
    public void handle(EventHandler handler) {
        handler.putToken(this.node, this.token);
    }
}
