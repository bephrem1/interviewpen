/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int pathSum(TreeNode node, int targetSum) {
    if (node == null) {
      return 0;
    }

    /*
     * We will traverse the whole tree and do a 'pathSum' query from each node
     * serving as root to a subtree of the overarching tree.
     * 
     * 1.) Find the total path sums with the root of the overarching tree as the
     * root to the "subtree"
     * 
     * 2.) We ask our left subtree, "How many path sums can you yield?", keeping the
     * original 'targetSum' intact
     * 
     * 3.) We ask our right subtree, "How many path sums can you yield?", keeping
     * the original 'targetSum' intact
     * 
     * We keep the 'targetSum' intact because it is the original query we are asking
     * to every node in the whole tree.
     */
    return totalPathSumsFromThisNode(node, targetSum) + pathSum(node.left, targetSum) + pathSum(node.right, targetSum);
  }

  /*
   * Helper function that counts total path sums with the initial 'node' passed
   * into the call being treated as the tree root.
   */
  private int totalPathSumsFromThisNode(TreeNode node, int targetSum) {
    if (node == null) {
      return 0;
    }

    /*
     * Does this node itself complete the running path? +1 if so, otherwise
     * initialize to 0.
     */
    int totalCompletedPathsFromThisNode = targetSum - node.val == 0 ? 1 : 0;

    /*
     * How many more paths can be completed from this node? Reduce the subproblem &
     * recurse.
     */
    totalCompletedPathsFromThisNode += totalPathSumsFromThisNode(node.left, targetSum - node.val)
        + totalPathSumsFromThisNode(node.right, targetSum - node.val);

    return totalCompletedPathsFromThisNode;
  }
}
