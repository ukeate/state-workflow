package com.statecore.core.obj.tune;

import com.dslplatform.json.CompiledJson;

import java.util.HashMap;
import java.util.Map;

public class CaseState {
    public Long stateId;
    public String name;
    public Map<String, Object> params = new HashMap<>();

    public CaseState(){}
    public CaseState(Long stateId, String name) {
        this.stateId = stateId;
        this.name = name;
    }
}
