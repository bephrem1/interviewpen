class Solution {
  private class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  }

  /*
   * n ode l eft r ight
   */
  public void printPreorder(TreeNode root) {
    if (root == null) {
      return;
    }
    System.out.println(root.val);
    printPreorder(root.left);
    printPreorder(root.right);
  }

  /*
   * l eft n ode r ight
   */
  public void printInorder(TreeNode root) {
    if (root == null) {
      return;
    }
    printInorder(root.left);
    System.out.println(root.val);
    printInorder(root.right);
  }

  /*
   * l eft r ight n ode
   */
  public void printPostorder(TreeNode root) {
    if (root == null) {
      return;
    }
    printPostorder(root.left);
    printPostorder(root.right);
    System.out.println(root.val);
  }
}
