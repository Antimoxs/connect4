package dev.antimoxs.connect4.api;

/**
 * The IGameField interface is used to obtain data from the current
 * game's field.
 *
 * @author Antimoxs
 */

 /* It follows the following design:
 *
 *     + - - - - - - - +
 *   5 | X             |
 *   4 | O X   O       |
 *   3 | O O X X       |
 *   3 | X O O O   O   |
 *   1 | X X O X   X X |
 *   0 | X O O X   O X |
 *   A + - - - - - - - +
 *     B 0 1 2 3 4 5 6
 * #
 *  0 = Empty
 *  1 = Player 1
 *  2 = Player 2
 *
 *  3 = Player 2 WIN
 *  4 = Player 4 WIN
 *
 *     + - - - - - - - +
 *   5 | 1 0 0 0 0 0 0 |
 *   4 | 2 1 0 2 0 0 0 |
 *   3 | 2 2 1 1 0 0 0 |
 *   3 | 1 2 2 2 0 2 0 |
 *   1 | 1 1 2 1 0 1 1 |
 *   0 | 1 2 2 1 0 2 1 |
 *   A + - - - - - - - +
 *     B 0 1 2 3 4 5 6
 */
public interface IGameField {

    /**
     * Get a certain row from the field.
     * @param row Row index (0-5)
     * @return The requested row.
     */
    int[] getFieldRow(int row);

    /**
     * Get a certain column from the field.
     * @param col Column index (0-6)
     * @return The requested column.
     */
    int[] getFieldCol(int col);

    /**
     * Get the entire field.
     * @return The field.
     */
    int[][] getField();

    /**
     * Make a render of the game with the given render callback.
     * @param render Render callback to be used.
     */
    void render(IGameRender render);

}
