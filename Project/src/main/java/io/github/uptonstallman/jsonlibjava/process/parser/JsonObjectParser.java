package io.github.uptonstallman.jsonlibjava.process.parser;

import io.github.uptonstallman.jsonlibjava.input.JsonObjectTextInput;
import io.github.uptonstallman.jsonlibjava.output.JsonObjectMapOutput;
import io.github.uptonstallman.jsonlibjava.process.JsonParseException;
import io.github.uptonstallman.jsonlibjava.process.log.Level;
import io.github.uptonstallman.jsonlibjava.process.util.ChunksFormatter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Json object parser.
 * Class to parse a json object literal representation into a map of key:value json elements.
 */
public class JsonObjectParser extends Constants {

    private final JsonObjectTextInput jsonObjectTextInput;

    //Map<String, String> mapa = new Hashtable<>();
    private final Map<String, String> jsonKeysAndValues = new LinkedHashMap<>();

    private char[] json;
    private char[] copy;
    private char[] points;
    private int[] pointsPositions;

    /**
     * Instantiates a new Json object parser.
     *
     * @param jsonObjectTextInput the json object text input
     */
    public JsonObjectParser(JsonObjectTextInput jsonObjectTextInput) {
        this.jsonObjectTextInput = jsonObjectTextInput;
    }

