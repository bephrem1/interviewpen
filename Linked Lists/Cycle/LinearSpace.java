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
