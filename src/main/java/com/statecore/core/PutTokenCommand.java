package com.statecore.core;

import com.statecore.core.obj.element.NodeAbstract;
import com.statecore.core.obj.event.PutTokenEvent;
import com.statecore.core.obj.Token;

public class PutTokenCommand implements Command {
    @Override
    public Object execute(CommandContext cc) {
        if (cc.pc.element instanceof NodeAbstract) {
            NodeAbstract node = (NodeAbstract) cc.pc.element;
            Token token = cc.pc.token;
            token.node = node;
            node.appendToken(token);
            cc.pc.pi.getSa().queue.addEvent(new PutTokenEvent(node, token));
            token.signal(cc);
        }
        return null;
    }
}
