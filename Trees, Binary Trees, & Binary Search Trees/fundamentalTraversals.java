/**
  @author Benyam Ephrem

  These are the 3 fundamental tree traversals all done recursively.

  Very basic, very straightforward. These can be done iteratively
  as well as in O(1) space, but then they get trickier to implement
  but don't worry. If you can grasp these, you can grasp those.
*/

private class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}

/*
  n    l    r
  ode  eft  ight
*/
public void printPreorder(TreeNode root) {
  if (root == null) { return; }
  System.out.println(root.val);
  printPreorder(root.left);
  printPreorder(root.right);
}

/*
  l    n    r
  eft  ode  ight
*/
public void printInorder(TreeNode root) {
  if (root == null) { return; }
  printInorder(root.left);
  System.out.println(root.val);
  printInorder(root.right);
}

/*
  l    r     n
  eft  ight  ode
*/
public void printPostorder(TreeNode root) {
  if (root == null) { return; }
  printPostorder(root.left);
  printPostorder(root.right);
  System.out.println(root.val);
}
