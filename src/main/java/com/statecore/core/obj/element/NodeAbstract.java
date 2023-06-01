package com.statecore.core.obj.element;

import com.statecore.core.CommandContext;
import com.statecore.core.PutTokenCommand;
import com.statecore.core.obj.Token;
import com.statecore.core.svc.Serviceability;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeAbstract extends ElementAbstract implements Node {
    private Element element;
    private List<Token> tokens = new ArrayList<>();

    public NodeAbstract() {}
    public NodeAbstract(Long id, Serviceability sa) {
        super(id, sa);
    }

    public void appendToken(Token t) {
        this.tokens.add(t);
    }

    @Override
    public Token getToken(int i) {
        if (i < 0 || i >= this.tokens.size()) {
            return null;
        }
        return this.tokens.get(i);
    }

    @Override
    public void putToken(CommandContext cc, Token t) {
        cc.pc.element = this;
        cc.pc.token = t;
        cc.pc.pi.getSa().getExecutor().execute(cc,new PutTokenCommand());
    }

    @Override
    public List<Token> getTokens() {
        return this.tokens;
    }

    @Override
    public void signal(CommandContext cc) {
        for (Element ele :this.getToElements()){
            if (ele instanceof Transition) {
                Transition trans = (Transition) ele;
            }
        }
    }
}
