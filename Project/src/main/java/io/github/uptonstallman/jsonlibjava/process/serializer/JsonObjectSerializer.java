package io.github.uptonstallman.jsonlibjava.process.serializer;

import io.github.uptonstallman.jsonlibjava.input.JsonOjectMapInput;
import io.github.uptonstallman.jsonlibjava.output.JsonObjectTextOutput;

import java.util.Map;

/**
 * The type Json object serializer.
 * Class to serialize (generate a json object literal representation) key:value map of json elements.
 */
public class JsonObjectSerializer {

    /**
     * Serialize json object text output.
     *
     * @param jsonOjectMapInput the json oject map input
     * @return the json object text output
     */
    public static JsonObjectTextOutput serialize(JsonOjectMapInput jsonOjectMapInput) {
        return serialize(false, jsonOjectMapInput);
    }

    /**
     * Serialize json object text output.
     *
     * @param flat              the flat
     * @param jsonOjectMapInput the json oject map input
     * @return the json object text output
     */
    public static JsonObjectTextOutput serialize(Boolean flat, JsonOjectMapInput jsonOjectMapInput) {
        StringBuilder out = new StringBuilder();

        String BR = "\n";
        String TAB = "\t";
        if (flat) {
            BR = "";
            TAB = "";
        }

        out.append("{").append(BR);

        int i = 1;
        Map<String, String> keysAndValues = jsonOjectMapInput.getKeysAndValues();
        for (final String k : keysAndValues.keySet()) {
            if (keysAndValues.get(k).trim().equals("null")) {
                out.append(TAB).append("\"").append(k).append("\" : null");

            } else if (keysAndValues.get(k).trim().startsWith("{") || keysAndValues.get(k).trim().startsWith("[")) {
                out.append(TAB).append("\"").append(k).append("\" : ").append(keysAndValues.get(k));

            } else {
                out.append(TAB).append("\"").append(k).append("\" : ").append(keysAndValues.get(k));

            }

            if (i < keysAndValues.size()) {
                out.append(", ");
            }
            out.append(BR);
            i++;
        }

        out.append("}");

        return new JsonObjectTextOutput(out.toString());
    }

}
