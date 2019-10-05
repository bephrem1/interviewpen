/*
  Symmetric Tree - LeetCode: https://leetcode.com/problems/symmetric-tree/
  This code passes all Leetcode test cases as of Oct. 4 2019
*/

public boolean isSymmetric(TreeNode root) {
  if (root == null) {
    return true;
  }

  return checkSymmetry(root.left, root.right);
}

private boolean checkSymmetry(TreeNode leftSubtreeRoot, TreeNode rightSubtreeRoot) {
  if (leftSubtreeRoot == null && rightSubtreeRoot == null) {
    return true;
  }

  if (leftSubtreeRoot != null && rightSubtreeRoot != null) {
    return leftSubtreeRoot.val == rightSubtreeRoot.val &&
      checkSymmetry(leftSubtreeRoot.right, rightSubtreeRoot.left) &&
      checkSymmetry(leftSubtreeRoot.left, rightSubtreeRoot.right);
  }

  return false;
}
