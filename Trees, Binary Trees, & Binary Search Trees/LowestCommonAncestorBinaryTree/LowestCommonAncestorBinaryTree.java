/**
  @author Benyam Ephrem

  Lowest Common Ancestor of a Binary Tree - LeetCode:
  https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree

  This code passes all Leetcode test cases as of Jan. 1st 2019
  Runtime: 5 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
  Memory Usage: 33.9 MB, less than 68.78% of Java online submissions for Lowest Common Ancestor of a Binary Tree.

  The video to explain this code is here: https://www.youtube.com/watch?v=py3R23aAPCA
*/

/*
  Useless driver...I just wanted to rename the function to 'search', can't change Leetcode's
  function name...just ignore this odd choice...I'd never do this in real life
*/
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  return search(root, p, q);
}

private TreeNode search(TreeNode root, TreeNode p, TreeNode q) {

  /*
    Our base cases.
    How our recursion stops.
    When we have an answer.

    1.) If the node we are holding is null then we can't search...just return null
    2.) If we find either value return ourselves to the caller

    Remember, we are "grabbing"/"holding" 'root' in this call.
  */
  if (root == null) return null;
  if (root.val == p.val || root.val == q.val) return root;
  
  /*
    'root' doesn't satisfy any of our base cases.

    Search left and then search right.
  */
  TreeNode leftSearchResult = search(root.left, p, q);
  TreeNode rightSearchResult = search(root.right, p, q);
  
  /*
    If nothing turned up on the left, return whatever we got
    back on the right.
  */
  if (leftSearchResult == null) return rightSearchResult;

  /*
    If nothing turned up on the right, return whatever we got
    back on the left.
  */
  if (rightSearchResult == null) return leftSearchResult;
  
  /*
    If we reach here that means we got something back on the left AND
    right...that means this node is the LCA...because our recursion returns
    from bottom to up...so we return what we hold...'root'
  */
  return root;
}
