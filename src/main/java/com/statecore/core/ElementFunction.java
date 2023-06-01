package com.statecore.core;

import com.statecore.core.obj.element.Element;

import java.util.Set;

@FunctionalInterface
public interface ElementFunction {
    public abstract void run(Set<Element> es);
}
