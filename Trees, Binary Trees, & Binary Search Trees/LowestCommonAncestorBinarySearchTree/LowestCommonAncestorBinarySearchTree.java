class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, int x, int y) {
    /*
     * 1) Both values are less than our root value.
     */
    if (x < root.val && y < root.val)
      return lowestCommonAncestor(root.left, x, y);

    /*
     * 2) Both values are greater than our root value.
     */
    if (x > root.val && y > root.val)
      return lowestCommonAncestor(root.right, x, y);

    /*
     * 3) One of x or y is equal to the root.
     * OR
     * 4) One of x or y is less than root and the other is greater than root.
     */
    return root;
  }
}
