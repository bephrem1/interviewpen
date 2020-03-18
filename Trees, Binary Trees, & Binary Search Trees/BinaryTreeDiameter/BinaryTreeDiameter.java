/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int diameterOfBinaryTree(TreeNode root) {
    RecursiveAnswer finalAnswer = subtreeMaxDiameter(root);

    return finalAnswer.bestDiameterSeenSoFar;
  }

  /*
    The "diameter" of a given subtree is the longest path between "any two
    nodes in the tree". If we want a maximal diameter, we want to know the
    deepest depth of the left & right subtree of a node (this maximizes the
    path) and we then add 1 to account for the subtree root.
  */
  private RecursiveAnswer subtreeMaxDiameter(TreeNode root) {
    if (root == null) {
      return new RecursiveAnswer(0, 0);
    }

    RecursiveAnswer left = subtreeMaxDiameter(root.left);
    RecursiveAnswer right = subtreeMaxDiameter(root.right);

    int leftAndRightMax = Math.max(left.bestDiameterSeenSoFar, right.bestDiameterSeenSoFar);
    int bestDiameterSeenSoFar = Math.max(leftAndRightMax, left.subtreeHeight + right.subtreeHeight);

    // +1 to include the root itself along with the deeper subtree height
    int subtreeHeight = Math.max(left.subtreeHeight, right.subtreeHeight) + 1;

    return new RecursiveAnswer(bestDiameterSeenSoFar, subtreeHeight);
  }

  private class RecursiveAnswer {
    int bestDiameterSeenSoFar;
    int subtreeHeight;

    public RecursiveAnswer(int bestDiameterSeenSoFar, int subtreeHeight) {
      this.bestDiameterSeenSoFar = bestDiameterSeenSoFar;
      this.subtreeHeight = subtreeHeight;
    }
  }
}
