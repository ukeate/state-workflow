package com.statecore.core.obj.element;

import com.statecore.core.CommandContext;
import com.statecore.core.ElementFunction;

import java.util.List;
import java.util.Set;

public interface Element {
    Long getId();

    List<Element> getFromElements();

    List<Element> getToElements();

    void bfs(Set<Element> paceSet, ElementFunction fn);

    void appendToElement(CommandContext cc, Element e);

    void appendFromElement(CommandContext cc, Element e);
}
