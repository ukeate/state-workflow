package com.statecore.core.obj.event;

import com.statecore.core.EventHandler;

public interface Event {
    public void handle(EventHandler handler);
}
