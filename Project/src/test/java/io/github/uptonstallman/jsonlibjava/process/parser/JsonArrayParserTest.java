package io.github.uptonstallman.jsonlibjava.process.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.uptonstallman.jsonlibjava.TestConfig;
import io.github.uptonstallman.jsonlibjava.input.JsonArrayListInput;
import io.github.uptonstallman.jsonlibjava.input.JsonArrayTextInput;
import io.github.uptonstallman.jsonlibjava.output.JsonArrayListOutput;
import io.github.uptonstallman.jsonlibjava.process.log.ConsoleLog;
import io.github.uptonstallman.jsonlibjava.process.serializer.JsonArraySerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonArrayParserTest extends TestConfig {

    ConsoleLog log = new ConsoleLog();

    // Parser to test
    JsonArrayParser jsonArrayParser;

    // Jackson to compare results
    JsonFactory jsonFactory = new JsonFactory();
    ObjectMapper jsonMapper = new ObjectMapper(jsonFactory);

    @Test
    public void givenValidJsonWhenParseThenReturnParseResult() throws Exception {

        for (final String fichero : jsonArrayTestFiles) {
            URL resource = getClass().getClassLoader().getResource(fichero);
            String j = new String(Files
                    .readAllBytes(Paths.get(Objects.requireNonNull(resource).toURI())));

            jsonArrayParser = new JsonArrayParser(new JsonArrayTextInput(j));

            // execute parser
            JsonArrayListOutput jsonArrayListOutput = jsonArrayParser.parse();

            //compare
            List<String> jacksonArrayItemsFromTestFile = getJacksonArrayItems(j);

            List<String> jacksonArrayItemsFromParseResult = getJacksonArrayItems(JsonArraySerializer
                    .serialize(
                            new JsonArrayListInput(jsonArrayListOutput.getJsonItems())
                    ).getJsonText()
            );

            int i = 0;
            for (final String s : jacksonArrayItemsFromTestFile) {
                Assertions.assertEquals(jacksonArrayItemsFromParseResult.get(i), s);
                i++;
            }

        }

    }

    private List<String> getJacksonArrayItems(String j) throws JsonProcessingException {

        JsonNode jsonRootNode = jsonMapper.readTree(j);
        List<String> items = new ArrayList<>();
        for (JsonNode element : jsonRootNode) {
            items.add(element.toString());
        }

        return items;

    }

}
