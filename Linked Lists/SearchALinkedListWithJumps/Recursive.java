/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void setJumpOrder(ListNode head) {
    IntegerWrapper order = new IntegerWrapper(0); // start ordering at 0

    setJumpOrderRecursiveHelper(head, order);
  }

  /*
    A "jump-first" search prioritizes the jump field in search. We go
    deep into searching on the jump field to populate the 'order' field,
    come back, and then go deep into searching on the next field.
  */
  private void setJumpOrderRecursiveHelper(ListNode node, IntegerWrapper currentOrder) {
    if (node == null || node.order != -1) {
      return;
    }

    node.order = currentOrder.value;

    // Increment the counter
    currentOrder.value += 1;

    // First we recurse deeply into the 'jump' pointer
    setJumpOrderRecursiveHelper(node.jump , currentOrder);

    // Then we recurse deeply into the 'next' pointer
    setJumpOrderRecursiveHelper(node.next, currentOrder);
  }

  private class ListNode {
    int order;
    ListNode next;
    ListNode jump;

    ListNode() {
      this.order = -1;
    }
  }

  private class IntegerWrapper {
    int value;

    IntegerWrapper(int value) {
      this.value = value;
    }
  }
}
