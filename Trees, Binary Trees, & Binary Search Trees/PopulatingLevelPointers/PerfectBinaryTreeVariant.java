/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public Node connect(Node root) {
    if (root == null) {
      return null;
    }

    if (root.left != null) {
      root.left.next = root.right;
    }

    if (root.right != null && root.next != null) {
      root.right.next = root.next.left;
    }

    root.left = connect(root.left);
    root.right = connect(root.right);

    return root;
  }
}
