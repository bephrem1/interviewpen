/*
  Path Sum - LeetCode: https://leetcode.com/problems/path-sum/
  Path Sum III - Leetcode: https://leetcode.com/problems/path-sum-iii/
*/

/*
  This code passes all Leetcode test cases as of Sept. 12 2019
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Path Sum.
*/
class PathSum {
  public boolean hasPathSum(TreeNode node, int targetSum) {
    if (node == null) {
      return false;
    }

    /*
      If we are at a leaf node & the subtraction of the value
      of this node from the running sum (we are reducing at each
      node) brings us to 0, this node completes the path
    */
    boolean isLeaf = node.left == null && node.right == null;
    if (isLeaf && targetSum - node.val == 0) {
      return true;
    }

    /*
      Reduce the subproblem, does the left subtree have a path or
      the right subtree?

      We "add" this node to the path by taking it's value from the
      target sum.

      Path "completion" is if we can reach 0 exactly (all items add
      up to 'targetSum').

      So:
      1 + 2 + 3 = 6
      
      Is the same as:
      0 = 6 - 1 - 2 - 3
    */
    return hasPathSum(node.left, targetSum - node.val) ||
      hasPathSum(node.right, targetSum - node.val);
  }
}

/*
  This code passes all Leetcode test cases as of Sept. 12 2019
  Runtime: 11 ms, faster than 50.58% of Java online submissions for Path Sum III.
*/
class PathSumIIIQuadratic {
  public int pathSum(TreeNode node, int targetSum) {
    if (node == null) {
      return 0;
    }

    /*
      We will traverse the whole tree and do a 'pathSum' query from each
      node serving as root to a subtree of the overarching tree.

      1.) Find the total path sums with the root of the overarching tree
      as the root to the "subtree"

      2.) We ask our left subtree, "How many path sums can you yield?", keeping
      the original 'targetSum' intact

      3.) We ask our right subtree, "How many path sums can you yield?", keeping
      the original 'targetSum' intact

      We keep the 'targetSum' intact because it is the original query we are
      asking to every node in the whole tree.
    */
    return totalPathSumsFromThisNode(node, targetSum) +
      pathSum(node.left, targetSum) +
      pathSum(node.right, targetSum);
  }

  /*
    Helper function that counts total path sums with the initial 'node' passed into
    the call being treated as the tree root.
  */
  private int totalPathSumsFromThisNode(TreeNode node, int targetSum) {
    if (node == null) {
      return 0;
    }

    /*
      Does this node itself complete the running path? +1 if so, otherwise
      initialize to 0.
    */
    int totalCompletedPathsFromThisNode = targetSum - node.val == 0 ? 1 : 0;

    /*
      How many more paths can be completed from this node? Reduce the
      subproblem & recurse.
    */
    totalCompletedPathsFromThisNode +=
      totalPathSumsFromThisNode(node.left, targetSum - node.val) +
      totalPathSumsFromThisNode(node.right, targetSum - node.val);

    return totalCompletedPathsFromThisNode;
  }
}

/*
  This code passes all Leetcode test cases as of Sept. 12 2019
  Runtime: 3 ms, faster than 99.98% of Java online submissions for Path Sum III.
*/
class PathSumIIILinear {
  public int pathSum(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }

