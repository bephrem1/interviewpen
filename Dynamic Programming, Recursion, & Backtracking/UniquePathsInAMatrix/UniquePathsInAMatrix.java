/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int uniquePaths(int rows, int cols) {
    int[][] uniquePathsToPosition = new int[rows][cols];

    // 1 unique way to get to left wall cells (go straight down)
    for (int row = 0; row < rows; row++) {
      uniquePathsToPosition[row][0] = 1;
    }

    // 1 unique way to get to the top wall cells (go straight right)
    for (int col = 0; col < cols; col++) {
      uniquePathsToPosition[0][col] = 1;
    }

    /*
      Unique ways to inner position is combination of unique ways coming
      from both possible directions
    */
    for (int row = 1; row < rows; row++) {
      for (int col = 1; col < cols; col++) {
        int uniqueWaysToAboveCell = uniquePathsToPosition[row - 1][col];
        int uniqueWaysToLeftCell = uniquePathsToPosition[row][col - 1];

        uniquePathsToPosition[row][col] = uniqueWaysToAboveCell + uniqueWaysToLeftCell;
      }
    }

    return uniquePathsToPosition[rows - 1][cols - 1];
  }
}
