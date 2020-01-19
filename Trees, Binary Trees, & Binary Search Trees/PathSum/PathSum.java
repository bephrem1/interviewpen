class PathSum {
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

class PathSumIIIQuadratic {
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

class PathSumIIILinear {
  public int pathSum(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }

    Map<Integer, Integer> prefixSumToTotalPrefixes = new HashMap<>();

    prefixSumToTotalPrefixes.put(0, 1);

    return findPathSum(root, 0, sum, prefixSumToTotalPrefixes);
  }

  private int findPathSum(TreeNode node, int rootToNodeSum, int target,
      Map<Integer, Integer> prefixSumToTotalPrefixes) {
    if (node == null) {
      return 0;
    }

    rootToNodeSum += node.val;
    
    int amountToCompensateFor = rootToNodeSum - target;
    int totalPathsEndingAtThisNode = prefixSumToTotalPrefixes.getOrDefault(amountToCompensateFor, 0);
    
    int totalPathsWithThisPathsSum = prefixSumToTotalPrefixes.getOrDefault(rootToNodeSum, 0);
    prefixSumToTotalPrefixes.put(rootToNodeSum, totalPathsWithThisPathsSum + 1);

    int totalCompletedPathsInThisSubtree = totalPathsEndingAtThisNode
        + findPathSum(node.left, rootToNodeSum, target, prefixSumToTotalPrefixes)
        + findPathSum(node.right, rootToNodeSum, target, prefixSumToTotalPrefixes);

    prefixSumToTotalPrefixes.put(rootToNodeSum, prefixSumToTotalPrefixes.getOrDefault(rootToNodeSum, 0) - 1);

    return totalCompletedPathsInThisSubtree;
  }
}
