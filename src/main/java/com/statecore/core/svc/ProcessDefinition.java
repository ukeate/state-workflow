package com.statecore.core.svc;


import com.statecore.core.obj.Graph;

public class ProcessDefinition {
    private Graph graph;
    public Graph getGraph(){
        return this.graph;
    }

    public ProcessDefinition(Graph graph) {
        this.graph = graph;
    }
}
