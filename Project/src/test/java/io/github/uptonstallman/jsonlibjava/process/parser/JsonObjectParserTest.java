package io.github.uptonstallman.jsonlibjava.process.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.uptonstallman.jsonlibjava.TestConfig;
import io.github.uptonstallman.jsonlibjava.input.JsonObjectTextInput;
import io.github.uptonstallman.jsonlibjava.input.JsonOjectMapInput;
import io.github.uptonstallman.jsonlibjava.output.JsonObjectMapOutput;
import io.github.uptonstallman.jsonlibjava.process.log.ConsoleLog;
import io.github.uptonstallman.jsonlibjava.process.serializer.JsonObjectSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonObjectParserTest extends TestConfig {

    ConsoleLog log = new ConsoleLog();

    // Parser to test
    JsonObjectParser jsonObjectParser;

    // Jackson to compare results
    JsonFactory jsonFactory = new JsonFactory();
    ObjectMapper jsonMapper = new ObjectMapper(jsonFactory);

    @Test
    public void givenValidJsonWhenParseThenReturnParseResult() throws Exception {

        for (final String testFilePath : jsonObjectTestFiles) {

            URL resource = getClass().getClassLoader().getResource(testFilePath);
            String j = new String(Files
                    .readAllBytes(Paths.get(Objects.requireNonNull(resource).toURI())));

            jsonObjectParser = new JsonObjectParser(new JsonObjectTextInput(j));

            // execute parser
            JsonObjectMapOutput jsonObjectMapOutput = jsonObjectParser.parse();

            //compare
            List<String> jacksonKeysAndValuesFromTesFile = getJacksonKeysAndValues(j);

            List<String> jacksonKeysAndValuesFromParseResult = getJacksonKeysAndValues(JsonObjectSerializer
                    .serialize(
                            new JsonOjectMapInput(jsonObjectMapOutput.getKeysAndValues())
                    ).getJsonText()
            );

            int i = 0;
            for (final String s : jacksonKeysAndValuesFromTesFile) {
                Assertions.assertEquals(jacksonKeysAndValuesFromParseResult.get(i), s);
                i++;
            }

        }
    }

    private List<String> getJacksonKeysAndValues(String j) throws JsonProcessingException {

        JsonNode jsonRootNode = jsonMapper.readTree(j);
        Iterator<Map.Entry<String,JsonNode>> jsonIterator = jsonRootNode.properties().stream().iterator();
        List<String> keysAndValuesConcatenations = new ArrayList<>();
        while (jsonIterator.hasNext()) {
            Map.Entry<String,JsonNode> jsonField = jsonIterator.next();
            String key = jsonField.getKey();
            String value = jsonField.getValue().asText();
            keysAndValuesConcatenations.add(key + "__" + value);
        }

        return keysAndValuesConcatenations;

    }

}
