/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean hasPathSum(TreeNode node, int targetSum) {
    if (node == null) {
      return false;
    }

    /*
     * If we are at a leaf node & the subtraction of the value of this node from the
     * running sum (we are reducing at each node) brings us to 0, this node
     * completes the path
     */
    boolean isLeaf = node.left == null && node.right == null;
    if (isLeaf && targetSum - node.val == 0) {
      return true;
    }

    /*
     * Reduce the subproblem, does the left subtree have a path or the right
     * subtree?
     * 
     * We "add" this node to the path by taking it's value from the target sum.
     * 
     * Path "completion" is if we can reach 0 exactly (all items add up to
     * 'targetSum').
     * 
     * So: 1 + 2 + 3 = 6
     * 
     * Is the same as: 0 = 6 - 1 - 2 - 3
     */
    return hasPathSum(node.left, targetSum - node.val) || hasPathSum(node.right, targetSum - node.val);
  }
}
