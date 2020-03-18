/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode reverseLinkedList(ListNode head) {
    /*
      We keep 2 pointers, 1 to the previous element and 1 to the =
      current element
    */
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      /*
        Another common pattern - preserve the pointer to the next node in
        the list since we will overwrite this value later.

        Literally preserving the pointer to the next object in memory that
        we want to visit...in a reference variable.
      */
      ListNode preservedNextNode = curr.next;

      curr.next = prev;

      prev = curr;
      curr = preservedNextNode;
    }

    /*
      Useless pointer to rename things to make it clear what is happening,
      prev is pointing to the last element when the above iteration ends.

      That is the head of the new reversed linked list.
    */
    ListNode reversedLinkedListHead = prev;

    return reversedLinkedListHead;
  }
}
