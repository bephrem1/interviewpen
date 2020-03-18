/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void printListInReverse(ListNode head) {
    // Replace call stack with our own stack
    Stack<ListNode> stack = new Stack<>();

    ListNode workingPointer = head;
    while (workingPointer != null) {
      stack.push(workingPointer);
      workingPointer = workingPointer.next;
    }

    while (!stack.isEmpty()) {
      ListNode node = stack.pop();
      System.out.println(node.value);
    }
  }
}
