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
