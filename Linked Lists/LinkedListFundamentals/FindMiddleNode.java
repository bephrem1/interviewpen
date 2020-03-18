/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode getMiddleNodeReference(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    /*
      Even
      O -> O -> O -> O -> O -> O -> x
      ^
      ^

      O -> O -> O -> O -> O -> O -> x
           ^
                ^

      O -> O -> O -> O -> O -> O -> x
                     ^
                                    ^

      Odd
      O -> O -> O -> O -> O -> x
      ^
      ^

      O -> O -> O -> O -> O -> x
           ^
                ^

      O -> O -> O -> O -> O -> x
                ^
                          ^
      
      Keep going until 'fast' is null or 'fast.next' is null.
      Then return the reference;
    */
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // Useless pointer to rename for teaching purposes
    ListNode middleNode = slow;

    return middleNode;
  }
}
