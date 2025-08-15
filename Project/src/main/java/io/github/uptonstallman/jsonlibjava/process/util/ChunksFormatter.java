package io.github.uptonstallman.jsonlibjava.process.util;

import io.github.uptonstallman.jsonlibjava.process.JsonParseException;

/**
 * The type Chunks formatter.
 */
public class ChunksFormatter {

    /**
     * Key string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String key(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && value.charAt(value.length() - 1) != '"') {
                value.deleteCharAt(value.length() - 1);
            }
        }
        if (!value.toString().startsWith("\"") && !value.toString().endsWith("\"")) {
            throw new JsonParseException("key error: " + value.toString());
        }
        return value.toString().replaceAll("^\"|\"$", "");
    }

    /**
     * Quotes string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String quotes(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && value.charAt(value.length() - 1) != '"') {
                value.deleteCharAt(value.length() - 1);
            }
        }
        if (!value.toString().startsWith("\"") && !value.toString().endsWith("\"")) {
            throw new JsonParseException("value error: " + value.toString());
        }
        return value.toString();
    }

    /**
     * Brackets string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String brackets(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && value.charAt(value.length() - 1) != ']') {
                value.deleteCharAt(value.length() - 1);
            }
        }
        if (!value.toString().startsWith("[") && !value.toString().endsWith("]")) {
            throw new JsonParseException("value error: " + value.toString());
        }
        return value.toString();
    }

    /**
     * Braces string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String braces(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && value.charAt(value.length() - 1) != '}') {
                value.deleteCharAt(value.length() - 1);
            }
        }
        if (!value.toString().startsWith("{") && !value.toString().endsWith("}")) {
            throw new JsonParseException("value error: " + value.toString());
        }
        return value.toString();
    }

    /**
     * Number string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String number(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && (value.charAt(value.length() - 1) < 48
                    || value.charAt(value.length() - 1) > 57)
            ) {
                value.deleteCharAt(value.length() - 1);
            }
        }
        try {
            Float.parseFloat(value.toString());
        } catch (Exception e) {
            throw new JsonParseException("value error: " + value.toString());
        }
        return value.toString();
    }

    /**
     * Bool string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String bool(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && value.charAt(value.length() - 1) != 'e') {
                value.deleteCharAt(value.length() - 1);
            }
        }
        if (!value.toString().equals("true") && !value.toString().equals("false")) {
            throw new JsonParseException("value error: " + value.toString());
        }
        return value.toString();
    }

    /**
     * Nulll string.
     *
     * @param v      the v
     * @param isLast the is last
     * @return the string
     * @throws JsonParseException the json parse exception
     */
    public static String nulll(String v, boolean isLast) throws JsonParseException {
        StringBuilder value = new StringBuilder(v);
        if (!isLast) {
            while (value.length() > 0 && value.charAt(value.length() - 1) != 'l') {
                value.deleteCharAt(value.length() - 1);
            }
        }
        if (!value.toString().equals("null")) {
            throw new JsonParseException("value error: " + value.toString());
        }
        return value.toString();
    }

}
