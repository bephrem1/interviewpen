class Solution {
  public void printList(ListNode head) {
    /*
      Common pattern of creating a reference variable to point to the
      head node so that we can operate on the linked list
    */
    ListNode workingPointer = head;

    while (workingPointer != null) {
      /*
        Our work - this can be anything we want to do to 'node'. 'workingPointer'
        literally points to an object on the heap that is a 'ListNode'
      */
      System.out.println(workingPointer.value);

      workingPointer = workingPointer.next; // go to the next node in the list
    }
  }
}
