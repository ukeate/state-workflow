package com.statecore.util;

import com.statecore.core.obj.tune.Spec;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.config.Config;

public class RedissonUtil {
    private static Redisson r;

    public static void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setDatabase(1)
                .setPassword("root");
        r = (Redisson) Redisson.create(config);
    }

    public static void set(Spec o) {
        RBucket<Spec> bucket = r.getBucket("DeviceSpec");
        bucket.set(o);
    }

    public static Spec get() {
        RBucket<Spec> bucket = r.getBucket("DeviceSpec");
        return bucket.get();
    }
}
