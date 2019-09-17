/*
  Validate Binary Search Tree - LeetCode: https://leetcode.com/problems/validate-binary-search-tree/
  This code passes all Leetcode test cases as of Sept. 16 2019
*/

class RecursiveApproach {
  public boolean isValidBST(TreeNode root) {
    return isNodeValueInRange(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isNodeValueInRange(TreeNode node, long min, long max) {
    if (node == null) {
      return true;
    } else if (node.val <= min || node.val >= max) {
      return false;
    }

    return isNodeValueInRange(node.left, min, node.val)
      && isNodeValueInRange(node.right, node.val, max);
  }
}

/*
  Validating in a breadth-first manner improves the real
  elapsed time because we will find a violating node often
  earlier by not going depth-first into the left subtree
*/
class BFSWithNodeAugmentation {
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
