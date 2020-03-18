/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public TreeNode inorderSuccessor(TreeNode node) {
    TreeNode searchPointer = node;

    // If we can go left, we want to jump to the right node and go as left as possible
    if (searchPointer.right != null) {
      searchPointer = searchPointer.right;

      // Go as left as possible
      while (searchPointer.left != null) {
        searchPointer = searchPointer.left;
      }
      
      return searchPointer;
    }

    /*
      We could not go right, so we must "return" to our previous "calling nodes"
      going up the tree with the parent pointers. We can stop when have come up
      from a node's left subtree.

      This is because every node we came from has already visited itself (left node right)
      and we are in the right subtree of some node. If we got to the furthest right node
      in an inorder search on a subtree, then the inorder traversal is finished for that
      subtree.

      We want to "escape" our position. And the way we do that is crawling back up the
      tree with the parent pointers.

      We keep doing this while we are the right child of the parent, that node already
      visited itself and exhausted it's right subtree (left node right)...we must continue
      up the tree.

      When we are a left child of a parent node then we know that the parent is next to
      search.
      
      This is because the parent searched it's whole left subtree...left node right...the
      parent is next.
    */
    while (searchPointer.parent != null && searchPointer.parent.right == searchPointer) {
      searchPointer = searchPointer.parent;
    }

    /*
      If we return null, then we were given reference to the last node in the inorder
      traversal. We will climb up and up and up and never be the left child of a parent.
    */
    return searchPointer.parent;
  }

  private class TreeNode {
    String val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    TreeNode(String x) { val = x; }
  }
}
