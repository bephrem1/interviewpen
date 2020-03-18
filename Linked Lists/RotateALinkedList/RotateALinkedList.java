/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      return head;
    }

    // Find the tail and list length (so we can adjust k)
    ListNode tail = head;
    int size = 1;
    while (tail.next != null) {
      size++;
      tail = tail.next;
    }

    // Normalize k
    k %= size;

    // If k normalizes to 0, we just return the same head
    if (k == 0) {
      return head;
    }

    // Create a cycle we can traverse, we will break this later
    tail.next = head;

    /*
      The distance from the current tail to the "new" rotated
      list's tail will be size - k:

      k = 2
      size = 6
      1 -> 2 -> 3 -> 4 -> 5 -> 6 -> x
                              ^
                              tail

      5 -> 6 -> 1 -> 2 -> 3 -> 4 -> x
                ^              ^
                k           new tail
      
      How far is the tail we have reference to from the
      new rotate list's tail? 4 positions, which is size - k.
    */
    int stepsToNewTail = size - k;
    ListNode rotatedListTail = tail;
    while (stepsToNewTail > 0) {
      rotatedListTail = rotatedListTail.next;
      stepsToNewTail--;
    }

    ListNode rotatedListHead = rotatedListTail.next; // The head we return
    rotatedListTail.next = null; // Cut off cycle

    return rotatedListHead;
  }
}
