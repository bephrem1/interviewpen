/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    /*
      Use the built-in PriorityQueue library, pass a Comparator to make poll() return
      the smallest item instead of the largest item (default is the largest/highest)
      priority item.

      A "Comparator" just tells the structure how to compare individual items to keep
      the queue sorted
    */
    PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
      @Override
      public int compare(ListNode o1, ListNode o2) {
        if (o1.val < o2.val) {
          return -1;
        } else if (o1.val == o2.val) {
          return 0;
        } else {
          return 1;
        }
      }
    });

    // Initialize the variables we need to begin building the sorted list
    ListNode mergedListDummyHead = new ListNode(0);
    ListNode mergedListTail = mergedListDummyHead;
    
    /*
      Add the head of each linked list to the Priority Queue, the queue will
      move the node with the smallest value to the front.
    */
    for (ListNode headOfList: lists) {
      if (headOfList != null) {
        queue.add(headOfList);
      }
    }

    /*
      Process each node in the queue while there are nodes to process across
      all k lists
    */
    while (!queue.isEmpty()) {
      ListNode smallestNode = queue.poll();

      // Append the node to the final list
      mergedListTail.next = smallestNode;

      // Advance the tail pointer to the node that we just added
      mergedListTail = smallestNode;

      /*
        Add the node that is after the node we just appended...to the priority
        queue for consideration (if it is not null)
      */
      if (smallestNode.next != null) {
        queue.add(smallestNode.next);
      }
    }

    return mergedListDummyHead.next;
  }
}
