package dev.antimoxs.connect4.api;

/**
 * Logger interface :)
 *
 * @author Antimoxs
 */
public interface ILogOutput {

    /**
     * The logging method.
     * @param message Message to be logged.
     */
    void onLogMessage(String message);

}
