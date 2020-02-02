class Solution {
  public void printList(ListNode node) {
    if (node == null) {
      return;
    }

    // Our work - this can be anything we want to do to 'node'
    System.out.println(node.value);

    // Continue the recursion
    printList(node.next);
  }
}
