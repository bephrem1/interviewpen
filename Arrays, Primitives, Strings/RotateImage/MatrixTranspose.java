/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void rotate(int[][] matrix) {
    flipVertically(matrix);
    transpose(matrix);
  }

  private void flipVertically(int[][] matrix) {
    for (int topRow = 0; topRow < (matrix.length / 2); topRow++) {
      int bottomRow = matrix.length - 1 - topRow;

      int[] temp = matrix[topRow];
      matrix[topRow] = matrix[bottomRow];
      matrix[bottomRow] = temp;
    }
  }

  // A matrix transpose turns each row into a column
  private void transpose(int[][] matrix) {
    for (int row = 0; row < matrix.length; row++) {
      for (int col = row + 1; col < matrix[0].length; col++) {
        int valueInUpperTriangle = matrix[row][col];
        int valueInLowerTriangle = matrix[col][row];

        matrix[row][col] = valueInLowerTriangle;
        matrix[col][row] = valueInUpperTriangle;
      }
    }
  }
}
