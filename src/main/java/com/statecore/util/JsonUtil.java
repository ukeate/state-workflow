package com.statecore.util;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import com.esotericsoftware.kryo.util.Pool;

public class JsonUtil {
    public static Pool<DslJson> pool;

    public static void init(int capacity) {
        pool = new Pool<DslJson>(true, false, capacity) {

            @Override
            protected DslJson create() {
                DslJson<Object> dslJson = new DslJson<>(Settings.basicSetup());
                return dslJson;
            }
        };
    }
}
