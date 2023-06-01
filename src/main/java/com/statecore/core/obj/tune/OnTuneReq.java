package com.statecore.core.obj.tune;

import java.util.HashMap;
import java.util.Map;

public class OnTuneReq {
    public Long caseId;
    public Long stateId;
    public Long tuneId;

    public Map<Long, Map<String, Object>> stateParamsMap = new HashMap<>();
}
