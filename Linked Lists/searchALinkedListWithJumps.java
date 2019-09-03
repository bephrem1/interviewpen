public class SearchALinkedListWithJumps {
  public static void main(String args[]) {
    ListNode a = new ListNode();
    ListNode b = new ListNode();
    ListNode c = new ListNode();
    ListNode d = new ListNode();

    // Regular next pointers
    a.next = b;
    b.next = c;
    c.next = d;

    // a jumps to c, c jumps to b, b jumps to d, and d cycles on itself
    a.jump = c;
    b.jump = d;
    c.jump = b;
    d.jump = d;

    setJumpOrder(a);

    System.out.println("a jump-first order: " + a.order);
    System.out.println("b jump-first order: " + b.order);
    System.out.println("c jump-first order: " + c.order);
    System.out.println("d jump-first order: " + d.order);
  }

  private static void setJumpOrder(ListNode head) {
    /*
      Since we use the 'Integer' wrapping the 'order' will
      stay in-memory as an object and we can pass it to recursive
      calls
    */
    Integer order = 0; // start ordering at 0

    setJumpOrderHelper(head, order);
  }

  /*
    A "jump-first" search prioritizes the jump field in search. We go
    deep into searching on the jump field to populate the 'order' field,
    come back, and then go deep into searching on the next field.
  */
  private static void setJumpOrderHelper(ListNode node, Integer currentOrder) {
    if (node == null || node.order != -1) {
      return;
    }

    node.order = currentOrder;

    // Increment the counter
    currentOrder += 1;

    // First we recurse deeply into the 'jump' pointer
    setJumpOrderHelper(node.jump , currentOrder);

    // Then we recurse deeply into the 'next' pointer
    setJumpOrderHelper(node.next, currentOrder);
  }

  private static class ListNode {
    int order;
    ListNode next;
    ListNode jump;

    ListNode() {
      this.order = -1;
    }
  }
}
