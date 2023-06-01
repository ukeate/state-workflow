package com.statecore.gateway.vertx;

import com.statecore.core.*;
import com.statecore.core.obj.tune.*;
import com.statecore.core.svc.Serviceability;
import com.statecore.exception.RedisSetException;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.impl.RoutingContextDecorator;
import io.vertx.ext.web.impl.RoutingContextImpl;
import io.vertx.ext.web.impl.RoutingContextImplBase;
import io.vertx.ext.web.impl.RoutingContextWrapper;

import java.util.HashMap;
import java.util.Map;

public class VertxServer {
    private static TuneService tunePrepare() {
        Serviceability sa = new Serviceability(
                new CommandExecutorImpl(new CommandConfig(), new CommandInvoker()),
                new EventQueue(new EventHandlerImpl())
        );
        TuneBuilder builder = new TuneBuilder(sa);

        // 初始化Spec
        Action action1 = new Action(1L, "1");

        TuneAction tuneAction1 = new TuneAction(1L, 1L, action1);

        Tune tune1 = new Tune(1L, 1L, "1");
        tune1.checkMap.put("1", "$Case.getParam(\"1\", \"a\") == 11");
        tune1.checkMap.put("2", "$Case.getParam(\"1\", \"b\") == 22");
        tune1.tuneActionMap.put(1L, tuneAction1);

        State state1 = new State(1L, "1", sa);
        state1.defaultParams.put("a", 1);
        state1.defaultParams.put("b", 2);
        state1.defaultParams.put("c", 3);
        state1.tuneMap.put(1L, tune1);

        Spec spec1 = new Spec(1L);
        TuneService s = builder.addSpec(spec1)
                .specAddState(spec1, state1)
                .build();

        return s;
    }

    private static TuneService tuneService;

    static {
        tuneService = tunePrepare();
    }

    private static Long fuzzyCaseId = 1L;

    public static void init(Vertx vertx) {


        Router router = Router.router(vertx);
        router.get("/blank").respond(ctx -> {
            return ctx.response().putHeader("content-type", "text/plain").end("ok");
        });

        router.get("/getSpecList").respond(ctx -> {
            String s = "";
            for (Spec spec : tuneService.getSpecList()) {
                s = spec.toString();
            }
            return ctx.response().putHeader("content-type", "text/plain").end("ok" + s);
        });

        router.get("/getCase").respond(ctx -> {
            ctx.response().putHeader("content-type", "text/plain");
            Promise<String> promise = Promise.promise();
            Future<Case> f = tuneService.getCase(1L);
            f.onSuccess(c -> {
                promise.complete("ok" + c.toString());
            }).onFailure(e -> {
                e.printStackTrace();
                promise.complete("fail");
            });
            return promise.future();
        }).failureHandler(ctx -> {
            ctx.failure().printStackTrace();
        });


        router.post("/addCase").respond(ctx -> {
            // build Case
            CaseState cState1 = new CaseState(1L, "1");
            cState1.params.put("a", 11);
            cState1.params.put("b", 22);

            // add case
            Case case1 = new Case(fuzzyCaseId++, 1L);
            case1.stateMap.put(1L, cState1);
            HttpServerResponse resp = ctx.response().putHeader("content-type", "text/plain");
            tuneService.addCase(case1);
            return resp.end("ok");
        });

        router.post("/forceSetCase").respond(ctx -> {
            Case c = new Case(1L, 1L);
            c.stateMap.put(1L, new CaseState(3L, "3"));
            HttpServerResponse resp = ctx.response().putHeader("content-type", "text/plain");
            tuneService.forceSetCase(c);
            return resp.end("ok");
        });

        router.post("/onTune").respond(ctx -> {
            ctx.response().putHeader("content-type", "text/plain");
            Promise<String> promise = Promise.promise();

            OnTuneReq req = new OnTuneReq();
            req.stateId = 1L;
            req.caseId = 1L;
            req.tuneId = 1L;
            Map<String, Object> state1Params = new HashMap<>();
            state1Params.put("c", 33);
            req.stateParamsMap.put(1L, state1Params);

            tuneService.onTune(req).onSuccess(res -> {
                promise.complete("ok");
            }).onFailure(e -> {
                System.out.println(e);
                promise.complete("fail");
            });

            return promise.future();
        });

        vertx.createHttpServer().requestHandler(router).listen(8080).onFailure(e -> {
            e.printStackTrace();
        });
    }
}
