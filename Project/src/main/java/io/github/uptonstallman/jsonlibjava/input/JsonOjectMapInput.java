package io.github.uptonstallman.jsonlibjava.input;

import io.github.uptonstallman.jsonlibjava.process.serializer.JsonObjectSerializer;

import java.util.Map;

/**
 * The type Json oject map input.
 * Wrapper class for a key:value map of json object.
 * To be used as argument for {@link JsonObjectSerializer}
 * @see JsonObjectSerializer#serialize(JsonOjectMapInput)
 */
public class JsonOjectMapInput {

    private Map<String, String> keysAndValues;

    /**
     * Instantiates a new Json oject map input.
     *
     * @param keysAndValues the keys and values
     */
    public JsonOjectMapInput(Map<String, String> keysAndValues) {
        this.keysAndValues = keysAndValues;
    }

    /**
     * Gets keys and values.
     *
     * @return the keys and values
     */
    public Map<String, String> getKeysAndValues() {
        return keysAndValues;
    }

    /**
     * Sets keys and values.
     *
     * @param keysAndValues the keys and values
     */
    public void setKeysAndValues(Map<String, String> keysAndValues) {
        this.keysAndValues = keysAndValues;
    }
}

