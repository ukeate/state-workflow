package com.statecore.core.obj.tune;

import com.dslplatform.json.*;
import com.dslplatform.json.runtime.Settings;
import com.statecore.util.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CompiledJson(onUnknown = CompiledJson.Behavior.IGNORE)
public class Spec {
    public Long id;
    public Map<Long, State> stateMap = new HashMap<>();
    @JsonAttribute(ignore = true)
    public Map<String, State> stateNameMap = new HashMap<>();

    public Spec() {
    }

    public Spec(Long id) {
        this.id = id;
    }

    @JsonConverter(target = Object.class)
    public static class ObjectRuntimeConverter {
        public static final JsonReader.ReadObject<Object> JSON_READER = r -> {
            if (r.wasNull()) return null;
            return ObjectConverter.deserializeObject(r);
        };
        public static final JsonWriter.WriteObject<Object> JSON_WRITER = (writer, value) -> {
            if (value != null) writer.serializeObject(value);
            else writer.writeNull();
        };
    }

    public String toJson() throws IOException {
        DslJson<Object> dslJson = JsonUtil.pool.obtain();
        try {
            JsonWriter writer = dslJson.newWriter();
            dslJson.serialize(writer, this);
            byte[] buffer = writer.getByteBuffer();
            return new String(buffer);
        } finally {
            JsonUtil.pool.free(dslJson);
        }
    }

    @Override
    public String toString() {
        try {
            return this.toJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
