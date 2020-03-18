/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public class MaxStack {
    private Deque<Integer> actualElements = new LinkedList<>();
    private Deque<MaxWithCount> cachedMaxWithCount = new LinkedList<>();

    /*
    * To check if the MaxStack is empty we just make an underlying call to the
    * stack of the actualElements
    */
    public boolean isEmpty() {
      return actualElements.isEmpty();
    }

    public int max() {
      if (isEmpty()) {
        throw new IllegalStateException("max(): empty stack");
      }

      return cachedMaxWithCount.peekFirst().max;
    }

    public int pop() {
      if (isEmpty()) {
        throw new IllegalStateException("pop(): empty stack");
      }

      int poppedElement = actualElements.removeFirst();

      /*
      * If the element that we popped was a max element we need to take action to
      * update our max cache stack.
      */
      if (poppedElement.equals(cachedMaxWithCount.peekFirst().max)) {

        /*
        * Reduce the count of occurrences of the element at the top of the max cache
        * stack since we just popped a max element
        */
        cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count - 1;

        /*
        * If there are no more of this max element then remove this entry from the max
        * cache stack
        */
        if (cachedMaxWithCount.peekFirst().count.equals(0)) {
          cachedMaxWithCount.removeFirst();
        }
      }

      return poppedElement; // give our caller the popped element
    }

    public void push(int item) {
      /*
      * Push the element to our actual elements BUT we are not done. We must see how
      * this element influences our max cache state
      */
      actualElements.addFirst(item);

      /*
      * If the max cache is empty then just add this element, otherwise see how this
      * element contributes to the max cache
      * 
      * 1.) If it is the same as the max element, add 1 to the occurrences of the top
      * item on the cache
      * 
      * 2.) If it is greater than the max element then we have a new maximum item,
      * create a cache entry for it
      * 
      * 3.) If it is less than the item at the top of the cache then NOTHING HAPPENS,
      * this item does not get an entry and it is ignored in terms of contributing to
      * the max stack cache state
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

  private class MaxWithCount {
    public int max;
    public int count;

    public MaxWithCount(int max, int count) {
      this.max = max;
      this.count = count;
    }
  }
}
