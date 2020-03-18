/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
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
