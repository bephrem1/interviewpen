/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode mergeSort(ListNode head) {
    /*
      Base case, an empty list or a single item list. That is a sorted list,
      hence we just return the list (it is either empty or only has 1 item)
    */
    if (head == null || head.next == null) {
      return head;
    }

    // Abstracting out finding the middle node
    ListNode mid = getMiddleAndSplitInHalf(head);

    /*
      Sort the left. Sort the right. This is recursive splitting and handing
      responsibility off
    */
    ListNode leftHalf = mergeSort(head);
    ListNode rightHalf = mergeSort(mid);

    // Merge the sorted left half and sorted right half
    return merge(leftHalf, rightHalf);
  }

  private ListNode merge(ListNode l1Pointer, ListNode l2Pointer) {
    ListNode dummyHead = new ListNode(0);
    ListNode endOfSortedList = dummyHead;

    // While neither list has been exhausted keep doing comparisons and rewirings
    while (l1Pointer != null && l2Pointer != null) {
      if (l1Pointer.val < l2Pointer.val) {
        // Where l1 points gets the placement
        endOfSortedList.next = l1Pointer;
        l1Pointer = l1Pointer.next;
      } else {
        // Where l2 points gets the placement
        endOfSortedList.next = l2Pointer;
        l2Pointer = l2Pointer.next;
      }

      /*
        The 'endOfSortedList' is now the item we just tacked to
        the end, move the pointer there
      */
      endOfSortedList = endOfSortedList.next;
    }

    // If we exhaust one list, just tack the other to the end of the sorted list
    if (l1Pointer != null) {
      endOfSortedList.next = l1Pointer;
    }

    if (l2Pointer != null) {
      endOfSortedList.next = l2Pointer;
    }

    /*
      The head of the merged list is the .next of the dummy head, the dummy head
      helped us protect against the empty state the list was in to start
    */
    return dummyHead.next;
  }

  // Get the middle node and split the linked list in half
  private ListNode getMiddleAndSplitInHalf(ListNode head) {
    ListNode prev = null;
    ListNode slow = head;
    ListNode fast = head;

    /*
      slow pointer, 1 hop per iteration
      fast pointer, 2 hops per iteration

      When 'fast' reaches the last element or runs over the list the 'slow'
      pointer will point to the middle of the list
    */
    while (fast != null && fast.next != null) {
      // Keep prev 1 behind where slow will be. We want this for later
      prev = slow;

      // Move the slow and fast pointers
      slow = slow.next;
      fast = fast.next.next;
    }

    /*
      Cut off the end of the first half list so it is no longer connected
      in memory to the right half list head

      We kept track of prev to be able to do this cutoff
    */
    prev.next = null;

    // 'slow' sits at the middle of the list
    return slow;
  }
}
