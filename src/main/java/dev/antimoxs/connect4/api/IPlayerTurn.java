package dev.antimoxs.connect4.api;

/**
 * The PlayerTurn interface.
 * @author Antimoxs
 * @param <T> PlayerObject as defined at game creation.
 */
public interface IPlayerTurn<T> {

    /**
     * This is the playerTurnCallback.
     * It's used to get an input to use as player move.
     * @param player The player which has to do the next move.
     * @return The desired column to play.
     */
    public int getPlayerTurn(T player);

}
