package io.github.uptonstallman.jsonlibjava.process.parser;

import io.github.uptonstallman.jsonlibjava.input.JsonArrayTextInput;
import io.github.uptonstallman.jsonlibjava.output.JsonArrayListOutput;
import io.github.uptonstallman.jsonlibjava.process.JsonParseException;
import io.github.uptonstallman.jsonlibjava.process.util.ChunksFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Json array parser.
 * Class to parse a json array literal representation into a list of json array elements.
 */
public class JsonArrayParser extends Constants {

    /**
     * The Json array items.
     */
    List<String> jsonArrayItems = new ArrayList<>();

    /**
     * The Json array text input.
     */
    JsonArrayTextInput jsonArrayTextInput;

    private char[] json;
    private char[] copy;
    private char[] points;
    private int[] pointsPositions;

    /**
     * Instantiates a new Json array parser.
     *
     * @param jsonArrayTextInput the json array text input
     */
    public JsonArrayParser(JsonArrayTextInput jsonArrayTextInput) {
        this.jsonArrayTextInput = jsonArrayTextInput;
    }

    /**
     * Parse json array literal.
     *
     * @return the json array list output
     * @throws JsonParseException the json parse exception
     */
    public JsonArrayListOutput parse() throws JsonParseException {
        long startTime = new Date().getTime();

        // initialization
        this.json = jsonArrayTextInput.getJsonText().toCharArray();
        copy = new char[this.json.length];
        for (int i = 0; i < this.json.length; i++) {
            copy[i] = this.json[i];
        }
        points = new char[this.json.length];
        pointsPositions = new int[this.json.length];

        clearBetweenQuotes(copy);

        log.debug("#1 " + new String(copy));

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

        //trim
        if (points[0] != leftBracket)
            throwException(0);
        copy[pointsPositions[0]] = ' ';

        int pointsSize = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i] == 0)
                break;
            pointsSize++;
        }

        if (points[pointsSize - 1] != rightBracket)
            throwException(pointsSize - 1);
        copy[pointsPositions[pointsSize - 1]] = ' ';

        // no need for parsing []
        // return empty json array
        if (points[0] == leftBracket && points[1] == rightBracket) { // []
            for (int i = 2; i < points.length; i++) {
                if (points[i] != 0) {
                    throwException(1);
                }
            }
            return new JsonArrayListOutput(Collections.emptyList());
        }

        int start = 1;
        int counterValues = 0;
        String pointsS = new String(points);
        while (true) {
            counterValues++;
            log.debug(counterValues + " Value ===================");
            Matcher matcher = findValueAndClear(start, pointsS, pointsSize);
            start = matcher.end();
            if (start == (pointsSize - 1)) // end
                break;

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

        long endTime = new Date().getTime();

        log.debug("#5 " + (endTime - startTime) + "ms");

        return new JsonArrayListOutput(jsonArrayItems);

    }

    private Matcher findValueAndClear(int start, String literal, int pointsSize) throws JsonParseException {

        String patternQuotesS = "(?!\"\"\")(?<quotes>\"\",?)";
        String patternBracketsS = "(?<brackets>\\[],?)";
        String patternBracesS = "(?<braces>\\{},?)";
        String patternNumbersS = "(?<number>-?\\d*(\\.{1})?\\d+,?)";

        String p = "(" + patternQuotesS + "|" + patternBracketsS + "|" + patternBracesS + "|" + patternNumbersS + "" +
                "|(?<tr>true,?)|(?<fa>false,?)|(?<nu>null,?))";
        log.debug(p);

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(literal);
        if (!matcher.find(start))
            throwException(start);
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
                jsonArrayItems.add(ChunksFormatter.quotes(new String(value), true));

            } else if (lastMatcher.start("lastBrackets") == 0) {
                jsonArrayItems.add(ChunksFormatter.brackets(new String(value), true));

            } else if (lastMatcher.start("lastBraces") == 0) {
                jsonArrayItems.add(ChunksFormatter.braces(new String(value), true));

            } else if (lastMatcher.start("lastNumber") == 0) {
                jsonArrayItems.add(ChunksFormatter.number(new String(value), true));

            } else if (lastMatcher.start("lastTr") == 0) {
                jsonArrayItems.add(ChunksFormatter.bool(new String(value), true));

            } else if (lastMatcher.start("lastFa") == 0) {
                jsonArrayItems.add(ChunksFormatter.bool(new String(value), true));

            } else if (lastMatcher.start("lastNu") == 0) {
                jsonArrayItems.add(ChunksFormatter.nulll(new String(value), true));

            } else {
                throwException(-1);
            }

            for (int i = valueStart; i <= valueEnd; i++) {
                copy[i] = ' ';
            }
            return matcher;
        }

        if (matcher.start("quotes") == start) {
            jsonArrayItems.add(ChunksFormatter.quotes(new String(value), false));

        } else if (matcher.start("brackets") == start) {
            jsonArrayItems.add(ChunksFormatter.brackets(new String(value), false));

        } else if (matcher.start("braces") == start) {
            jsonArrayItems.add(ChunksFormatter.braces(new String(value), false));

        } else if (matcher.start("number") == start) {
            jsonArrayItems.add(ChunksFormatter.number(new String(value), false));

        } else if (matcher.start("tr") == start) {
            jsonArrayItems.add(ChunksFormatter.bool(new String(value), false));

        } else if (matcher.start("fa") == start) {
            jsonArrayItems.add(ChunksFormatter.bool(new String(value), false));

        } else if (matcher.start("nu") == start) {
            jsonArrayItems.add(ChunksFormatter.nulll(new String(value), false));

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
