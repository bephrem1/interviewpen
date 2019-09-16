/*
  Copy List with Random Pointer - LeetCode: https://leetcode.com/problems/copy-list-with-random-pointer/

  Revisions by Benyam Ephrem (Jan. 28th 2019)
    > Making variable names more conventional
    > Adding more clarifying comments
    > Moving code around to be more conventional

  The video to explain this code is here: https://www.youtube.com/watch?v=OvpKeraoxW0
*/

/*
  This code passes all Leetcode test cases as of Jan. 28th 2019
  Runtime: 3 ms, faster than 56.69% of Java online submissions for Copy List with Random Pointer.
  (can't get much faster than this)

  Credits: Leetcode user @jeantimex
*/
class LinearSpaceApproach {

  public RandomListNode copyRandomList(RandomListNode head) {

    /*
      If the head is null then no cloning can happen
    */
    if (head == null) {
      return null;
    }
    
    /*
      Our clone map. We map the original node to its clone.

      This is the key to the problem, from here all else
      is simple linear time iteration.
    */
    Map<RandomListNode, RandomListNode> cloneMap = new HashMap<RandomListNode, RandomListNode>();
    
    /*
      1st pass.
      Give all nodes their clone in the mapping.
    */
    RandomListNode curr = head;
    while (curr != null) {
      cloneMap.put(curr, new RandomListNode(curr.label));
      curr = curr.next;
    }
    
    /*
      2nd pass.
      Reset the curr pointer to the head of the original list
      Give all clones their next and random pointer assignments.

      Our cloneMap lets us reach an original node's clone in 
      O(1) time.
    */
    curr = head;
    while (curr != null) {

      /*
        cloneMap.get(curr).next -> Set the next of curr's clone to...
        cloneMap.get(curr.next) -> The clone of curr's next.
      */
      cloneMap.get(curr).next = cloneMap.get(curr.next);

      /*
        cloneMap.get(curr).random -> Set the random of curr's clone to...
        cloneMap.get(curr.random) -> The clone node that curr.random is pointing to.
      */
      cloneMap.get(curr).random = cloneMap.get(curr.random);

      curr = curr.next;
    }
    
    /*
      Return the clone node of the head. This is the caller's access
      to the newly copied list.
    */
    return cloneMap.get(head);
  }

}

/*
  This code passes all Leetcode test cases as of Jan. 28th 2019
  Runtime: 1 ms, faster than 99.37% of Java online submissions for Copy List with Random Pointer.

  Buckle up and start your imagination...this is about to get tricky...

  Credits: Leetcode user @liaison
*/
class ConstantSpaceApproach {

  public RandomListNode copyRandomList(RandomListNode head) {

    RandomListNode curr = head;
    RandomListNode next = null;

    /*
      First pass.
      Clone each node and link each original node to it's copy
      via the original node's net pointer
    */
    while (curr != null) {

      /*
        Stash the next value of the current node so we
        do not lose it
      */
      next = curr.next;

      /*
        Create the copy node.
        Point curr (original node) next value to the copy.
        Point the next value of the copy node to curr (original node) next value.
        You'll see why we do this later...
      */
      RandomListNode copy = new RandomListNode(curr.label);
      curr.next = copy;
      copy.next = next;

      /*
        Advance to next node in the original list
      */
      curr = next;
    }

    /*
      Second pass.
      Reset curr to the head of the original list.
    */
    curr = head;
    while (curr != null) {

      /*
        If there is a random pointer to copy
      */
      if (curr.random != null) {
        /*
          Assign clone the random mapping

          "curr.next.random = curr.random.next" means...
          Jump to the copy node with "curr.next" and set its random value to...

          "curr.random.next" which is the clone node of curr.random

          KEY IDEA. We use each original node's next value to map to the clone
          allowing us to reach the clone list.

          The hashtable did this for us before. This is how we save space
        */
        curr.next.random = curr.random.next;
      }

      /*
        Advance to the next node. But why curr.next.next?

        Well...remember that each clone...points to what? It points
        to the NEXT node of its original node so that we still have
        reference to that.
      */
      curr = curr.next.next;
    }

    /*
      Third pass.
      Our goal is to restore the original list, and extract the copy list.
      First reset curr to the head of the original list.

    */
    curr = head;

    RandomListNode dummyHead = new RandomListNode(0);
    RandomListNode cloneListTail = dummyHead;
    RandomListNode copy = null;

    while (curr != null) {

      /*
        Stash the next value of the curr original node, remember this...
        we access that by saying "curr.next.next". We reach to the clone
        node and hop to that node's next value.
      */
      next = curr.next.next;

      /*
        curr.next is curr's clone. Let's grab it.
      */
      copy = curr.next;

      /*
        Append the copy to the final list tail
      */
      cloneListTail.next = copy;
      cloneListTail = copy;

      /*
        Restore curr (the original node's) next value
      */
      curr.next = next;

      /*
        Advance curr to the stashed next
      */
      curr = next;
    }

    /*
      The next node to the dummy head is the head of the
      deeply cloned list we just made. Return to our caller.
    */
    return dummyHead.next;
  }

}
