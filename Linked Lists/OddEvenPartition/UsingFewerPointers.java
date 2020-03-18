/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode even = head;
    ListNode odd = head.next;

    ListNode oddHead = odd;

    while (odd != null && odd.next != null) {
      even.next = odd.next;
      even = odd.next;
      odd.next = even.next;
      odd = even.next;
    }
    even.next = oddHead;

    return head;
  }
}
