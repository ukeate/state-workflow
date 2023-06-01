package com.statecore.core;

import com.statecore.core.obj.*;
import com.statecore.core.obj.element.Element;
import com.statecore.core.obj.element.Node;
import com.statecore.core.obj.element.Task;
import com.statecore.core.obj.element.Transition;
import com.statecore.core.svc.BehaviorContext;
import com.statecore.core.svc.Serviceability;
import com.statecore.core.svc.Variables;

import java.util.ArrayList;
import java.util.List;

public class ProcessInstance {
    private Graph graph;
    private Variables variables;
    private Serviceability sa;

    public Serviceability getSa() {
        return this.sa;
    }

    public ProcessInstance(Graph graph, Variables variables, Serviceability scServiceability) {
        this.graph = graph;
        this.variables = variables;
        this.sa = scServiceability;
    }

    public CommandContext createCommandContext(BehaviorContext bc) {
        return new CommandContext(new ProcessContext(this), bc);
    }

    public void start(BehaviorContext bc) {
        CommandContext cc = this.createCommandContext(bc);
        for (Element ele : this.graph.source.getToElements()) {
            if (ele instanceof Transition) {

            } else {
                Node n = (Node) ele;
                Token t = new Token(null);
                n.putToken(cc, t);
            }
        }

    }

    public List<Activity> getActivitiesByResourceName(String name) {
        List<Activity> list = new ArrayList<>();
        this.graph.bfs(es -> {
            for (Element e : es) {
                if (e instanceof Task) {
                    Task t = (Task) e;
                    for (WorkItem w : t.getWorkItems()) {
                        Activity activity = w.openActivity(name);
                        if (activity != null) {
                            list.add(activity);
                        }
                    }
                }
            }
        });
        return list;
    }
}
