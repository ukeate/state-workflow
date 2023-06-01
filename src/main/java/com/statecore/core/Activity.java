package com.statecore.core;

import com.statecore.core.obj.Resource;
import com.statecore.core.obj.ResourceStatus;
import com.statecore.core.obj.TokenStatus;
import com.statecore.core.obj.WorkItem;

public class Activity {
    private WorkItem workItem;
    private Resource res;

    public Activity(WorkItem workItem, Resource res) {
        this.workItem = workItem;
        this.res = res;
    }

    public void active() {
        this.res.status = ResourceStatus.Active;
    }

    public void complete(CommandContext cc) {
        if (ResourceStatus.Active.equals(this.res.status)) {
            this.res.status = ResourceStatus.Completed;
            this.workItem.token.status = TokenStatus.Active;
            this.workItem.token.signal(cc);
        }
    }
}
