/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  class LRUCache {
    Map<Integer, ListNode> hashtable = new HashMap<Integer, ListNode>();
    ListNode head;
    ListNode tail;

    int totalItemsInCache;
    int maxCapacity;

    public LRUCache(int maxCapacity) {
      // Cache starts empty and capacity is set by client
      totalItemsInCache = 0;
      this.maxCapacity = maxCapacity;

      // Dummy head and tail nodes to avoid empty states
      head = new ListNode();
      tail = new ListNode();

      // Wire the head and tail together
      head.next = tail;
      tail.prev = head;
    }

    public Integer get(int key) {
      ListNode node = hashtable.get(key);

      if (node == null) {
        return null;
      }

      // Item has been accessed. Move to the front of the cache
      moveToHead(node);

      return node.value;
    }

    public void put(int key, int value) {
      ListNode node = hashtable.get(key);

      if (node == null) {
        // Item not found, create a new entry
        ListNode newNode = new ListNode();
        newNode.key = key;
        newNode.value = value;

        // Add to the hashtable and the actual list that represents the cache
        hashtable.put(key, newNode);
        addToFront(newNode);
        totalItemsInCache++;

        // If over capacity remove the LRU item
        if (totalItemsInCache > maxCapacity) {
          removeLRUEntry();
        }
      } else {
        // If item is found in the cache, just update it and move it to the head of the list
        node.value = value;
        moveToHead(node);
      }
    }

    private void removeLRUEntry() {
      ListNode tail = popTail();

      hashtable.remove(tail.key);
      --totalItemsInCache;
    }

    private ListNode popTail() {
      ListNode tailItem = tail.prev;
      removeFromList(tailItem);

      return tailItem;
    }

    private void addToFront(ListNode node) {
      // Wire up the new node being to be inserted
      node.prev = head;
      node.next = head.next;

      /*
        Re-wire the node after the head. Our node is still sitting "in the middle of nowhere".
        We got the new node pointing to the right things, but we need to fix up the original
        head & head's next.

        head <-> head.next <-> head.next.next <-> head.next.next.next <-> ...
        ^            ^
        |- new node -|

        That's where we are before these next 2 lines.
      */
      head.next.prev = node;
      head.next = node;
    }

    private void removeFromList(ListNode node) {
      ListNode savedPrev = node.prev;
      ListNode savedNext = node.next;

      savedPrev.next = savedNext;
      savedNext.prev = savedPrev;
    }

    private void moveToHead(ListNode node) {
      removeFromList(node);
      addToFront(node);
    }

    private class ListNode {
      int key;
      int value;

      ListNode prev;
      ListNode next;
    }
  }
}
