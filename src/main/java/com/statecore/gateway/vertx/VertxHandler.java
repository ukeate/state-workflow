package com.statecore.gateway.vertx;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

public class VertxHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest req) {
        req.response().putHeader("content-type", "text/plain")
                .end("ok");
    }
}
