/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode ptr1 = l1;
    ListNode ptr2 = l2;

    ListNode dummyHead = new ListNode(0);
    ListNode newListBuildPtr = dummyHead;

    int carry = 0;

    while (ptr1 != null || ptr2 != null) {
      int first = (ptr1 != null) ? ptr1.val : 0;
      int second = (ptr2 != null) ? ptr2.val : 0;

      int sum = carry + first + second;
      carry = sum / 10;

      newListBuildPtr.next = new ListNode(sum % 10);

      if (ptr1 != null) {
        ptr1 = ptr1.next;
      }

      if (ptr2 != null) {
        ptr2 = ptr2.next;
      }

      newListBuildPtr = newListBuildPtr.next;
    }

    // If a carry remains create a node for it
    if (carry > 0) {
      newListBuildPtr.next = new ListNode(carry);
    }

    return dummyHead.next;
  }
}
