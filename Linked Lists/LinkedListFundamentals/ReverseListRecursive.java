/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  private static ListNode reverseLinkedList(ListNode me) {
    if (me == null || me.next == null) {
      return me;
    }

    /*
      We need to preserve the reference to the last node in the original
      linked list since it will be the head of the new reversed linked list.

      We "bubble" that value up in our calls to the root caller by recursing
      deep first to get reference to the last node (and in the process creating
      n stack frames on the call stack, each with reference to a node)
    */
    ListNode headOfReversedList = reverseLinkedListRecursive(me.next);
    
    /*
      When the base case is hit & returns, we will return to here.
      1 node before the final node in the list:

      O -> O -> O -> O -> O -> O -> O -> x
                               ^
                               me
      
      In that frame we will have reference to a node 1 before
      the end of the linked list. We point the node 'nodeToMyRight'
      to 'me'.

      Then we cut off 'me's next because 'me' may be the last node in
      the reversed linked list
    */
    ListNode nodeToMyRight = me.next;
    nodeToMyRight.next = me;
    me.next = null;

    // We continue "bubbling up" reference to the tail node up and up and up
    return headOfReversedList;
  }
}
