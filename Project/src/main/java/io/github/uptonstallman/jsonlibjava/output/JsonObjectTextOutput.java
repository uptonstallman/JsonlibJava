package io.github.uptonstallman.jsonlibjava.output;

import io.github.uptonstallman.jsonlibjava.input.JsonOjectMapInput;
import io.github.uptonstallman.jsonlibjava.process.serializer.JsonObjectSerializer;

/**
 * The type Json object text output.
 * Wrapper class for serialized json.
 * Output of {@link JsonObjectSerializer}
 * @see JsonObjectSerializer#serialize(JsonOjectMapInput)
 */
public class JsonObjectTextOutput {

    private String jsonText;

    /**
     * Instantiates a new Json object text output.
     *
     * @param jsonText the json text
     */
    public JsonObjectTextOutput(String jsonText) {
        this.jsonText = jsonText;
    }

    /**
     * Gets json text.
     *
     * @return the json text
     */
    public String getJsonText() {
        return jsonText;
    }

    /**
     * Sets json text.
     *
     * @param jsonText the json text
     */
    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
}
