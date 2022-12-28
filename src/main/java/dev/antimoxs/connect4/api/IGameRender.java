package dev.antimoxs.connect4.api;

/**
 * The GameRenderer used to render the current gameField.
 *
 * @author Antimoxs
 */
public interface IGameRender {

    /**
     * This method is called upon a field update.
     * @param field The game field.
     */
    void onGameRender(IGameField field);

}
