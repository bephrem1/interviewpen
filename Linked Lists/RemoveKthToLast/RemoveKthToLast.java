/*
  Remove Nth Node From End of List - LeetCode: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
  This code passes all Leetcode test cases as of Oct. 1st 2019
*/

public ListNode removeNthFromEnd(ListNode head, int n) {
  ListNode dummyHead = new ListNode(-1);
  dummyHead.next = head;

  ListNode right = dummyHead.next;
  while (n > 0) {
    right = right.next;
    n--;
  }

  ListNode left = dummyHead;
  while (right != null) {
    left = left.next;
    right = right.next;
  }

  left.next = left.next.next;

  return dummyHead.next;
}
