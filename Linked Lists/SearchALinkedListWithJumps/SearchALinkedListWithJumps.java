import java.util.List;
import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;

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

    setJumpOrderRecursive(a);
    System.out.println("a jump-first order: " + a.order);
    System.out.println("b jump-first order: " + b.order);
    System.out.println("c jump-first order: " + c.order);
    System.out.println("d jump-first order: " + d.order);

    reset(Arrays.asList(a, b, c, d));

    setJumpOrderIterative(a);
    System.out.println("a jump-first order: " + a.order);
    System.out.println("b jump-first order: " + b.order);
    System.out.println("c jump-first order: " + c.order);
    System.out.println("d jump-first order: " + d.order);
  }

  private static void setJumpOrderRecursive(ListNode head) {
    /*
      Since we use the 'Integer' wrapping the 'order' will
      stay in-memory as an object and we can pass it to recursive
      calls
    */
    IntegerWrapper order = new IntegerWrapper(0); // start ordering at 0

    setJumpOrderRecursiveHelper(head, order);
  }

  /*
    A "jump-first" search prioritizes the jump field in search. We go
    deep into searching on the jump field to populate the 'order' field,
    come back, and then go deep into searching on the next field.
  */
  private static void setJumpOrderRecursiveHelper(ListNode node, IntegerWrapper currentOrder) {
    if (node == null || node.order != -1) {
      return;
    }

    node.order = currentOrder.value;

    // Increment the counter
    currentOrder.value += 1;

    // First we recurse deeply into the 'jump' pointer
    setJumpOrderRecursiveHelper(node.jump , currentOrder);

    // Then we recurse deeply into the 'next' pointer
    setJumpOrderRecursiveHelper(node.next, currentOrder);
  }

  private static void setJumpOrderIterative(ListNode head) {
    /*
      We can model the Depth First Search with our own stack
      (instead of the call stack).
    */
    Stack<ListNode> stack = new Stack<>();

    int currentOrder = 0;
    stack.push(head);

    /*
      Continue the search while there are nodes to search
    */
    while (!stack.isEmpty()) {
      ListNode node = stack.pop();

      if (node != null && node.order == -1) {
        node.order = currentOrder;

        currentOrder += 1;

        // Priority goes to the jump node, we push it last.
        // It will be popped first next iteration to be searched on.
        stack.push(node.next);
        stack.push(node.jump);
      }
    }
  }

  private static void reset(List<ListNode> nodes) {
    System.out.println("\nResetting nodes.\n");

    for (ListNode node: nodes) {
      node.order = -1;
    }
  }

  private static class ListNode {
    int order;
    ListNode next;
    ListNode jump;

    ListNode() {
      this.order = -1;
    }
  }

  private static class IntegerWrapper {
    int value;

    IntegerWrapper(int value) {
      this.value = value;
    }
  }

}
