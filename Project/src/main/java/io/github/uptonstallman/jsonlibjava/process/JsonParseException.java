package io.github.uptonstallman.jsonlibjava.process;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The type Json parse exception.
 */
public class JsonParseException extends Exception {
    /**
     * Instantiates a new Json parse exception.
     *
     * @param mensaje the mensaje
     */
    public JsonParseException(String mensaje) {
        super(mensaje);
    }

    /**
     * Gets stack trace.
     *
     * @param throwable the throwable
     * @return the stack trace
     */
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(printWriter);
        return stringWriter.getBuffer().toString();
    }
}
