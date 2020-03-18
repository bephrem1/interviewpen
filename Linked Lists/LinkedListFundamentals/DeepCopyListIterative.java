/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode deepCopyList(ListNode head) {
    /*
      A "dummy head" node allows us to not have to handle the
      initial empty case of a new list not being initialized
      with a first node.

      We can immediately begin appending to this new list and
      "chop off" this node at the end and it will be garbage
      collected (probably, if no one is using the reference later).
    */
    ListNode newListHead = new ListNode(-1);

    /*
      Point a working pointer to the old list, point a working pointer
      to the dummy head.

      We will build off the 'next' field of the dummy head immediately.
    */
    ListNode oldListWorkingPointer = head;
    ListNode newListWorkingPointer = newListHead;

    while (oldListWorkingPointer != null) {
      ListNode newNode = new ListNode(oldListWorkingPointer.value);

      // Append the deep copied node to the new list we are building
      newListWorkingPointer.next = newNode;

      // Advance to the next node in the old list to process
      oldListWorkingPointer = oldListWorkingPointer.next;

      // Move to the new node we just appended
      newListWorkingPointer = newListWorkingPointer.next;

      /*
        We can also do the following, same reference to the same
        node (the newly appended node):
      */
      // newListWorkingPointer = newNode;
    }

    // "Cut off" the dummy head just leaving the pure new list created
    return newListHead.next;
  }
}
