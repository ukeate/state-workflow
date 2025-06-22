package com.statecore.core.obj.tune;


public class CaseEl {
    private Case c;
    private Spec spec;

    public CaseEl(Case c, Spec spec) {
        this.c = c;
        this.spec = spec;
    }

    public Object getParam(String stateName, String paramName) {
        if (stateName == null || stateName.isEmpty()
                || paramName == null || paramName.isEmpty()) {
            return null;
        }
        State state = this.spec.stateNameMap.get(stateName);
        if (state == null) {
            return null;
        }
        CaseState caseState = c.stateMap.get(state.id);
        if (caseState == null) {
            return null;
        }
        return caseState.params.get(paramName);
    }
}