    /*
      The key is that at each node we will know the root to node sum following the
      path we recursed on.

      We will either overshoot, or undershoot, the target sum. We track prefixes to
      see if we can "compensate" by removing that prefix from the root to node path
      to hit the target sum.

      'prefixSumToTotalPrefixes' is a hashtable that maps:
        prefixSum -> total prefixes with that sum
      
      But what is the context? The context is that the prefixes
      we track are all connected & previous to the node we will be
      sitting at.

      So if we have:
            10
           /  \
          5   -3
         / \    \
        3   2   11
       / \   \
      3  -2   1

      And we are sitting at -2 in the last level, our prefix table will
      look like this:

      {
        0 -> 1      path: nothing - default
        16 -> 1     path: 10 (root) -> 5 -> 3 -> -2
        18 -> 1     path: 10 (root) -> 5 -> 3
        10 -> 1     path: 10 (root)
        15 -> 1     path: 10 (root) -> 5
      }

      Imagine our target is 8.

      The breakdown:
        - Our root (10) to node (-2) path sum is 10 + 5 + 3 + -2 = 16
        - How far away are we from the target?
          - We do rootToNodeSum - target = (16) - (8) = 8
          - This means we need to "chop off" 8 in value from this path
      
      This is where the prefixes come in. We can look up in the hashtable, how many
      unique prefixes up to this node (starting at the root) had sum 8?

      If they had sum 8 we can remove those to yield a "pruned path" that does not
      start from the root...but hits our target because it is connected to the node
      we are sitting at.
    */
    Map<Integer, Integer> prefixSumToTotalPrefixes = new HashMap<>();

    /*
      If we need to take away 0 from the root to leaf path to the node we sit at, then
      that is a complete path.

      Namely, we have a current sum that requires no "compensation" with prefix reduction,
      we have hit the target sum. This will be a literal path from the actual root to the
      node that we will stand at.
    */
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

    // Add the current node's value to the path we are tracking from the root to this node
    rootToNodeSum += node.val;

    /*
      Based on sum of the path from the root to here, how much must we "truncate" our current
      path's sum (by removing a prefix also coming from the root) to create a new path with
      the target sum?
    */
    int amountToCompensateFor = rootToNodeSum - target;
    int totalPathsUpToThisNode = prefixSumToTotalPrefixes.getOrDefault(amountToCompensateFor, 0);

    // Update the 'prefixSumToTotalPrefixes' with this root to node path itself
    int totalPathsWithThisPathsSum = prefixSumToTotalPrefixes.getOrDefault(rootToNodeSum, 0);
    prefixSumToTotalPrefixes.put(rootToNodeSum, totalPathsWithThisPathsSum + 1);

    // Investigate the total paths that can be completed in this node's left & right subtrees
    int totalCompletedPathsInThisSubtree =
      totalPathsUpToThisNode +
      findPathSum(node.left, rootToNodeSum, target, prefixSumToTotalPrefixes) +
      findPathSum(node.right, rootToNodeSum, target, prefixSumToTotalPrefixes);

    /*
      Cleanup, when we are back, we need to remove this path (the root to this node path we are on)
      from the prefix table before we return to our caller:

      So if we have:
            10
           /  \
          5   -3
         / \    \
        3   2   11
       / \   \
      3  -2   1

      And we are sitting at 3 in the last level, our prefix table will
      look like this:

      {
        0 -> 1      path: nothing - default
        18 -> 1     path: 10 (root) -> 5 -> 3
        21 -> 1     path: 10 (root) -> 5 -> 3 -> 3    *** will get decreased by 1 since we will backtrack ***
        10 -> 1     path: 10 (root)
        15 -> 1     path: 10 (root) -> 5
      }

      Before we return to the 3 that called us (in the 2nd to last layer), we must remove our prefix
      entry from the hastable (which is notated as a path with sum 21...10 (root) -> 5 -> 3 -> 3)

      After:
      {
        0 -> 1      path: nothing - default
        18 -> 1     path: 10 (root) -> 5 -> 3
        21 -> 0     *** can be removed if value hits 0 but doesn't matter ***
        10 -> 1     path: 10 (root)
        15 -> 1     path: 10 (root) -> 5
      }

      Then control will go back to the 3 (in the second to last level):
            10
           /  \
          5   -3
         / \    \
       >3<  2   11
       / \   \
      3  -2   1

      Real prefixes:
      {
        0 -> 1      path: nothing - default
        18 -> 1     path: 10 (root) -> 5 -> 3
        10 -> 1     path: 10 (root)
        15 -> 1     path: 10 (root) -> 5
      }
    */
    prefixSumToTotalPrefixes.put(rootToNodeSum, prefixSumToTotalPrefixes.getOrDefault(rootToNodeSum, 0) - 1);

    return totalCompletedPathsInThisSubtree;
  }
}
