/*
  Intersection of Two Linked Lists - LeetCode: https://leetcode.com/problems/intersection-of-two-linked-lists/
  This code passes all Leetcode test cases as of Oct. 2nd 2019
*/

class BruteForce {
  public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
    Set<ListNode> firstListNodes = new HashSet<>();

    ListNode curr = l1;
    while (curr != null) {
      firstListNodes.add(curr);
      curr = curr.next;
    }

    ListNode curr2 = l2;
    while (curr2 != null) {
      if (firstListNodes.contains(curr2)) {
        return curr2;
      }

      curr2 = curr2.next;
    }

    return null;
  }
}

class ConstantSpace {
  public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return null;
    }

    int l1Length = length(l1);
    int l2Length = length(l2);

    if (l1Length > l2Length) {
      l1 = advanceReferenceBy(l1Length - l2Length, l1);
    } else {
      l2 = advanceReferenceBy(l2Length - l1Length, l2);
    }

    while (l1 != null && l2 != null && l1 != l2) {
      l1 = l1.next;
      l2 = l2.next;
    }

    return l1;
  }

  private ListNode advanceReferenceBy(int distanceToGo, ListNode head) {
    while (distanceToGo > 0) {
      head = head.next;
      distanceToGo--;
    }

    return head;
  }

  private int length(ListNode head) {
    if (head == null) {
      return 0;
    }
  
    int length = 1;
    ListNode curr = head;
    while (curr != null) {
      length++;
      curr = curr.next;
    }

    return length;
  }
}
