package dev.antimoxs.connect4.api;

/**
 * The GameEnd interface.
 * @author Antimoxs
 * @param <T> PlayerObject as defined at game creation.
 */
public interface IGameEnd<T> {

    /**
     * The method is called when the game has ended.
     * @param winner The winning player T or null (board full).
     */
    void onGameEnd(T winner);

}
