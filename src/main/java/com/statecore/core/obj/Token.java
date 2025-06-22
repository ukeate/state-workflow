package com.statecore.core.obj;

import com.statecore.core.CommandContext;
import com.statecore.core.obj.element.Node;

import java.util.UUID;

public class Token {
    private String color;
    public TokenStatus status;
    public Node node;

    public Token(String color) {
        if (color != null && !color.isEmpty()) {
            this.color = color;
        } else {
            this.color = UUID.randomUUID().toString();
        }
        this.status = TokenStatus.Inactive;
    }

    public void signal(CommandContext cc){
        if (this.node != null){
            this.node.signal(cc);
        }
    }
}
