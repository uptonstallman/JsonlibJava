package io.github.uptonstallman.jsonlibjava.input;

import io.github.uptonstallman.jsonlibjava.process.parser.JsonArrayParser;

/**
 * The type Json array text input.
 * Wrapper class for a json array literal.
 * To be used as argument for {@link JsonArrayParser}. It is passed as constructor parameter.
 * @see JsonArrayParser#parse()
 */
public class JsonArrayTextInput {

    private String jsonText;

    /**
     * Instantiates a new Json array text input.
     *
     * @param jsonText the json text
     */
    public JsonArrayTextInput(String jsonText) {
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
