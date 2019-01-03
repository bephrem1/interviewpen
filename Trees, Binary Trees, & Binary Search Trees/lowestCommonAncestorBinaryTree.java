/**
  @author Benyam Ephrem

  This code passes all Leetcode test cases as of Jan. 1st 2019
  Runtime: 14 ms, faster than 23.21% of Java online submissions* for
  Lowest Common Ancestor of a Binary Tree.

  This is the recursion approach and has the same runtime as the
  accepted recursion approach on Leetcode's "solution" section.

  Complexities:
    # Let n be the total nodes in the binary tree.
  Time:
    - O(n) worst case if we have to touch all n nodes in our search
  Space:
    - Stack frames will take up space through the recursion
    - O(h) best case if we have a balanced binary tree
    - O(n) worst case if we have a purely left or right skewed tree
      and have to find the LCA of two nodes at the bottom of the skewed
      tree (and technically in this worst case n IS h but we say n because
      it makes more sense and is more accurate to what is happening)

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
  
  if (leftSearchResult == null) {
      return rightSearchResult;
  }
  
  if (rightSearchResult == null) {
      return leftSearchResult;
  }
  
  return root;
}
