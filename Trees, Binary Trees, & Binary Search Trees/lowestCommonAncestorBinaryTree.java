/**
  @author Benyam Ephrem

  Lowest Common Ancestor of a Binary Tree - LeetCode:
  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree

  This code passes all Leetcode test cases as of Jan. 1st 2019
  Runtime: 5 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
  Memory Usage: 33.9 MB, less than 68.78% of Java online submissions for Lowest Common Ancestor of a Binary Tree.

  The video to explain this code is here: [a link will live here someday]
*/

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  return search(root, p, q);
}

public TreeNode search(TreeNode root, TreeNode p, TreeNode q) {

  if (root == null) return null;
  if (root.val == p.val || root.val == q.val) return root;
  
  TreeNode leftSearchResult = search(root.left, p, q);
  TreeNode rightSearchResult = search(root.right, p, q);
  
  if (leftSearchResult == null) return rightSearchResult;
  if (rightSearchResult == null) return leftSearchResult;
  
  return root;
}
