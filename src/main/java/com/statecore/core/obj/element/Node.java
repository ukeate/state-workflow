package com.statecore.core.obj.element;


import com.statecore.core.CommandContext;
import com.statecore.core.obj.Token;
import com.statecore.core.obj.element.Element;

import java.util.List;

public interface Node extends Element {
    public Token getToken(int id);
    public void putToken(CommandContext cc, Token t);

    public List<Token> getTokens();

    void signal(CommandContext cc);
}
