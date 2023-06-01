package com.statecore.core.obj.tune;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.statecore.exception.RedisSetException;
import com.statecore.util.QlUtil;
import io.vertx.core.Future;
import io.vertx.core.Promise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Tune {

    public Long stateId;
    public Long id;
    public String name;
    public Map<String, String> checkMap = new HashMap<>();
    public Map<Long, TuneAction> tuneActionMap = new HashMap<>();

    public Tune() {
    }

    public Tune(Long stateId, Long id, String name) {
        this.stateId = stateId;
        this.id = id;
        this.name = name;
    }


    public static Future<Void> tuneExecute(TuneService s, Spec spec, Case c, Tune t, Map<Long, Map<String, Object>> stateParamsMap) {
        Promise<Void> promise = Promise.promise();
        // calculate params

        // check
        DefaultContext<String, Object> ctx = new DefaultContext<>();
        ctx.put("$Case", new CaseEl(c, spec));
        try {
            for (String express : t.checkMap.values()) {
                Object rst = QlUtil.execute(express, ctx, null, true, false);
                if (rst.equals(false)) {
                    promise.fail("check fail:" + express);
                    return promise.future();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            promise.fail(e);
            return promise.future();
        }

        // action
        for (TuneAction ta : t.tuneActionMap.values()) {
            // get action
            if (ta.action == null) {
                continue;
            }
            if (!spec.stateMap.containsKey(ta.stateId)) {
                continue;
            }
            CaseState state = c.stateMap.get(ta.stateId);

            // get params
            Map<String, Object> stateParams = new HashMap<>();
            stateParams.putAll(state.params);
            if (stateParamsMap.containsKey(ta.stateId)) {
                for (Map.Entry<String, Object> param : stateParamsMap.get(ta.stateId).entrySet()) {
                    stateParams.put(param.getKey(), param.getValue());
                }
            }

            // merge Case to set
            Map<String, Object> actionResult = ta.action.execute(stateParams);

            state.params = stateParams;
        }
        s.forceSetCase(c).onSuccess(o -> {
            promise.complete();
        }).onFailure(e -> {
            promise.fail(e);
        });
        return promise.future();
    }
}
