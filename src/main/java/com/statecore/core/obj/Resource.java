package com.statecore.core.obj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Resource {
    public String name;
    public ResourceStatus status;
    public Map<String, Resource> resMap;

    public Resource(String name) {
        this.name = name;
        this.status = ResourceStatus.Inactive;
        this.resMap = new HashMap<>();
    }

    @Override
    protected Object clone() {
        HashSet<Resource> paceSet = new HashSet<>();
        paceSet.add(this);
        Resource res2 = new Resource(this.name);
        res2.status = this.status;
        for (Map.Entry<String, Resource> entry : this.resMap.entrySet()) {
            Resource res = entry.getValue();
            if (!paceSet.contains(res)) {
                paceSet.add(res);
                res2.resMap.put(entry.getKey(), (Resource) res.clone());
            }
        }
        return res2;
    }
}
