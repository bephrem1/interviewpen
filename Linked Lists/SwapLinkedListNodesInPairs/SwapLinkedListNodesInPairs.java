/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode swapInPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode first = head;
    ListNode second = head.next;
    ListNode startOfNextSegment = null;

    // Move the head of the list to the 2nd node in the list
    head = head.next;

    while (true) {
      startOfNextSegment = second.next;
      second.next = first;

      if (startOfNextSegment == null || startOfNextSegment.next == null) {
        first.next = startOfNextSegment;
        return head;
      }

      first.next = startOfNextSegment.next;

      first = startOfNextSegment;
      second = startOfNextSegment.next;
    }
  }
}
