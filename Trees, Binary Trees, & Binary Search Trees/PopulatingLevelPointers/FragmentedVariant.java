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
      if (root.right != null) {
        root.left.next = root.right;
      } else {
        root.left.next = findNextNode(root.next);
      }
    }

    if (root.right != null) {
      root.right.next = findNextNode(root.next);
    }

    root.right = connect(root.right);
    root.left = connect(root.left);

    return root;
  }

  private Node findNextNode(Node node) {
    if (node == null) {
      return null;
    }

    if (node.left != null) {
      return node.left;
    }

    if (node.right != null) {
      return node.right;
    }

    return findNextNode(node.next);
  }
}
