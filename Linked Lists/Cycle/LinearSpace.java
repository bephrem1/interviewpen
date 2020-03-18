/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean hasCycle(ListNode head) {
    Set<ListNode> seen = new HashSet<>();
    
    ListNode curr = head;
    while (curr != null) {
      if (seen.contains(curr)) {
        return true;
      }

      seen.add(curr);
      curr = curr.next;
    }
    
    return false;
  }
}
