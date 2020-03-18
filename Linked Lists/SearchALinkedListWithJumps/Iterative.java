/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void setJumpOrder(ListNode head) {
    /*
      We can model the Depth First Search with our own stack
      (instead of the call stack).
    */
    Stack<ListNode> stack = new Stack<>();

    int currentOrder = 0;
    stack.push(head);

    while (!stack.isEmpty()) {
      ListNode node = stack.pop();

      if (node != null && node.order == -1) {
        node.order = currentOrder;

        currentOrder += 1;

        // Priority goes to the jump node, we push it last.
        // It will be popped first next iteration to be searched on.
        stack.push(node.next);
        stack.push(node.jump);
      }
    }
  }

  private class ListNode {
    int order;
    ListNode next;
    ListNode jump;

    ListNode() {
      this.order = -1;
    }
  }
}
