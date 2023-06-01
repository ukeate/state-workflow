package com.statecore.core.obj;

import com.statecore.core.Activity;
import com.statecore.core.obj.element.Task;

public class WorkItem {
    private Task task;
    public Token token;
    private Resource res;

    public Activity openActivity(String name) {
        Resource res = this.res.resMap.get(name);
        if (res == null || ResourceStatus.Inactive.equals(res.status)) {
            return null;
        }
        Activity activity = new Activity(this, res);
        activity.active();
        return activity;
    }
}
