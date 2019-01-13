/*
  Authorship: ALL credit for the iterative code in this file goes to the
  authors of the book "Elements of Programming Interviews" by Adnan Aziz,
  Amit Prakash, and Tsung-Hsien Lee.
  
  I have just adapted the solution to pass on Leetcode, added explanatory
  comments, reformatted the code, & changed variable names for understanding.

  Merge Two Sorted Lists - LeetCode: https://leetcode.com/problems/merge-two-sorted-lists

  The video to explain this code is here: [a link will live here someday]
*/

/*
  This code passes all Leetcode test cases as of Jan. 13 2019
  Runtime: 12 ms, faster than 75.73% of Java online submissions for Merge Two Sorted Lists.
*/
class MergeIterative {

  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

    /*
      DUMMY HEADS ARE VERY HELPFUL FOR LINKED LIST PROBLEMS. Probably the biggest tip
      I can give for doing linked list problems. They help us avoid the empty state
      case and we can just immediately get to building our new list without worrying
      about it having no nodes yet
    */
    ListNode dummyHead = new ListNode(0);

    /*
      Point our current pointer to the dummy head, we will append to the current pointer
      and since we used a dummy head, we do not need to worry about current pointing
      to nothing initially
    */
    ListNode current = dummyHead;

    /*
      We set pointers to the head of list 1 and list 2, we will work off of these pointers
      to advance the iteration
    */
    ListNode p1 = l1, p2 = l2;

    /*
      While neither list has been exhausted, we will continue traversal and comparisons
    */
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

/*
  This code passes all Leetcode test cases as of Jan. 13 2019
  Runtime: 12 ms, faster than 75.73% of Java online submissions for Merge Two Sorted Lists.
*/
class MergeRecursive {

  public ListNode mergeTwoLists(ListNode l1, ListNode l2){

    /*
      l1 has been exhausted, return where we are at in l2
    */
    if(l1 == null) {
      return l2;
    }

    /*
      l2 has been exhausted, return where we are at in l1
    */
    if(l2 == null) {
      return l1;
    }

    if(l1.val < l2.val){

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
