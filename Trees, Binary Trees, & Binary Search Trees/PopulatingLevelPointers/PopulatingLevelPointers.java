class PartI {
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

class PartII {
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
