package io.github.uptonstallman.jsonlibjava.output;

import io.github.uptonstallman.jsonlibjava.input.JsonArrayListInput;
import io.github.uptonstallman.jsonlibjava.process.serializer.JsonArraySerializer;

/**
 * The type Json array text output.
 * Wrapper class for serialized json.
 * Output of {@link JsonArraySerializer}
 * @see JsonArraySerializer#serialize(JsonArrayListInput) ()
 */
public class JsonArrayTextOutput {

    private String jsonText;

    /**
     * Instantiates a new Json array text output.
     *
     * @param jsonText the json text
     */
    public JsonArrayTextOutput(String jsonText) {
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
