/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // l1 has been exhausted, return where we are at in l2
    if (l1 == null) {
      return l2;
    }

    // l2 has been exhausted, return where we are at in l1
    if (l2 == null) {
      return l1;
    }

    if (l1.val < l2.val) {
      /*
        l1 gets the placement, we first need to get the rest
        of the lists merged without this l1 node and then we can
        return this node
      */
      l1.next = mergeTwoLists(l1.next, l2);

      return l1;
    } else {
      /*
        l2 gets the placement, we first need to get the rest
        of the lists merged without this l2 node and then we can
        return this node
      */
      l2.next = mergeTwoLists(l1, l2.next);

      return l2;
    }
  }
}
