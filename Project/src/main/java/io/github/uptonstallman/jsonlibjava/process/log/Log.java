package io.github.uptonstallman.jsonlibjava.process.log;

/**
 * The interface Log.
 */
public interface Log {

    /**
     * Info.
     *
     * @param m the m
     */
    public void info(String m);

    /**
     * Debug.
     *
     * @param m the m
     */
    public void debug(String m);

    /**
     * Warn.
     *
     * @param m the m
     */
    public void warn(String m);

    /**
     * Error.
     *
     * @param m the m
     */
    public void error(String m);

    /**
     * Error.
     *
     * @param m the m
     * @param t the t
     */
    public void error(String m, Throwable t);

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel();

}
