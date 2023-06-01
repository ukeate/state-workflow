package com.statecore.core.obj;

import com.statecore.core.CommandContext;
import com.statecore.core.ElementFunction;
import com.statecore.core.obj.element.Element;
import com.statecore.core.obj.element.Node;
import com.statecore.core.obj.element.Transition;

import java.util.HashSet;

public class Graph {
    public Element source;

    public Graph(Element source) {
        this.source = source;
    }

    public void putElement(CommandContext cc, Element e) {
        source.appendToElement(cc, e);
    }

    public void bfs(ElementFunction fn) {
        source.bfs(new HashSet<>(), fn);
    }

    public static void print(Graph g) {
        g.bfs(es -> {
            for (Element e : es) {
                if (e instanceof Node) {
                    Node n = (Node) e;
                    System.out.println("n" + e.getId() + n.getTokens());
                } else if (e instanceof Transition) {
                    Transition t = (Transition) e;
                    System.out.println("t" + e.getId());
                }

            }
        });
    }
}
