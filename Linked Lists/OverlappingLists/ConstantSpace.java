/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return null;
    }

    int l1Length = length(l1);
    int l2Length = length(l2);

    if (l1Length > l2Length) {
      l1 = advanceReferenceBy(l1Length - l2Length, l1);
    } else {
      l2 = advanceReferenceBy(l2Length - l1Length, l2);
    }

    while (l1 != null && l2 != null && l1 != l2) {
      l1 = l1.next;
      l2 = l2.next;
    }

    return l1;
  }

  private ListNode advanceReferenceBy(int distanceToGo, ListNode head) {
    while (distanceToGo > 0) {
      head = head.next;
      distanceToGo--;
    }

    return head;
  }

  private int length(ListNode head) {
    if (head == null) {
      return 0;
    }

    int length = 1;
    ListNode curr = head;
    while (curr != null) {
      length++;
      curr = curr.next;
    }

    return length;
  }
}
