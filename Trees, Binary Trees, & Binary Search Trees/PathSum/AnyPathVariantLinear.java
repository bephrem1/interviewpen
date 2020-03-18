/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int pathSum(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }

    Map<Integer, Integer> prefixSumToTotalPrefixes = new HashMap<>();

    prefixSumToTotalPrefixes.put(0, 1);

    return findPathSum(root, 0, sum, prefixSumToTotalPrefixes);
  }

  private int findPathSum(
    TreeNode node,
    int rootToNodeSum,
    int target,
    Map<Integer, Integer> prefixSumToTotalPrefixes
  ) {
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
