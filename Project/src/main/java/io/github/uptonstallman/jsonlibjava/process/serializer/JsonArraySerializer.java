package io.github.uptonstallman.jsonlibjava.process.serializer;

import io.github.uptonstallman.jsonlibjava.input.JsonArrayListInput;
import io.github.uptonstallman.jsonlibjava.output.JsonArrayTextOutput;

import java.util.List;

/**
 * The type Json array serializer.
 * Class to serialize (generate a json array literal representation) a list of json array elements.
 */
public class JsonArraySerializer {

    /**
     * Serialize json array.
     *
     * @param jsonArrayListInput the json array list input
     * @return the json array text output
     */
    public static JsonArrayTextOutput serialize(JsonArrayListInput jsonArrayListInput) {
        StringBuilder out = new StringBuilder();

        List<String> jsonItems = jsonArrayListInput.getJsonItems();

        out.append("[");
        int i = 1;
        for (final String item : jsonItems) {
            out.append(item);
            if (i < jsonItems.size())
                out.append(", ");
            i++;
        }
        out.append("]");

        return new JsonArrayTextOutput(out.toString());
    }

}
