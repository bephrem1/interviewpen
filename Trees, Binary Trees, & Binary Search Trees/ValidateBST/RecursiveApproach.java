/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean isValidBST(TreeNode root) {
    return isNodeValueInRange(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isNodeValueInRange(TreeNode node, long min, long max) {
    if (node == null) {
      return true;
    } else if (node.val <= min || node.val >= max) {
      return false;
    }

    return isNodeValueInRange(node.left, min, node.val) && isNodeValueInRange(node.right, node.val, max);
  }
}
