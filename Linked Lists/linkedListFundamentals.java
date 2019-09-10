import java.util.*;

public class LinkedListFundamentals {
  public static void main(String args[]) {
    ListNode a = new ListNode(0);
    ListNode b = new ListNode(1);
    ListNode c = new ListNode(2);
    ListNode d = new ListNode(3);

    a.next = b;
    b.next = c;
    c.next = d;
    // d.next is null by default - reference default to null if uninitialized

    // 'a' is the head of the list - this is a useless pointer...just for renaming
    ListNode head = a;

    System.out.println("printListIterative");
    printListIterative(head);
    newline();

    System.out.println("printListRecursive");
    printListRecursive(head);
    newline();

    System.out.println("printListInReverseRecursive");
    printListInReverseRecursive(head);
    newline();

    System.out.println("printListInReverseIterative");
    printListInReverseIterative(head);
    newline();
  }

  private static void printListIterative(ListNode head) {
    /*
      Common pattern. Create a reference variable to point to the
      head node so that we can operate on the linked list
    */
    ListNode workingPointer = head;

    while (workingPointer != null) {
      /*
        Our work - this can be anything we want to do to 'node'. 'workingPointer'
        literally points to an object on the heap that is a 'ListNode'
      */
      System.out.println(workingPointer.value);

      workingPointer = workingPointer.next; // go to the next node in the list
    }
  }

  private static void printListRecursive(ListNode node) {
    if (node == null) {
      return;
    }

    // Our work - this can be anything we want to do to 'node'
    System.out.println(node.value);

    // Continue the recursion
    printListRecursive(node.next);
  }

  private static void printListInReverseRecursive(ListNode node) {
    if (node == null) {
      return;
    }

    /*
      Recursion first - drill down to the end of the list. Base
      case will catch us.
    */
    printListInReverseRecursive(node.next);

    /*
      Our work. This will be fired by the last node first, then
      the 2nd to last node, and so on...because we recursed first,
      got a ton of nodes on the stack in recursive calls, and now
      they will run this as the recursion goes "back up"
    */
    System.out.println(node.value);
  }

  private static void printListInReverseIterative(ListNode head) {
    /*
      Replace call stack with our own stack
    */
    Stack<ListNode> stack = new Stack<>();

    ListNode workingPointer = head;
    while (workingPointer != null) {
      stack.push(workingPointer);
      workingPointer = workingPointer.next;
    }

    while (!stack.isEmpty()) {
      ListNode node = stack.pop();
      System.out.println(node.value);
    }
  }

  private static void newline() {
    System.out.println();
  }

  /*
    Each node is an object on the heap. Each object points
    to more objects, creating a conceptual list.
  */
  private static class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
      this.value = value;
    }
  }
}
