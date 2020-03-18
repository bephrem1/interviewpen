/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> inorderTraversal = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    /*
      The inorder traversal investigates: left node right
      
      So:
        1.) Left subtree
        2.) The node itself
        3.) Left subtree
      
      1.) We drill down to the left, the stack keeps a history of nodes
      we have touched that need searching.

      2.) When we cannot go left anymore, we pull the most recent visited
      node (the stack keeps track of this for us) and process it. In this
      case our work is just adding the item to the list.

      3.) We then set 'curr' to be the right subtree, the same while loop
      iteration will run & steps 1-3 will repeat. It mimics the recursion.

      This continues while there are past nodes seen in our "history"
      stack that need processing or curr has a value that we can see
      more nodes from.
    */
    TreeNode curr = root;
    while (!stack.isEmpty() || curr != null) {
      /*
        Left: go as left as possible, the stack keeps the history of
        nodes that need searching
      */
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }

      // Node: curr is null now, investigate the node on the top of the stack
      curr = stack.pop();
      inorderTraversal.add(curr.val);

      // Right: now do this same routine on this node's right subtree
      curr = curr.right;
    }

    return inorderTraversal;
  }
}
