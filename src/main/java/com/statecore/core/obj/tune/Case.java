package com.statecore.core.obj.tune;

import com.dslplatform.json.*;
import com.statecore.util.JsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CompiledJson(onUnknown = CompiledJson.Behavior.IGNORE)
public class Case {

    public Long id;
    public Long specId;
    public Map<Long, CaseState> stateMap = new HashMap<>();

    public Case() {
    }

    public Case(Long id, Long specId) {
        this.id = id;
        this.specId = specId;
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


    private String toJson() throws IOException {
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
