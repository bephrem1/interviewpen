class Solution {
  public boolean search(int[][] matrix, int target) {
    if (matrix.length == 0) {
      return false;
    }

    int col = matrix[0].length - 1;
    int row = 0;
=
    while (col >= 0 && row <= matrix.length - 1) {
      /*
        Cases:
          target == item : We found the item. Return true.
          target < item : Target must be to our left...go down in value
          target > item : Target must be to our below...go up in value
      */
      if (target == matrix[row][col]) {
        return true;
      } else if (target < matrix[row][col]) {
        col--;
      } else if (target > matrix[row][col]) {
        row++;
      }
    }

    return false;
  }
}
