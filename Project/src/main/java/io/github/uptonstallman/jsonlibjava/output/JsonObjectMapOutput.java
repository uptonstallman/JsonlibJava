package io.github.uptonstallman.jsonlibjava.output;

import io.github.uptonstallman.jsonlibjava.process.parser.JsonObjectParser;

import java.util.Map;

/**
 * The type Json object map output.
 * Wrapper class for a key:value map of json object elements.
 * Output of {@link JsonObjectParser}
 * @see JsonObjectParser#parse()
 */
public class JsonObjectMapOutput {

    private Map<String, String> keysAndValues;

    /**
     * Instantiates a new Json object map output.
     *
     * @param keysAndValues the keys and values
     */
    public JsonObjectMapOutput(Map<String, String> keysAndValues) {
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
