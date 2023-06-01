package com.statecore.core.obj.element;

import com.statecore.core.CommandContext;
import com.statecore.core.ElementFunction;
import com.statecore.core.svc.Serviceability;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ElementAbstract implements Element {
    public Long id;
    private Serviceability sa;
    private List<Element> fromElements = new ArrayList<>();
    private List<Element> toElements = new ArrayList<>();

    public  ElementAbstract(){}
    public ElementAbstract(Long id, Serviceability sa) {
        this.id = id;
        this.sa = sa;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public List<Element> getFromElements() {
        return this.fromElements;
    }

    @Override
    public List<Element> getToElements() {
        return this.toElements;
    }


    public void appendToElement(CommandContext cc, Element e) {
        toElements.add(e);
    }

    @Override
    public void appendFromElement(CommandContext cc, Element e) {
        fromElements.add(e);
    }

    @Override
    public void bfs(Set<Element> paceSet, ElementFunction fn) {
        Set<Element> forwardElements = toElements.stream().filter(ele -> {
            if (!paceSet.contains(ele)) {
                paceSet.add(ele);
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toSet());

        if (!forwardElements.isEmpty()) {
            fn.run(forwardElements);
            for (Element ele : forwardElements) {
                ele.bfs(paceSet, fn);
            }
        }

    }

}
