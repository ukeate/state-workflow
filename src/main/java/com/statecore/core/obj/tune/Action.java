package com.statecore.core.obj.tune;

import com.dslplatform.json.CompiledJson;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@CompiledJson
public class Action {
    private Long id;
    private String name;

    public Action(){}
    public Action(@JsonProperty("id") Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Map<String, Object> execute(Map<String, Object> params) {
        // stateParams to action params

        // call action

        return new HashMap<>();
    }
}