    /**
     * Parse json object literal.
     *
     * @return the json object map output
     * @throws JsonParseException the json parse exception
     */
    public JsonObjectMapOutput parse() throws JsonParseException {
        long startTime = new Date().getTime();

        // initialization
        this.json = jsonObjectTextInput.getJsonText().toCharArray();
        copy = new char[this.json.length];
        for (int i = 0; i < this.json.length; i++) {
            copy[i] = this.json[i];
        }
        points = new char[this.json.length];
        pointsPositions = new int[this.json.length];

        clearBetweenQuotes(copy);

        log.debug("\n\n#1 " + new String(copy));

        clearBetweenBracesAndBrackets(copy);

        log.debug("#2 " + new String(copy));

        // find points
        int ii = 0;
        for (int i = 0; i < copy.length; i++) {
            int asciiCode = copy[i];
            if (
                    asciiCode == quotes              ||
                    asciiCode == backSlash           ||

                    asciiCode == leftBrace           ||
                    asciiCode == rightBrace          ||
                    asciiCode == leftBracket         ||
                    asciiCode == rightBracket        ||

                    asciiCode == colon               ||
                    asciiCode == comma               ||

                    asciiCode == minus               ||
                    asciiCode == zero ||
                    asciiCode == one ||
                    asciiCode == two ||
                    asciiCode == three ||
                    asciiCode == four ||
                    asciiCode == five ||
                    asciiCode == six ||
                    asciiCode == seven ||
                    asciiCode == eight ||
                    asciiCode == nine ||
                    asciiCode == point ||

                    asciiCode == t                   ||
                    asciiCode == r                   ||
                    asciiCode == u                   ||
                    asciiCode == e                   ||

                    asciiCode == f                   ||
                    asciiCode == a                   ||
                    asciiCode == l                   ||
                    asciiCode == s                   ||

                    asciiCode == n
            ) {
                points[ii] = (char) asciiCode;
                pointsPositions[ii] = i;
                ii++;
            }
        }

        log.debug("#3 " + new String(points));

        // trim
        if (points[0] != leftBrace)
            throwException(0);
        copy[pointsPositions[0]] = ' ';

        int pointsSize = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i] == 0)
                break;
            pointsSize++;
        }

        if (points[pointsSize - 1] != rightBrace)
            throwException(pointsSize - 1);
        copy[pointsPositions[pointsSize - 1]] = ' ';

        // no need for parsing {}
        // return empty json
        if (points[0] == leftBrace && points[1] == rightBrace) { // {}
            for (int i = 2; i < points.length; i++) {
                if (points[i] != 0) {
                    throwException(1);
                }
            }
            return new JsonObjectMapOutput(Collections.emptyMap());
        }

        int mode = 1;
        int start = 1;
        int counterKeys = 0;
        int counterValues = 0;
        String pointsS = new String(points);
        while (true) {
            if (mode == 1) {
                counterKeys++;
                log.debug(counterKeys + " Key ============================================");
                Matcher matcher = findKeyAndClear(start, pointsS);
                start = matcher.end();
                mode = 2;

            } else if (mode == 2) {
                counterValues++;
                log.debug(counterValues + " Value ===================");
                Matcher matcher = findValueAndClear(start, pointsS, pointsSize);
                start = matcher.end();
                mode = 1;
                if (start == (pointsSize - 1)) // end
                    break;
            }

        }

        log.debug("#4 " + new String(copy));

        // validation
        for (int i = 0; i < copy.length; i++) {
            if (
                copy[i] != SPACE &&
                copy[i] != TAB &&
                copy[i] != CR &&
                copy[i] != LF
            ) {
                throw new JsonParseException("Validation failed");
            }
        }

        if (keys.size() != values.size())
            throwException(-1);

        // add extracted key value pairs
        for (int i = 0; i < keys.size(); i++) {
            if (jsonObjectTextInput.getRenameKeys() != null && jsonObjectTextInput.getRenameKeys().get(keys.get(i)) != null) {
                log.debug("renaming key " + keys.get(i) + " to " + jsonObjectTextInput.getRenameKeys().get(keys.get(i)));
                jsonKeysAndValues.put(jsonObjectTextInput.getRenameKeys().get(keys.get(i)), values.get(i));

            } else {
                jsonKeysAndValues.put(keys.get(i), values.get(i));
            }
            log.debug("#5 k: " + keys.get(i) + ", v: " + values.get(i));
        }

        long endTime = new Date().getTime();

        log.debug("#6 " + (endTime - startTime) + "ms");

        return new JsonObjectMapOutput(jsonKeysAndValues);
    }

    /**
     * The Keys.
     */
    List<String> keys = new ArrayList<>();
    /**
     * The Values.
     */
    List<String> values = new ArrayList<>();

    private Matcher findKeyAndClear(int start, String literal) throws JsonParseException {
        String p = "(?!\"\"\")(\"\":)";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(literal);
        if (!matcher.find(start))
            throwException(start);
        if (log.getLevel().equals(Level.DEBUG))
            debugMatcher(matcher, false);
        if (matcher.start() != start) {
            throwException(start);
        }

        int matcherStart = matcher.start();
        int matcherEnd = matcher.end() - 1;
        int valueStart = pointsPositions[matcherStart];
        int valueEnd = pointsPositions[matcherEnd];

        char[] key = new char[valueEnd - valueStart +1];
        int ii = 0;
        for (int i = valueStart; i <= valueEnd; i++) {
            key[ii] = json[i];
            ii++;
        }
        keys.add(ChunksFormatter.key(new String(key), false));

        for (int i = valueStart; i <= valueEnd; i++) {
            copy[i] = ' ';
        }
        if (valueEnd == 0)
            copy[valueStart] = ' ';
        return matcher;
    }

    private Matcher findValueAndClear(int start, String literal, int pointsSize) throws JsonParseException {
        String patternQuotesS = "(?!\"\"\")(?<quotes>\"\",?)";
        String patternBracketsS = "(?<brackets>\\[],?)";
        String patternBracesS = "(?<braces>\\{},?)";
        String patternNumberS = "(?<number>-?\\d*(\\.{1})?\\d+,?)";

        String p = "(" + patternQuotesS + "|" + patternBracketsS + "|" + patternBracesS + "|" + patternNumberS + "" +
                "|(?<tr>true,?)|(?<fa>false,?)|(?<nu>null,?))";
        log.debug(p);

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(literal);
        if (!matcher.find(start))
            throwException(start);
        if (log.getLevel().equals(Level.DEBUG))
            debugMatcher(matcher, true);
        if (matcher.start() != start) {
            throwException(start);
        }

        int matcherStart = matcher.start();
        int matcherEnd = matcher.end() - 1;
        int valueStart = pointsPositions[matcherStart];
        int valueEnd = pointsPositions[matcherEnd];

        char[] value = new char[valueEnd - valueStart +1];
        int ii = 0;
        for (int i = valueStart; i <= valueEnd; i++) {
            value[ii] = json[i];
            ii++;
        }

        if (matcher.end() == (pointsSize - 1)) { // last value
            String lastPatternQuotesS = "(?!\"\"\")(?<lastQuotes>^\"\"$)";
            String lastPatternBracketsS = "(?<lastBrackets>^\\[]$)";
            String lastPatternBracesS = "(?<lastBraces>^\\{}$)";
            String lastPatternNumberS = "(?<lastNumber>^-?\\d*(\\.{1})?\\d+$)";
            String lastP = "(" + lastPatternQuotesS + "|" + lastPatternBracketsS + "|" + lastPatternBracesS + "|" + lastPatternNumberS + "" +
                    "|(?<lastTr>^true$)|(?<lastFa>^false$)|(?<lastNu>^null$))";
            Pattern lastPattern = Pattern.compile(lastP);
            Matcher lastMatcher = lastPattern.matcher(matcher.group());
            if (!lastMatcher.matches())
                throwException(matcher.end() - 1);

            if (lastMatcher.start("lastQuotes") == 0) {
                values.add(ChunksFormatter.quotes(new String(value), true));

            } else if (lastMatcher.start("lastBrackets") == 0) {
                values.add(ChunksFormatter.brackets(new String(value), true));

            } else if (lastMatcher.start("lastBraces") == 0) {
                values.add(ChunksFormatter.braces(new String(value), true));

            } else if (lastMatcher.start("lastNumber") == 0) {
                values.add(ChunksFormatter.number(new String(value), true));

            } else if (lastMatcher.start("lastTr") == 0) {
                values.add(ChunksFormatter.bool(new String(value), true));

            } else if (lastMatcher.start("lastFa") == 0) {
                values.add(ChunksFormatter.bool(new String(value), true));

            } else if (lastMatcher.start("lastNu") == 0) {
                values.add(ChunksFormatter.nulll(new String(value), true));

            } else {
                throwException(-1);
            }

            for (int i = valueStart; i <= valueEnd; i++) {
                copy[i] = ' ';
            }
            return matcher;
        }

        if (matcher.start("quotes") == start) {
            values.add(ChunksFormatter.quotes(new String(value), false));

        } else if (matcher.start("brackets") == start) {
            values.add(ChunksFormatter.brackets(new String(value), false));

        } else if (matcher.start("braces") == start) {
            values.add(ChunksFormatter.braces(new String(value), false));

        } else if (matcher.start("number") == start) {
            values.add(ChunksFormatter.number(new String(value), false));

        } else if (matcher.start("tr") == start) {
            values.add(ChunksFormatter.bool(new String(value), false));

        } else if (matcher.start("fa") == start) {
            values.add(ChunksFormatter.bool(new String(value), false));

        } else if (matcher.start("nu") == start) {
            values.add(ChunksFormatter.nulll(new String(value), false));

        } else {
            throwException(-1);
        }

        for (int i = valueStart; i <= valueEnd; i++) {
            copy[i] = ' ';
        }

        return matcher;

    }

    private void throwException(int where) throws JsonParseException {
        if (where < 0) {
            throw new JsonParseException("Format error near col [undefined]");

        } else {
            throw new JsonParseException("Format error near col [" + pointsPositions[where] +
                    "]  " + json[pointsPositions[where]]);
        }
    }

}
