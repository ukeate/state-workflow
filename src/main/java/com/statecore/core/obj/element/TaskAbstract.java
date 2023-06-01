package com.statecore.core.obj.element;

import com.statecore.core.obj.Resource;
import com.statecore.core.obj.WorkItem;
import com.statecore.core.svc.Serviceability;

import java.util.ArrayList;
import java.util.List;

public class TaskAbstract extends NodeAbstract implements Task {
    private Resource res;
    private List<WorkItem> workItems;

    public TaskAbstract(Long id, Serviceability sa) {
        super(id, sa);
        this.res = new Resource("");
        this.workItems = new ArrayList<>();
    }

    @Override
    public void includeResource(Resource res) {
        this.res.resMap.put(res.name, res);
    }

    @Override
    public Resource getResource() {
        return this.res;
    }

    @Override
    public List<WorkItem> getWorkItems() {
        return this.workItems;
    }
}
