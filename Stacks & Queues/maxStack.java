/*
  Authorship: ALL credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.
  
  I have just added explanatory comments, reformatted the code, & changed
  variable names for understanding*

  This problem (for min stack) on Leetcode: https://leetcode.com/problems/min-stack/
  (This code is easily adaptable for the Leetcode question)

  The video to explain this code is here: [a link will live here someday]

  * The code below is the most efficient solution with a best case O(1) space
  complexity and O(1) time complexity on the supported .max() operation.
*/

private static class MaxWithCount {
  public int max;
  public int count;

  public MaxWithCount(int max, int count) {
    this.max = max;
    this.count = count;
  }
}

public static class MaxStack {

  /*
    The stack with the actual elements
  */
  private Deque<Integer> actualElements = new LinkedList<>();

  /*
    The stack to cache historical maximums and how often they have
    occurred in sequence
  */
  private Deque<MaxWithCount> cachedMaxWithCount = new LinkedList<>();

  /*
    To check if the MaxStack is empty we just make an underlying call
    to the stack of the actualElements
  */
  public boolean isEmpty() {
    return actualElements.isEmpty();
  }

  /*
    Constant time access to the maximum element through our max cache
  */
  public int max() {

    if(isEmpty()) {
      throw new IllegalStateException("max(): empty stack");
    }

    return cachedMaxWithCount.peekFirst().max;
  }

  /*
    Standard pop operation
  */
  public int pop() {

    // Empty state check
    if(isEmpty()) {
      throw new IllegalStateException("pop(): empty stack");
    }

    int poppedElement = actualElements.removeFirst();

    /*
      If the element that we popped was a max element we need to take
      action to update our max cache stack.
    */
    if (poppedElement.equals(cachedMaxWithCount.peekFirst().max)){

      /*
        Reduce the count of occurrences of the element at the top of the
        max cache stack since we just popped a max element
      */
      cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count - 1;

      /*
        If there are no more of this max element then remove this entry from the
        max cache stack
      */
      if (cachedMaxWithCount.peekFirst().count.equals(0)){
        cachedMaxWithCount.removeFirst();
      }
    }

    return poppedElement; // give our caller the popped element
  }

  /*
    Standard push operation
  */
  public void push(int item) {

    /*
      Push the element to our actual elements BUT we are not done.
      We must see how this element influences our max cache state
    */
    actualElements.addFirst(item);

    /*
      If the max cache is empty then just add this element, otherwise see
      how this element contributes to the max cache

      1.) If it is the same as the max element, add 1 to the occurrences of
      the top item on the cache

      2.) If it is greater than the max element then we have a new maximum item,
      create a cache entry for it

      3.) If it is less than the item at the top of the cache then NOTHING HAPPENS,
      this item does not get an entry and it is ignored in terms of contributing
      to the max stack cache state
    */
    if (!cachedMaxWithCount.isEmpty()) {
      if (Integer.compare(item, cachedMaxWithCount.peekFirst().max) == 0) {
        cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count + 1;
      } else if (Integer.compare(item, cachedMaxWithCount.peekFirst().max) > 0) {
        cachedMaxWithCount.addFirst(new MaxWithCount(item, 1));
      }
    } else {
      cachedMaxWithCount.addFirst(new MaxWithCount(item, 1));
    }
  }

}
