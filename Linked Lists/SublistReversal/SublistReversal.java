/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode reverseBetween(ListNode head, int start, int end) {
    if (start == end) {
      return head;
    }

    // Pad a dummy head to the start of the list
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    /*
      Create a working pointer to advance to right before the sublist head:

      pos = 1
      dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
          ^
      
      pos = 2
      dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                  ^
      break, pos == start
    */
    ListNode nodeBeforeReversedSublist = dummyHead;
    int pos = 1;
    while (pos < start) {
      nodeBeforeReversedSublist = nodeBeforeReversedSublist.next;
      pos++;
    }

    /*
      Reverse the actual sublist:

      start:
        dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                    nbrs swp
      
      1st iteration (start = 2):
        1.) Cut out of sublist
          dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                      nbrs swp nctsf
          
          dummyHead -> 1 -> 2 -> 4 -> 5 -> x
                      nbrs swp   ^
                              3--|
                            nctsf

        2.) Wire into sublist head
          dummyHead -> 1 -> 2 -> 4 -> 5 -> x
                      nbrs swp
                            ^ 
                        3--|
                      nctsf

          dummyHead -> 1 -> 3 -> 2 -> 4 -> 5 -> x
                      nbrs      swp
        
      2nd iteration (start = 3):
        1.) Cut out of sublist
          dummyHead -> 1 -> 3 -> 2 -> 4 -> 5 -> x
                    nbrs       swp nctsf
          
          dummyHead -> 1 -> 3 -> 2 -> 5 -> x
                    nbrs       swp   ^
                                  4--|
                                nctsf
        
        2.) Wire into sublist head
          dummyHead -> 1 -> 3 -> 2 -> 5 -> x
                    nbrs   ^   swp
                        4--|
                      nctsf

          dummyHead -> 1 -> 4 -> 3 -> 2 -> 5 -> x
                    nbrs            swp
      
      3rd Iteration (start = 4), break while loop
    */
    ListNode sublistWorkingPtr = nodeBeforeReversedSublist.next;
    while (start < end) {
      // 1.) Cut out of sublist
      ListNode nodeComingToSublistFront = sublistWorkingPtr.next;
      sublistWorkingPtr.next = nodeComingToSublistFront.next;

      // 2.) Wire into sublist head
      nodeComingToSublistFront.next = nodeBeforeReversedSublist.next;
      nodeBeforeReversedSublist.next = nodeComingToSublistFront;

      start++;
    }

    return dummyHead.next;
  }
}
