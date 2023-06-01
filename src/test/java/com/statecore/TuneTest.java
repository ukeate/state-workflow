package com.statecore;

import com.statecore.core.*;
import com.statecore.core.obj.Graph;
import com.statecore.core.obj.Resource;
import com.statecore.core.obj.element.Task;
import com.statecore.core.obj.element.TransitionColorAnd;
import com.statecore.core.obj.tune.*;
import com.statecore.core.svc.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class TuneTest {
    private Serviceability sa;

    @Before
    public void before() {
        this.sa = new Serviceability(
                new CommandExecutorImpl(new CommandConfig(), new CommandInvoker()),
                new EventQueue(new EventHandlerImpl())
        );
        App.init();
    }

}
