/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public static final char LETTER_O = 'O';
  public static final char LETTER_X = 'X';
  public static final char PRESERVATION_CHAR = 'P';
  
  public void solve(char[][] board) {
    if (board.length == 0) {
      return;
    }

    /*
      We will:
      1.) Do a DFS and preserve cells starting from border 'O's on the left and right borders
      2.) Do the same for the top and bottom borders
      3.) Capture all 'O's left unpreserved, they by rule must be surrounded
      4.) Restore all of the preserved 'O's
    */
    
    preserveFromLeftAndRight(board);
    preserveFromTopAndBottom(board);
    capture(board);
    restorePreservedCells(board);
  }
  
  private void preserveFromLeftAndRight(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      if (board[row][0] == LETTER_O) {
        preserveRegion(board, row, 0);
      }

      int totalCols = board[row].length;
      if (board[row][totalCols - 1] == LETTER_O) {
        preserveRegion(board, row, totalCols - 1);
      }
    }
  }

  private void preserveFromTopAndBottom(char[][] board) {
    for (int col = 0; col < board[0].length; col++) {
      if (board[0][col] == LETTER_O) {
        preserveRegion(board, 0, col);
      }
      
      int totalRows = board.length;
      if (board[totalRows - 1][col] == LETTER_O) {
        preserveRegion(board, totalRows - 1, col);
      }
    }
  }

  private void capture(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == LETTER_O) {
          board[row][col] = LETTER_X;
        }
      }
    }
  }

  private void restorePreservedCells(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == PRESERVATION_CHAR) {
          board[row][col] = LETTER_O;
        }
      }
    }
  }
  
  private void preserveRegion(char[][] board, int row, int col) {
    if (!isInBounds(board, row, col) || board[row][col] != LETTER_O) {
        return;
    }

    // Preserve this character
    board[row][col] = PRESERVATION_CHAR;

    // Continue searching
    preserveRegion(board, row - 1, col); // up
    preserveRegion(board, row + 1, col); // down
    preserveRegion(board, row, col - 1); // left
    preserveRegion(board, row, col + 1); // right
  }

  private boolean isInBounds(char[][] board, int row, int col) {
    int totalRows = board.length;
    int totalCols = board[0].length;

    return row >= 0 && row < totalRows && col >= 0 && col < totalCols;
  }
}
