package com.statecore;

import com.statecore.gateway.vertx.VertxServer;
import com.statecore.core.obj.tune.Spec;
import com.statecore.util.JsonUtil;
import com.statecore.util.KryoUtil;
import com.statecore.util.QlUtil;
import com.statecore.util.VertxRedis;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Hello world!
 */
public class App {

    public static void init() {
        Vertx serverVertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(64));
        Vertx redisVertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(64));

        VertxServer.init(serverVertx);
        VertxRedis.init(redisVertx);

        KryoUtil.init(128);
        JsonUtil.init(128);
        QlUtil.init(128);
    }

    public static void main(String[] args) {
        App.init();

    }
}
