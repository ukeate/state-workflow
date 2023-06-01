package com.statecore.core.obj.element;

import com.statecore.core.obj.Resource;
import com.statecore.core.obj.WorkItem;
import com.statecore.core.obj.element.Node;

import java.util.List;

public interface Task extends Node {
    void includeResource(Resource res);

    Resource getResource();

    List<WorkItem> getWorkItems();
}
