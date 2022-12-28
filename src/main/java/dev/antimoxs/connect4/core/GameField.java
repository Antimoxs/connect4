package dev.antimoxs.connect4.core;

import dev.antimoxs.connect4.api.IGameField;
import dev.antimoxs.connect4.api.IGameRender;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Internal GameField class used to store the current field.
 *
 * @author Antimoxs
 */
public class GameField implements IGameField {

    private int[][] gameField = new int[6][7];

    public GameField() {

        for (int[] row : gameField) {
            Arrays.fill(row, 0);
        }

    }

    /**
     * Insert piece into column with a value. (Drop a piece into a slot visually)
     * @param col The column to 'drop'
     * @param val The value to set.
     * @return The successful slot or error code -1 (Invalid operation) & -2 (Board is full)
     */
    public int addPiece(int col, int val) {

        if (col <= -1 || val <= -1 || col >= 7 || val >= 5) return -1;
        for (int i = 0; i < 6; i++) {

            int[] row = gameField[i];
            if (row[col] == 0) {

                row[col] = val;
                return i;

            }

        }
        return -2;

    }

    public int[] getRow(int row) { return gameField[row]; }

    public int[] getCol(int col) {
        ArrayList<Integer> colvals = new ArrayList<>();
        for (int[] row : gameField) {

            colvals.add(row[col]);

        }
        int[] ret = new int[colvals.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = colvals.get(i);
        }
        return ret;
    }

    @Override
    public int[] getFieldRow(int row) {
        return getRow(row).clone();
    }

    @Override
    public int[] getFieldCol(int col) {
        return getCol(col).clone();
    }

    @Override
    public int[][] getField() {
        return getGameField().clone();
    }

    @Override
    public void render(IGameRender render) {

        render.onGameRender(this);

    }

    public int getValue(int row, int pos) {

        return gameField[row][pos];

    }
    public void setValue(int row, int pos, int val) {

        gameField[row][pos] = val;

    }

    public int[][] getGameField() { return gameField; }

}
