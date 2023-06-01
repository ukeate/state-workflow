package com.statecore;

import static org.junit.Assert.assertTrue;

import com.statecore.core.*;
import com.statecore.core.obj.*;
import com.statecore.core.obj.tune.*;
import com.statecore.core.obj.element.Task;
import com.statecore.core.obj.element.TransitionColorAnd;
import com.statecore.core.svc.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private Serviceability sa;

    @Before
    public void before() {
        this.sa = new Serviceability(
                new CommandExecutorImpl(new CommandConfig(), new CommandInvoker()),
                new EventQueue(new EventHandlerImpl())
        );
        App.init();
    }

    @Test
    public void executeGraph() {

        ModelBuilder mb = new ModelBuilder(sa);

        State n1 = mb.newState(1L, "1");
        State n2 = mb.newState(2L, "2");
        Task n3 = mb.newTask(3L);
        TransitionColorAnd t1 = mb.newTransitionColorAndImpl(1L);
        n3.includeResource(new Resource("r1"));

        mb.elementConnectTo(n1, t1);
        mb.elementConnectTo(t1, n2);
        mb.elementConnectTo(t1, n3);

        Graph g1 = mb.newGraph();
        mb.graphPutElement(g1, n1);
        Graph.print(g1);

        ProcessDefinition pd = new ProcessDefinition(g1);
        Service s = new Service(sa);
        BehaviorContext bc = new BehaviorContext();
        ProcessInstance instance = s.DeployProcessDefinition(bc, pd, new Variables());
        CommandContext cc = instance.createCommandContext(bc);
        for (Activity activity : instance.getActivitiesByResourceName("r1")) {
            activity.complete(cc);
        }
        Graph.print(g1);
    }
}
