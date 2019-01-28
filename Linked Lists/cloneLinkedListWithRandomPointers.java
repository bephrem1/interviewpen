/*
  Copy List with Random Pointer - LeetCode: https://leetcode.com/problems/copy-list-with-random-pointer/

  Revisions by Benyam Ephrem (Jan. 27th 2019)
    > Making variable names more conventional
    > Adding more clarifying comments
    > Moving code around to be more conventional

  The video to explain this code is here: [a link will live here someday]
*/

/*
  This code passes all Leetcode test cases as of Jan. 27th 2019
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
  This code passes all Leetcode test cases as of Jan. 27th 2019
  Runtime: 

  Credits: Leetcode user @liaison
*/
class ConstantSpaceApproach {

}
