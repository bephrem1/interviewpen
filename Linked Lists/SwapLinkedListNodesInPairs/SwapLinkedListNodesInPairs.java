/*
  Swap Linked List Nodes In Pairs:

  Even List:
  a -> b -> c -> d -> e -> f -> x
  b -> a -> d -> c -> f -> e -> x

  Odd List:
  a -> b -> c -> d -> e -> x
  b -> a -> d -> c -> e -> x
*/

public class SwapNodesInPairs {
  public static void main(String args[]) {
    ListNode l1 = generateList(ListType.EVEN);
    swapInPairsLeavePointersAlone(l1);
    print(l1);

    newline();

    ListNode l2 = generateList(ListType.ODD);
    swapInPairsLeavePointersAlone(l2);
    print(l2);

    newline();

    ListNode l3 = generateList(ListType.EVEN);
    ListNode newHead = swapInPairs(l3);
    print(newHead);

    newline();

    ListNode l4 = generateList(ListType.ODD);
    ListNode newHead1 = swapInPairs(l4);
    print(newHead1);
  }

  private static void swapInPairsLeavePointersAlone(ListNode head) {
    ListNode curr = head;

    while (curr != null && curr.next != null) {
      char temp = curr.val;
      curr.val = curr.next.val;
      curr.next.val = temp;
      curr = curr.next.next;
    }
  }

  /*
    We can get to this solution by drawing out the problem & progressively wiring
    pointers and covering edge cases as we step through the problem
  */
  private static ListNode swapInPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode first = head;
    ListNode second = head.next;
    ListNode startOfNextSegment = null;

    // Move the head of the list to the 2nd node in the list
    head = head.next;

    while (true) {
      startOfNextSegment = second.next;
      second.next = first;

      if (startOfNextSegment == null || startOfNextSegment.next == null) {
        first.next = startOfNextSegment;
        return head;
      }

      first.next = startOfNextSegment.next;

      first = startOfNextSegment;
      second = startOfNextSegment.next;
    }
  }

  private static ListNode generateList(ListType type) {
    ListNode a = new ListNode('a');
    ListNode b = new ListNode('b');
    ListNode c = new ListNode('c');
    ListNode d = new ListNode('d');
    ListNode e = new ListNode('e');
    ListNode f = new ListNode('f');

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    
    if (type == ListType.EVEN) {
      e.next = f;
    }

    return a;
  }

  private static void print(ListNode head) {
    ListNode curr = head;

    while (curr != null) {
      System.out.println(curr.val);
      curr = curr.next;
    }
  }

  private static void newline() {
    System.out.println();
  }

  private static class ListNode {
    char val;
    ListNode next;

    ListNode(char val) {
      this.val = val;
    }
  }

  static enum ListType { EVEN, ODD }
}
