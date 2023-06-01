package com.statecore.core;

import com.statecore.core.obj.event.Event;

import java.util.*;

public class EventQueue {
    private Queue<Event> queue = new LinkedList<>();
    private EventHandler handler;

    public EventQueue(EventHandler handler) {
        this.handler = handler;
    }

    public void notifyAllo() {
        Iterator<Event> it = this.queue.iterator();
        while (it.hasNext()) {
            Event ev = it.next();
            ev.handle(this.handler);
            this.queue.remove();
        }
    }

    public void addEvent(Event ev) {
        this.queue.offer(ev);
        this.notifyAllo();
    }
}
