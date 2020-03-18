/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public class MedianManager {
    private static final int INITIAL_CAPACITY = 10;

    /*
      Java default is a priority queue that has the minimum item at the top, so we need
      to pass a custom compare function to order the priority queue so that the max
      item is at the front (so it behaves like a max heap)
    */
    PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(INITIAL_CAPACITY, Collections.reverseOrder());
    PriorityQueue<Integer> upperHalf = new PriorityQueue<>(); // Default behaves like min heap, no modification necessary

    public double median() {
      if (lowerHalf.size() == upperHalf.size()) {
        return .5 * (lowerHalf.peek() + upperHalf.peek());
      } else {
        return upperHalf.peek();
      }
    }

    public void insert(int x) {
      /*
        1.) The first item
        2.) Item belongs in the upper half of items
        3.) Item belongs in the lower half of items
      */
      if (upperHalf.isEmpty()) {
        upperHalf.add(x); // 1
      } else {
        if (x < upperHalf.peek()) {
          lowerHalf.add(x); // 2
        } else {
          upperHalf.add(x); // 3
        }
      }

      rebalance();
    }

    /*
      2 situations for reading the median:
        1.) If we have read odd items the 'upperHalf' heap will have the median avaliable.
        2.) If we have read even items then the median is .5 the 2 items offered by both heaps.

      2 rebalancing situations:
        1.) The upperHalf has 2 more items than the lowerHalf -> [ x x ] and [ x x x x ]
        2.) The lowerHalf has 1 more item than the upperHalf -> [ x x x ] and [ x x ]
      
      Our "balanced state" is when either both heaps are equal in size or the upperHalf
      has 1 more element than the lowerHalf.
    */
    private void rebalance() {
      if (upperHalf.size() >= lowerHalf.size() + 2) {
        lowerHalf.add(upperHalf.poll());
      } else if (lowerHalf.size() >= upperHalf.size() + 1) {
        upperHalf.add(lowerHalf.poll());
      }
    }
  }
}
