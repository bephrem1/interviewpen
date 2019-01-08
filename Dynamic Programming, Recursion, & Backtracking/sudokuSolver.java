/*
  Authorship: ALL credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.
  
  I have just adapted the solution to pass on Leetcode, added explanatory
  comments, reformatted the code, & changed variable names for understanding.

  Sudoku Solver - LeetCode: https://leetcode.com/problems/sudoku-solver/

  This code passes all Leetcode test cases as of Jan. 8 2019 (12:18 am)
  Runtime: 11 ms*, faster than 73.28% of Java online submissions for Sudoku Solver.

  * Funny Note: Took me 30 minutes of unchecked code editing (no IDE) to get the code
  in working order before I first ran it. IT WORKED FIRST RUN IN LEETCODE. No syntax
  errors, no out of bounds exceptions. That is so impossible, but I'll take it.

  The video to explain this code is here: [a link will live here someday]
*/

private static final char EMPTY_ENTRY = '.';

/*
  Driver function to kick off the recursion
*/
public static boolean solveSudoku(char[][] board){
  return solveSudokuCell(0 , 0, board);
}

/*
  This function chooses a placement for the cell at (row, col)
  and continues solving based on the rules we define.
  
  Our strategy:
  We will start at row 0.
  We will solve every column in that row.
  When we reach the last column we move to the next row.
  If this is past the last row (row == board.length) we are done.
  The whole board has been solved.
*/
private static boolean solveSudokuCell(int row, int col, char[][] board) {

  /*
    Have we finished placements in all columns for
    the row we are working on?
  */
  if (col == board[row].length){

    /*
      Yes. Reset to col 0 and advance the row by 1.
      We will work on the next row.
    */
    col = 0;
    row++;

    /*
      Have we completed placements in all rows? If so then we are done.
      If not, drop through to the logic below and keep solving things.
    */
    if (row == board.length){
      return true; // Entire board has been filled without conflict.
    }

  }

  // Skip non-empty entries. They already have a value in them.
  if (board[row][col] != EMPTY_ENTRY) {
    return solveSudokuCell(row, col + 1, board);
  }

  /*
    Try all values 1 through 9 in the cell at (row, col).
    Recurse on the placement if it doesn't break the constraints of Sudoku.
  */
  for (int value = 1; value <= board.length; value++) {

    char charToPlace = (char) (value + '0'); // convert int value to char

    /*
      Apply constraints. We will only add the value to the cell if
      adding it won't cause us to break sudoku rules.
    */
    if (canPlaceValue(board, row, col, charToPlace)) {
      board[row][col] = charToPlace;
      if (solveSudokuCell(row, col + 1, board)) { // recurse with our VALID placement
        return true;
      }
    }

  }

  /*
    Undo assignment to this cell. No values worked in it meaning that
    previous states put us in a position we cannot solve from. Hence,
    we backtrack by returning "false" to our caller.
  */
  board[row][col] = EMPTY_ENTRY;
  return false; // No valid placement was found, this path is faulty, return false
}

/*
  Will the placement at (row, col) break the Sudoku properties?
*/
private static boolean canPlaceValue(char[][] board, int row, int col, char charToPlace) {

  // Check column constraint. For each row, we do a check on column "col".
  for (char[] element : board) {
    if (charToPlace == element[col]){
      return false;
    }
  }

  // Check row constraint. For each column in row "row", we do a check.
  for (int i = 0; i < board.length; i++) {
    if (charToPlace == board[row][i]) {
      return false;
    }
  }

  /*
    Check region constraints.
  */
  int regionSize = (int) Math.sqrt(board.length);

  int I = row / regionSize;
  int J = col / regionSize;

  int topLeftOfBlockRow = regionSize * I; // the row of the top left of the block
  int topLeftOfBlockCol = regionSize * J; // the column of the tol left of the block

  for (int i = 0; i < regionSize; i++) {
    for (int j = 0; j < regionSize; j++) {

      /*
        i and j just define our offsets from topLeftOfBlockRow
        and topLeftOfBlockCol respectively
      */
      if (charToPlace == board[topLeftOfBlockRow + i][topLeftOfBlockCol + j]) {
        return false;
      }

    }
  }

  return true; // placement is valid
}
