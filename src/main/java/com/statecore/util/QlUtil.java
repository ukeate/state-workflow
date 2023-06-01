package com.statecore.util;

import com.esotericsoftware.kryo.util.Pool;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

import java.util.List;

public class QlUtil {
    public static Pool<ExpressRunner> pool;

    public static void init(int capacity) {
        pool = new Pool<ExpressRunner>(true, false, capacity) {

            @Override
            protected ExpressRunner create() {
                return new ExpressRunner();
            }
        };
    }

    public static Object execute(String expressString, IExpressContext<String, Object> context, List<String> errorList, boolean isCache, boolean isTrace) throws Exception {
        ExpressRunner runner = pool.obtain();
        try {
            return runner.execute(expressString, context, errorList, isCache, isTrace);
        } finally {
            pool.free(runner);
        }
    }

}
