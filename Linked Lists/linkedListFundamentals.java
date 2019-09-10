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

    System.out.println("deepCopyListIterative");
    ListNode newListHead = deepCopyListIterative(head);
    printListIterative(newListHead);
    System.out.println("New list head value: " + newListHead.value);
    System.out.println("Are the 2 list head's equivalent in memory?: " + (a == newListHead));
    newline();

    System.out.println("reverseLinkedListIterative");
    ListNode reversedListHead1 = reverseLinkedListIterative(head);
    printListIterative(reversedListHead1);
    newline();

    /*
      Restore the original list by reversing again...it is sitting reversed
      in memory...we have to restore it
    */
    reverseLinkedListIterative(reversedListHead1);

    System.out.println("reverseLinkedListRecursive");
    ListNode reversedListHead2 = reverseLinkedListRecursive(head);
    printListIterative(reversedListHead2);
    newline();

    // Restore
    reverseLinkedListIterative(reversedListHead2);

    System.out.println("getMiddleNodeReference");
    ListNode middle = getMiddleNodeReference(head);
    System.out.println("Middle node value: " + middle.value);
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

  private static ListNode deepCopyListIterative(ListNode head) {
    /*
      A "dummy head" node allows us to not have to handle the
      initial empty case of a new list not being initialized
      with a first node.

      We can immediately begin appending to this new list and
      "chop off" this node at the end and it will be garbage
      collected (probably, if no one is using the reference later).
    */
    ListNode newListHead = new ListNode(-1);

    /*
      Point a working pointer to the old list, point a working pointer
      to the dummy head.

      We will build off the 'next' field of the dummy head immediately.
    */
    ListNode oldListWorkingPointer = head;
    ListNode newListWorkingPointer = newListHead;

    while (oldListWorkingPointer != null) {
      ListNode newNode = new ListNode(oldListWorkingPointer.value);

      // Append the deep copied node to the new list we are building
      newListWorkingPointer.next = newNode;

      // Advance to the next node in the old list to process
      oldListWorkingPointer = oldListWorkingPointer.next;

      // Move to the new node we just appended
      newListWorkingPointer = newListWorkingPointer.next;

      /*
        We can also do the following, same reference to the same
        node (the newly appended node):
      */
      // newListWorkingPointer = newNode;
    }

    // "Cut off" the dummy head just leaving the pure new list created
    return newListHead.next;
  }

  private static ListNode reverseLinkedListIterative(ListNode head) {
    /*
      Common pattern. We keep 2 pointers, 1 to the previous element and 1
      to the current element
    */
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      /*
        Another common pattern - preserve the pointer to the next node in
        the list since we will overwrite this value later.

        Literally preserving the pointer to the next object in memory that
        we want to visit...in a reference variable.
      */
      ListNode preservedNextNode = curr.next;

      curr.next = prev;

      prev = curr;
      curr = preservedNextNode;
    }

    /*
      Useless pointer to rename things to make it clear what is happening,
      prev is pointing to the last element when the above iteration ends.

      That is the head of the new reversed linked list.
    */
    ListNode reversedLinkedListHead = prev;

    return reversedLinkedListHead;
  }

  private static ListNode reverseLinkedListRecursive(ListNode me) {
    if (me == null || me.next == null) {
      return me;
    }

    /*
      We need to preserve the reference to the last node in the original
      linked list since it will be the head of the new reversed linked list.

      We "bubble" that value up in our calls to the root caller by recursing
      deep first to get reference to the last node (and in the process creating
      n stack frames on the call stack, each with reference to a node)
    */
    ListNode headOfReversedList = reverseLinkedListRecursive(me.next);
    
    /*
      When the base case is hit, we will be here:

      O -> O -> O -> O -> O -> O -> O -> x
                               ^
                               me
      
      In that frame we will have reference to a node 1 before
      the end of the linked list. We point the node 'nodeToMyRight'
      to 'me'.

      Then we cut off 'me's next because 'me' may be the last node in
      the reversed linked list
    */
    ListNode nodeToMyRight = me.next;
    nodeToMyRight.next = me;
    me.next = null;

    // We continue "bubbling up" reference to the tail node up and up and up
    return headOfReversedList;
  }

  private static ListNode getMiddleNodeReference(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    /*
      Even
      O -> O -> O -> O -> O -> O -> x
      ^
      ^

      O -> O -> O -> O -> O -> O -> x
           ^
                ^

      O -> O -> O -> O -> O -> O -> x
                     ^
                                    ^

      Odd
      O -> O -> O -> O -> O -> x
      ^
      ^

      O -> O -> O -> O -> O -> x
           ^
                ^

      O -> O -> O -> O -> O -> x
                ^
                          ^
      
      Keep going until 'fast' is null or 'fast.next' is null.
      Then return the reference;
    */
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // Useless pointer to rename for teaching purposes
    ListNode middleNode = slow;

    return middleNode;
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
