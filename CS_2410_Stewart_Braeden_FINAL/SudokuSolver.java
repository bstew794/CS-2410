package CS_2410_Stewart_Braeden_FINAL;

public class SudokuSolver {

    static int[][] solve(int[][] myBoard) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (myBoard[row][column] == 0) {
                    for (int i = 1; i <= 9; i++) {
                        myBoard[row][column] = i;
                        if (isLegal(myBoard, row, column)) {
                            if (hasZeroes(myBoard)) {
                                myBoard = solve(myBoard); // expands recursion out with legal value of cell
                            }
                            if (!hasZeroes(myBoard)) {
                                return myBoard;  // used to collapse call that found the correct board
                            }
                        }
                    }
                    myBoard[row][column] = 0;  // no legal move found. Backtrack
                    return myBoard;
                }
            }
        }
        return null;
    }

    static boolean isLegal(int[][] board, int changedId1, int changedId2) { // checks if a changed number is legal
        int changedValue = board[changedId1][changedId2];
        board[changedId1][changedId2] = 0; // change value to zero, so that we can find duplicates

        int[] myRow = board[changedId1];

        int[] myColummn = new int[board.length];
        for (int findColumn = 0; findColumn < board.length; findColumn++) {
            myColummn[findColumn] = board[findColumn][changedId2];
        }

        // this section only works for 9 tile sudoku board
        // find the square the changedValue resides in
        int[] mySquare = new int[board.length];
        int g;
        int h;

        if (changedId1 < 3) {g = 0;}
        else if (changedId1 < 6) {g = 3;}
        else {g = 6;}

        if (changedId2 < 3) {h = 0;}
        else if (changedId2 < 6) {h = 3;}
        else {h = 6;}

        int index = 0;
        for (int rowG = g; rowG <= (g + 2); rowG ++) {
            for (int rowH = h; rowH <= (h + 2); rowH++) {
                mySquare[index] = board[rowG][rowH];
                index++;
            }
        }

        // see if changed value is inside its row, column, or square already.
        for (int checkingIndex = 0; checkingIndex < board.length; checkingIndex++) {
            if (changedValue == myRow[checkingIndex] || changedValue == myColummn[checkingIndex] || changedValue == mySquare[checkingIndex]) {
                board[changedId1][changedId2] = changedValue; // change value back
                return false; // duplicate found. Move is illegal
            }
        }
        board[changedId1][changedId2] = changedValue; //change value back
        return true; // no duplicates found. Move is legal
    }

    static boolean hasZeroes(int[][] board) {  // check to see if board has any zeroes
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}