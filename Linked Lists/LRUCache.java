/*
	LRU Cache - LeetCode: https://leetcode.com/problems/lru-cache/

  An adaption of the answer from user "liaison" on Leetcode.
  Link: https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-%2B-Double-linked-list-(with-a-touch-of-pseudo-nodes)

  Revision by Benyam Ephrem (Dec. 31th 2018)
    > Making variable names more conventional
    > Adding more clarifying comments
    > Moving code around to be more conventional

  This code passes all Leetcode test cases as of Dec. 31st 2018
  Runtime: 77 ms, faster than 95.85% of Java online submissions for LRU Cache.

	The video to explain this code is here: [a link will live here someday]
*/

class LRUCache {

    /*
      Our internal definition of a doubly linked list
      node, put in this class for convenience since
      this is submitted in one file on Leetcode
    */
    private class DNode {
      int key;
      int value;
      DNode prev;
      DNode next;
    }

    private Map<Integer, DNode> hashtable = new HashMap<Integer, DNode>();
    private DNode head, tail;
    private int totalItemsInCache;
    private int maxCapacity;

    public LRUCache(int maxCapacity) {

      // Cache starts empty and capacity is set by client
      totalItemsInCache = 0;
      this.maxCapacity = maxCapacity;

      // Initialize the dummy head of the cache
      head = new DNode();
      head.prev = null;

      // Init the dummy tail of the cache
      tail = new DNode();
      tail.next = null;

      // Wire the head and tail together
      head.next = tail;
      tail.prev = head;
    }

    /*
      Retrieve an item from the cache
    */
    public int get(int key) {

      DNode node = hashtable.get(key);
      boolean itemFoundInCache = node != null;

      // Empty state check. Should raise exception here.
      if(!itemFoundInCache){
        return -1;
      }

      // Item has been accessed. Move to the front of the list.
      moveToHead(node);

      return node.value;
    }

    /*
      Add an item to the cache if it is not already there,
      if it is already there update the value and move it
      to the front of the cache
    */
    public void put(int key, int value) {

      DNode node = hashtable.get(key);
      boolean itemFoundInCache = node != null;

      if(!itemFoundInCache){

        // Create a new node
        DNode newNode = new DNode();
        newNode.key = key;
        newNode.value = value;

        // Add to the hashtable and the doubly linked list
        hashtable.put(key, newNode);
        addNode(newNode);

        // We just added an item to the cache
        totalItemsInCache++;

        // If over capacity evict an item with LRU cache eviction policy
        if(totalItemsInCache > maxCapacity){
          removeLRUEntryFromStructure();
        }

      } else {
        // If item is in cache just update and move it to the head
        node.value = value;
        moveToHead(node);
      }

    }

    /*
      Remove the least used entry from the doubly linked
      list as well as the hashtable. Hence it is evicted
      from the whole LRUCache structure
    */
    private void removeLRUEntryFromStructure() {
      DNode tail = popTail();
      hashtable.remove(tail.key);
      --totalItemsInCache;
    }

    /*
      Insertions to the doubly linked list will always
      be right after the dummy head
    */
    private void addNode(DNode node){

      // Wire the node being inserted
      node.prev = head;
      node.next = head.next;

      // Re-wire the head's old next
      head.next.prev = node;

      // Re-wire the head to point to the inserted node
      head.next = node;
    }

    /*
      Remove the given node from the doubly linked list
    */
    private void removeNode(DNode node){

      // Grab reference to the prev and next of the node
      DNode savedPrev = node.prev;
      DNode savedNext = node.next;

      // Cut out going forwards
      savedPrev.next = savedNext;

      // Cut out going backards
      savedNext.prev = savedPrev;
    }

    /*
      Move a node to the head of the doubly linked lsit
    */
    private void moveToHead(DNode node){
      removeNode(node);
      addNode(node);
    }

    /*
      Pop the last item from the structure
    */
    private DNode popTail(){
      DNode itemBeingRemoved = tail.prev;
      removeNode(itemBeingRemoved);
      return itemBeingRemoved;
    }

}

/**
 * The LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(maxCapacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
