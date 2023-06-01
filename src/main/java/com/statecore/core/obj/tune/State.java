package com.statecore.core.obj.tune;

import com.statecore.core.obj.element.StateAbstract;
import com.statecore.core.svc.Serviceability;

import java.util.HashMap;
import java.util.Map;

public class State extends StateAbstract {
    public String name;
    public Map<String, Object> defaultParams = new HashMap<>();
    public Map<Long, Tune> tuneMap = new HashMap<>();

    public State(){}
    public State(Long id, String name, Serviceability sa) {
        super(id, sa);
        this.name = name;
    }
}
