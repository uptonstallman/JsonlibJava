package io.github.uptonstallman.jsonlibjava.input;

import io.github.uptonstallman.jsonlibjava.process.parser.JsonObjectParser;

import java.util.Map;

/**
 * The type Json object text input.
 * Wrapper class for a json object literal.
 * To be used as argument for {@link JsonObjectParser}. It is passed as constructor parameter.
 * Optionally a map of key:newkey can be passed for renaming json keys.
 */
public class JsonObjectTextInput {

    private String jsonText;
    private Map<String, String> renameKeys;

    /**
     * Instantiates a new Json object text input.
     *
     * @param jsonText the json text
     */
    public JsonObjectTextInput(String jsonText) {
        this(jsonText, null);
    }

    /**
     * Instantiates a new Json object text input.
     *
     * @param jsonText   the json text
     * @param renameKeys the rename keys
     */
    public JsonObjectTextInput(String jsonText, Map<String, String> renameKeys) {
        this.jsonText = jsonText;
        this.renameKeys = renameKeys;
    }

    /**
     * Gets rename keys.
     *
     * @return the rename keys
     */
    public Map<String, String> getRenameKeys() {
        return renameKeys;
    }

    /**
     * Sets rename keys.
     *
     * @param renameKeys the rename keys
     */
    public void setRenameKeys(Map<String, String> renameKeys) {
        this.renameKeys = renameKeys;
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
