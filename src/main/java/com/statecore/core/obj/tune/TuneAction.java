package com.statecore.core.obj.tune;

import java.util.HashMap;
import java.util.Map;

public class TuneAction {
    public Long id;
    public Long stateId;
    public Action action;

    public TuneAction(Long id, Long stateId, Action action) {
        this.id = id;
        this.stateId = stateId;
        this.action = action;
    }
}
