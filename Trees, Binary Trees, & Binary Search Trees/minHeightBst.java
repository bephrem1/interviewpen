/*
  Convert Sorted Array to Binary Search Tree - LeetCode: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

  This code passes all Leetcode test cases as of Sept. 10 2019
  Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
*/

public TreeNode sortedArrayToBST(int[] nums) {
  return buildMinHeighTree(nums, 0, nums.length);
}

private TreeNode buildMinHeighTree(int[] nums, int left, int right) {
  if (left >= right) {
    return null;
  }

  /*
    Pull out the value from the middle item and create a new node with
    this value
  */
  int middleIndex = left + ((right - left) / 2);
  TreeNode newNode = new TreeNode(nums[middleIndex]);

  /*
    Set the left and right subtrees of this new node using this same function.
    Each call will root the tree built at the "most balanced" node, which is
    the middle of the sorted sub-section (between left & right)
  */
  newNode.left = buildMinHeighTree(nums, left, middleIndex);
  newNode.right = buildMinHeighTree(nums, middleIndex + 1, right);

  return newNode;
}
