package com.statecore.core.obj.tune;

import com.statecore.core.svc.Serviceability;

public class TuneBuilder {
    private Serviceability sa;
    private TuneService s;

    public TuneBuilder(Serviceability sa) {
        this.sa = sa;
        this.s = new TuneService(sa);
    }

    public TuneService build() {
        return this.s;
    }

    public TuneBuilder addSpec(Spec spec) {
        s.specMap.put(spec.id, spec);
        return this;
    }

    public TuneBuilder specAddState(Spec spec, State state) {
        spec.stateMap.put(state.id, state);
        spec.stateNameMap.put(state.name, state);
        return this;
    }
}
