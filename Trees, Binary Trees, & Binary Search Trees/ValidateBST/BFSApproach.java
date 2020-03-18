/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean isValidBST(TreeNode root) {
    Queue<AugmentedTreeNode> queue = new LinkedList<>();
    queue.add(new AugmentedTreeNode(root, Long.MIN_VALUE, Long.MAX_VALUE));

    while (!queue.isEmpty()) {
      AugmentedTreeNode augmentedNode = queue.poll();

      if (augmentedNode.node != null) {
        // Check the constraints on the node
        int nodeValue = augmentedNode.node.val;
        if (nodeValue <= augmentedNode.min || nodeValue >= augmentedNode.max) {
          return false;
        }

        queue.add(new AugmentedTreeNode(augmentedNode.node.left, augmentedNode.min, nodeValue));
        queue.add(new AugmentedTreeNode(augmentedNode.node.right, nodeValue, augmentedNode.max));
      }
    }

    return true;
  }

  private class AugmentedTreeNode {
    TreeNode node;
    long min, max;

    public AugmentedTreeNode(TreeNode node, long min, long max) {
      this.node = node;
      this.min = min;
      this.max = max;
    }
  }
}
