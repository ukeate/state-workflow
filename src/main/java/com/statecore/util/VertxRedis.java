package com.statecore.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferOutputStream;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.statecore.exception.RedisSetException;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.redis.client.*;

import java.util.Arrays;

public class VertxRedis {

    private static final int KEY_BUFFER_SIZE = 200;
    private static final int VALUE_BUFFER_SIZE = 3000;
    public static RedisAPI redis;

    public static void init(Vertx vertx) {
        RedisOptions opt = new RedisOptions()
                .setConnectionString("redis://localhost:6379")
                .setPassword("root")
                .setMaxPoolSize(128)
                .setMaxPoolWaiting(20480).setMaxWaitingHandlers(20480);
        Promise<Object> promise = Promise.promise();

        Redis client = Redis.createClient(vertx, opt);
        VertxRedis.redis = RedisAPI.api(client);
        client.connect().onSuccess(conn -> {
            conn.send(Request.cmd(Command.PING)).onSuccess(res -> {
                promise.complete();
            });
        });
        promise.future().result();
    }

    public static Future<Void> set(String key, Object value) {
        Promise<Void> promise = Promise.promise();
        redis.set(Arrays.asList(
                key,
                KryoUtil.writeObjectToString(value)
        )).onSuccess(res -> {
            promise.complete();
        }).onFailure(e -> {
            e.printStackTrace();
            promise.fail(e);
        });

        return promise.future();
    }

    public static <T> Future<T> get(String key, Class<T> type) {
        Promise<T> promise = Promise.promise();
        redis.get(key).onSuccess(res -> {
            T o = KryoUtil.readObjectFromString(res.toString(), type);
            promise.complete(o);
        }).onFailure(e -> {
            e.printStackTrace();
            promise.fail(e.getCause());
        });

        return promise.future();
    }

}
