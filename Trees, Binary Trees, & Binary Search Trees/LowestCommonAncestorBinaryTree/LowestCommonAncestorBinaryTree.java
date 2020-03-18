/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    /*
     * Our base cases. How our recursion stops. When we have an answer.
     * 
     * 1.) If the node we are holding is null then we can't search...just return
     * null 2.) If we find either value return ourselves to the caller
     * 
     * Remember, we are "grabbing"/"holding" 'root' in this call.
     */
    if (root == null)
      return null;
    if (root.val == p.val || root.val == q.val)
      return root;

    /*
     * 'root' doesn't satisfy any of our base cases.
     * 
     * Search left and then search right.
     */
    TreeNode leftSearchResult = search(root.left, p, q);
    TreeNode rightSearchResult = search(root.right, p, q);

    /*
     * If nothing turned up on the left, return whatever we got back on the right.
     */
    if (leftSearchResult == null)
      return rightSearchResult;

    /*
     * If nothing turned up on the right, return whatever we got back on the left.
     */
    if (rightSearchResult == null)
      return leftSearchResult;

    /*
     * If we reach here that means we got something back on the left AND
     * right...that means this node is the LCA...because our recursion returns from
     * bottom to up...so we return what we hold...'root'
     */
    return root;
  }
}
