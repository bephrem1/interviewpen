/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public class LinkedList {
    ListNode head;
    int size;

    public MyLinkedList() {
      head = new ListNode(); // dummy head
    }

    // O(n)
    public int get(int index) {
      if (index < 0 || index >= this.size) {
        return -1;
      }

      ListNode curr = head.next;
      for (int i = 0; i < index; i++) {
        curr = curr.next;
      }

      return curr.val;
    }

    // O(1)
    public void addAtHead(int val) {
      ListNode newNode = new ListNode(val);

      newNode.next = head.next; // wire new node to first item
      head.next = newNode; // wire dummy head to new node
      size++;
    }

    /*
      O(n) - can be made O(1) if we make the tail node doubly linked
      so it can "look back". This will complicate deletion and insertion
      a bit so for simplicity the linear approach is shown.
    */
    public void addAtTail(int val) {
      ListNode newNode = new ListNode(val);

      ListNode curr = head;
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = newNode;
      size++;
    }

    // O(n)
    public void addAtIndex(int index, int val) {
      if (index < 0 || index > this.size) {
        return;
      }

      ListNode newNode = new ListNode(val);

      ListNode curr = head;
      for (int i = 0; i < index; i++) {
        curr = curr.next;
      }
      newNode.next = curr.next;
      curr.next = newNode;

      size++;
    }

    // O(n)
    public void deleteAtIndex(int index) {
      if (index < 0 || index >= this.size) {
        return;
      }

      ListNode curr = head;
      for (int i = 0; i < index; i++) {
        curr = curr.next;
      }
      curr.next = curr.next.next;

      size--;
    }

    private class ListNode {
      int val;
      ListNode next;

      public ListNode() { }

      public ListNode(int val) {
        this.val = val;
      }
    }
  }
}
