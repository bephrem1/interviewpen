/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean isBalanced(TreeNode root) {
    return checkBalanced(root).isBalanced;
  }

  private BalanceStatusWithHeight checkBalanced(TreeNode root) {

    /*
     * Base case, an empty subtree is balanced and has a height of -1 as we define
     * it (since it is technically below "sea level", weird analogy)
     */
    if (root == null) {
      return new BalanceStatusWithHeight(true, -1);
    }

    /*
     * Go deep into the left subtree and get a result back
     */
    BalanceStatusWithHeight leftResult = checkBalanced(root.left);
    if (!leftResult.isBalanced) {
      return leftResult; // Left subtree is not balanced. Bubble up failure.
    }

    /*
     * Go deep into the right subtree and get a result back
     */
    BalanceStatusWithHeight rightResult = checkBalanced(root.right);
    if (!rightResult.isBalanced) {
      return rightResult; // Right subtree is not balanced. Bubble up failure.
    }

    /*
     * Our left and right subtrees are back and we have our results. Let us analyze
     * them here and bubble up our own answer.
     * 
     * 1.) Check if the subtreesAreBalanced 2.) Notate the height that this node
     * sits at (which is 1 plus the height of the larger of the left and right
     * subtrees coming off of this node)
     */
    boolean subtreesAreBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;

    return new BalanceStatusWithHeight(subtreesAreBalanced, height);
  }

  /*
    We care about 2 things as our recursion goes upwards after bottoming out:
    1.)  The node's height
    2.)  Whether its left and right subtrees are balanced
  */
  private class BalanceStatusWithHeight {
    public boolean isBalanced;
    public int height;

    public BalanceStatusWithHeight(boolean isBalanced, int height) {
      this.isBalanced = isBalanced;
      this.height = height;
    }
  }
}

/* Solution via EPI (Elements of Programming Interviews) */
