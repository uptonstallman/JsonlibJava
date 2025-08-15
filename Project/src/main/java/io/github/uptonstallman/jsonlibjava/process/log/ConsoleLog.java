package io.github.uptonstallman.jsonlibjava.process.log;

import io.github.uptonstallman.jsonlibjava.process.JsonParseException;

/**
 * The type Console log.
 * To override default log level: System property -DjsonlibLogLevel=DEBUG
 */
public class ConsoleLog implements Log {

    /**
     * The constant RESET.
     */
    public static final String RESET = "\u001B[0m";
    /**
     * The constant BLACK.
     */
    public static final String BLACK = "\u001B[30m";
    /**
     * The constant RED.
     */
    public static final String RED = "\u001B[31m";
    /**
     * The constant GREEN.
     */
    public static final String GREEN = "\u001B[32m";
    /**
     * The constant YELLOW.
     */
    public static final String YELLOW = "\u001B[33m";
    /**
     * The constant BLUE.
     */
    public static final String BLUE = "\u001B[34m";
    /**
     * The constant PURPLE.
     */
    public static final String PURPLE = "\u001B[35m";
    /**
     * The constant CYAN.
     */
    public static final String CYAN = "\u001B[36m";
    /**
     * The constant WHITE.
     */
    public static final String WHITE = "\u001B[37m";

    private Level level = Level.INFO; // default level

    /**
     * Instantiates a new Console log.
     */
    public ConsoleLog() {
        String jsonlibLogLevel = System.getProperty("jsonlibLogLevel");
        if (jsonlibLogLevel != null) {
            level = Level.valueOf(jsonlibLogLevel);
        }
    }

    /**
     * Instantiates a new Console log.
     *
     * @param overrideLevel the override level
     */
    public ConsoleLog(Level overrideLevel) {
        this.level = overrideLevel;
    }

    @Override
    public void debug(String m) {
        if (level.equals(Level.DEBUG))
            System.out.println(PURPLE + "DEBUG" + RESET + " " + getCallerInfo() + " " + m);
    }

    @Override
    public void info(String m) {
        if (level.equals(Level.DEBUG) || level.equals(Level.INFO))
            System.out.println(GREEN + "INFO" + RESET + " " + getCallerInfo() + " " + m);
    }

    @Override
    public void warn(String m) {
        if (level.equals(Level.DEBUG) || level.equals(Level.INFO) || level.equals(Level.WARN))
            System.out.println(YELLOW + "INFO" + RESET + getCallerInfo() + " " + m);
    }

    @Override
    public void error(String m) {
        System.err.println(RED + "ERROR" + RESET + getCallerInfo() + " " + m);
    }

    @Override
    public void error(String m, Throwable t) {
        try {
            error(m + " " + (t == null ? "null" : JsonParseException.getStackTrace(t)));
        } catch (Throwable ignored) {}
    }

    @Override
    public Level getLevel() {
        return level;
    }

    private String getCallerInfo() {
        return
                Thread.currentThread().getStackTrace()[3].getClassName() + "." +
                Thread.currentThread().getStackTrace()[3].getMethodName() +
                "(" + Thread.currentThread().getStackTrace()[3].getFileName()
                    + ":" +
                Thread.currentThread().getStackTrace()[3].getLineNumber() + ")";
    }
}

