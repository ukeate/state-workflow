package com.statecore.core.obj.tune;

import com.statecore.core.svc.Serviceability;
import com.statecore.exception.RedisSetException;
import com.statecore.util.VertxRedis;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import jodd.util.StringUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TuneService {
    private Serviceability sa;

    public Map<Long, Spec> specMap = new HashMap<>();

    public TuneService(Serviceability sa) {
        this.sa = sa;
    }


    public Collection<Spec> getSpecList() {
        return this.specMap.values();
    }

    public Future<Void> addCase(Case c) {
        return VertxRedis.set("case_" + c.id, c);
    }

    public Future<Case> getCase(Long id) {
        return VertxRedis.get("case_" + id, Case.class);
    }

    public void setCase(Case originC, Case targetC) {
        // set to redis
    }

    public Future<Void> forceSetCase(Case c) {
        return addCase(c);
    }


    public Future onTune(OnTuneReq req) {
        Promise<Void> promise = Promise.promise();

        if (req.stateId == null
                || req.tuneId == null
                || req.caseId == null) {
            promise.complete();
            return promise.future();
        }

        // get case
        this.getCase(req.caseId).onSuccess(c -> {
            if (c == null || c.specId == null
                    || !this.specMap.containsKey(c.specId)) {
                promise.complete();
                return;
            }

            Spec spec = this.specMap.get(c.specId);
            if (!spec.stateMap.containsKey(req.stateId)) {
                promise.complete();
                return;
            }
            State state = spec.stateMap.get(req.stateId);

            if (!state.tuneMap.containsKey(req.tuneId)) {
                promise.complete();
                return;
            }
            Tune tune = state.tuneMap.get(req.tuneId);

            Tune.tuneExecute(this, spec, c, tune, req.stateParamsMap).onSuccess(o -> {
                promise.complete();
            }).onFailure(promise::fail);
        });

        return promise.future();
    }
}
