package dev.antimoxs.connect4;

import dev.antimoxs.connect4.api.IGameEnd;
import dev.antimoxs.connect4.api.IGameRender;
import dev.antimoxs.connect4.api.ILogOutput;
import dev.antimoxs.connect4.api.IPlayerTurn;
import dev.antimoxs.connect4.core.GameField;

import java.io.StringReader;

/**
 * Connect4 Library by Antimoxs.
 * This is a very simple connect4 library used in some
 * of my projects. It's very dynamic and can be used
 * in any other project.
 *
 * @author Antimoxs
 * @param <T> The Class used as player-object.
 */
public final class Connect4<T> {

    /**
     * The first player.
     */
    private final T player1;

    /**
     * The second player.
     */
    private final T player2;

    /**
     * Player-Turn callback to implement own input.
     */
    private final IPlayerTurn<T> onPlayerTurn;

    /**
     * Game-End callback.
     */
    private final IGameEnd<T> onGameEnd;

    /**
     * Game-Render callback.
     */
    private final IGameRender onGameRender;

    /**
     * Game-Log callback.
     */
    private final ILogOutput output;

    /**
     * The actual gameField.
     */
    private final GameField field;

    /**
     * Who's next?
     */
    private boolean player1turn = false;
    private T winner = null;

    /**
     * Create a new Connect4 Game.
     * @param player1 First Player (Will begin)
     * @param player2 Second Player
     * @param onPlayerTurn Will be called before in every turn.
     * @param onGameEnd Will be called on game end.
     * @param onGameRender Will be called whenever the gameField is updated.
     * @param output Log output (optional, can be null)
     */
    public Connect4(T player1, T player2, IPlayerTurn<T> onPlayerTurn, IGameEnd<T> onGameEnd, IGameRender onGameRender, ILogOutput output) {

        this.player1 = player1;
        this.player2 = player2;
        this.onPlayerTurn = onPlayerTurn;
        this.onGameEnd = onGameEnd;
        this.onGameRender = onGameRender;
        this.output = output == null ? message -> {} : output; // no log :( ?

        this.field = new GameField();

    }

    /**
     * Start the created game!
     */
    public void start() {

        this.output.onLogMessage("Starting Connect4!");
        boolean gameEnded;

        do {

            gameEnded = gameLoop();

        }
        while (!gameEnded);

        this.output.onLogMessage("We have a winner!");
        this.onGameRender.onGameRender(this.field);
        this.onGameEnd.onGameEnd(this.winner);

    }

    /**
     * Internal gameLoop method which is run for every turn.
     * @return Whether the game has ended.
     */
    private boolean gameLoop() {

        this.player1turn = !player1turn;
        this.onGameRender.onGameRender(this.field);

        int col = this.onPlayerTurn.getPlayerTurn(player1turn ? player1 : player2);
        int result = this.field.addPiece(col, player1turn ? 1 : 2);

        if (result == -1) {

            this.output.onLogMessage("This is an invalid move!");
            this.player1turn = !player1turn;
            return false;

        }
        else if (result == -2) {

            winner = null;
            return true;

        }

        this.winner = player1turn ? player1 : player2;
        return check();

    }

    /**
     * Internal check method to check for any wins.
     * @return Whether we have a win;
     */
    private boolean check() {

        // check horizontal
        for (int row = 0; row < 6; row++) {

            int[] r = this.field.getRow(row);

            // pos to 7, until 4 check there 4 in row
            for (int pos = 0; pos < 4; pos++) {

                // v = value
                int v = r[pos];
                if (v == 0) {
                    continue;
                }

                if (r[pos + 1] == v && r[pos + 2] == v && r[pos + 3] == v) {

                    this.field.setValue(row, pos + 0, v + 2);
                    this.field.setValue(row, pos + 1, v + 2);
                    this.field.setValue(row, pos + 2, v + 2);
                    this.field.setValue(row, pos + 3, v + 2);
                    return true;

                }

            }


        }

        // check vertical
        for (int col = 0; col < 7; col++) {

            int[] c = this.field.getCol(col);

            for (int pos = 0; pos < 3; pos++) {

                // v = value
                int v = c[pos];
                if (v == 0) {
                    continue;
                }

                if (c[pos + 1] == v && c[pos + 2] == v && c[pos + 3] == v) {

                    this.field.setValue(pos + 0, col, v + 2);
                    this.field.setValue(pos + 1, col, v + 2);
                    this.field.setValue(pos + 2, col, v + 2);
                    this.field.setValue(pos + 3, col, v + 2);
                    return true;

                }

            }

        }

        // check diagonalLR
        // first 4 rows
        for (int row = 0; row < 3; row++) {

            for (int pos = 0; pos < 4; pos++) {

                int v = this.field.getValue(row, pos);
                if (v == 0) {
                    continue;
                }
                int X1 = this.field.getValue(row + 1, pos + 1);
                int X2 = this.field.getValue(row + 2, pos + 2);
                int X3 = this.field.getValue(row + 3, pos + 3);
                if (X1 == v && X2 == v && X3 == v) {

                    this.field.setValue(row + 0, pos + 0, v + 2);
                    this.field.setValue(row + 1, pos + 1, v + 2);
                    this.field.setValue(row + 2, pos + 2, v + 2);
                    this.field.setValue(row + 3, pos + 3, v + 2);
                    return true;

                }

            }

        }

        // check diagonalRL
        for (int row = 3; row < 6; row++) {

            for (int pos = 0; pos < 4; pos++) {

                int v = this.field.getValue(row, pos);
                if (v == 0) {
                    continue;
                }
                int X1 = this.field.getValue(row - 1, pos + 1);
                int X2 = this.field.getValue(row - 2, pos + 2);
                int X3 = this.field.getValue(row - 3, pos + 3);
                if (X1 == v && X2 == v && X3 == v) {

                    this.field.setValue(row - 0, pos + 0, v + 2);
                    this.field.setValue(row - 1, pos + 1, v + 2);
                    this.field.setValue(row - 2, pos + 2, v + 2);
                    this.field.setValue(row - 3, pos + 3, v + 2);
                    return true;

                }

            }

        }

        return false;

    }

}
