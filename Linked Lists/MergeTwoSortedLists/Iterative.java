/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode current = dummyHead;

    ListNode p1 = l1, p2 = l2;

    while (p1 != null && p2 != null) {
      /*
        The node p1 points to gets the placement. Advance where
        p1 points to in list 1 (remember p1 points into list 1)
      */
      if (p1.val <= p2.val) {
        current.next = p1;
        p1 = p1.next;
      } else {
        /*
          The node p2 points to gets the placement. Advance where
          p2 points to in list 2 (remember p2 points into list 2)
        */
        current.next = p2;
        p2 = p2.next;
      }

      /*
        Advance current to point to the node that we just place, we
        will continue our building from there
      */
      current = current.next;
    }

    /*
      If we exhausted either list we just append the other list to the
      end of the merged list since the list still standing will have all
      elements greater than the last item in the merged list so far (and
      be in sorted order)
    */
    current.next = p1 != null ? p1 : p2;

    /*
      We just return dummyHead's next, the new list we built off of it from
      rearranging pointers
    */
    return dummyHead.next;
  }
}
