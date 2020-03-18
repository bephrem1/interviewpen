/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  private static ListNode getKthToLastNodeReference(ListNode head, int k) {
    ListNode leftOfWindow = head;
    ListNode rightOfWindow = head;

    /*
      Move the right of the window so that the gap between the 2 references is length 'k'

      Imagine k = 3, we want:
      O -> O -> O -> O -> O -> O -> x
                     ^

      We do:
      O -> O -> O -> O -> O -> O -> x
      l
      r

      O -> O -> O -> O -> O -> O -> x
      l
                     r
    */
    for (int i = 0; i < k; i++) {
      rightOfWindow = rightOfWindow.next;
    }

    /*
      Now we do:
      O -> O -> O -> O -> O -> O -> x
      l
                     r

      O -> O -> O -> O -> O -> O -> x
           l
                          r

      O -> O -> O -> O -> O -> O -> x
                l
                               r

      O -> O -> O -> O -> O -> O -> x
                     l
                                    r

      And we have found the kth to last node
    */
    while (rightOfWindow != null) {
      leftOfWindow = leftOfWindow.next;
      rightOfWindow = rightOfWindow.next;
    }

    // Useless pointer to rename for teaching purposes
    ListNode kthToLastNode = leftOfWindow;

    return kthToLastNode;
  }
}
