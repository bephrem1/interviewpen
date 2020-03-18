/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> spiralSequence = new ArrayList<>();

    if (matrix.length == 0) {
      return spiralSequence;
    }

    // Our restricting boundaries - imagine a "fence"
    int topFence = 0;
    int bottomFence = matrix.length - 1; // last row index
    int leftFence = 0;
    int rightFence = matrix[0].length - 1; // last col index

    while (topFence <= bottomFence && leftFence <= rightFence) {
      // Walk top fence
      for (int col = leftFence; col <= rightFence; col++) {
        spiralSequence.add(matrix[topFence][col]);
      }
      topFence++; // Push top fence in

      // Walk right fence
      for (int row = topFence; row <= bottomFence; row++) {
        spiralSequence.add(matrix[row][rightFence]);
      }
      rightFence--; // Close right fence in

      /*
        If center is a horizontal line, prevent the bottom from rereading
        what the top row already read.
      */
      if (topFence <= bottomFence) {
        // Walk bottom fence
        for (int col = rightFence; col >= leftFence; col--) {
          spiralSequence.add(matrix[bottomFence][col]);
        }
      }
      bottomFence--; // Close the bottom fence in

      /*
        If center is a vertical line, prevent the left from rereading
        what the right col already read.
      */
      if (leftFence <= rightFence) {
        // Walk left fence
        for (int row = bottomFence; row >= topFence; row--) {
          spiralSequence.add(matrix[row][leftFence]);
        }
        leftFence++; // Push to left fence in
      }
    }

    return spiralSequence;
  }
}
