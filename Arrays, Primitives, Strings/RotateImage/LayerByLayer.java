/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void rotate(int[][] matrix) {
    int size = matrix.length - 1; // this is really the last index

    for (int layer = 0; layer < (matrix.length / 2); layer++) {
      for (int i = layer; i < size - layer; i++) {
        int topFence = matrix[layer][i];                  // starts at top left of layer
        int rightFence = matrix[i][size - layer];         // starts at top right of layer
        int bottomFence = matrix[size - layer][size - i]; // starts at bottom right of layer
        int leftFence = matrix[size - i][layer];          // starts at bottom left of layer

        // rotate 90 degrees clockwise element by element
        matrix[layer][i] = leftFence;                     // set value walking top fence
        matrix[i][size - layer] = topFence;               // set value walking right fence
        matrix[size - layer][size - i] = rightFence;      // set value walking bottom fence
        matrix[size - i][layer] = bottomFence;            // set value walking left fence
      }
    }
  }
}
