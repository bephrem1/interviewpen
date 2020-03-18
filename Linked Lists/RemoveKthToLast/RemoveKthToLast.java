/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    ListNode right = dummyHead.next;
    while (n > 0) {
      right = right.next;
      n--;
    }

    ListNode left = dummyHead;
    while (right != null) {
      left = left.next;
      right = right.next;
    }

    left.next = left.next.next;

    return dummyHead.next;
  }
}
