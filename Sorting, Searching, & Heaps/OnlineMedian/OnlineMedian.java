/*
  "Online" algorithms are those that can process input piece-by-piece
  instead of getting the whole input all at once to create an output from.

  In this problem we are asked to create a structure that allows to know
  the live median of an incoming sequence of data.

  The full data stream is not known all at once and we must optimize for an
  asymptotically fast median read time at all points in the data stream
  being sent to us.
*/
import java.util.*;

public class MedianOfOnlineData {
  public static void main(String args[]) {
    int[] dataStream = new int[]{
      21, 19, 80, 21, 28, 27, 47, 21, 98, 81, 74, 73, 53, 72, 86, 83, 88, 64, 15, 51,
      90, 25, 3, 7, 4, 80, 76, 93, 15, 24, 88, 25, 94, 20, 97, 42, 5, 87, 81, 68, 31,
      69, 46, 36, 71, 34, 21, 80, 46, 43, 83, 91, 1, 2, 66, 31, 20, 75, 32, 6, 39, 44,
      44, 4, 58, 70, 60, 41, 53, 94, 58, 21, 35, 21, 69, 26, 95, 69, 75, 21, 68, 37,
      23, 100, 86, 50, 83, 49, 99, 84, 7, 48, 92, 20, 31, 53, 85, 95, 96, 8
    };

    MedianManager medianManager = new MedianManager();

    for (int i = 0; i < dataStream.length; i++) {
      System.out.println("Inserting " + dataStream[i]);
      medianManager.insert(dataStream[i]);
      System.out.println("The new running median is " + medianManager.median() + "\n");
    }
  }

  private static class MedianManager {
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
      if (lowerHalf.isEmpty()) {
        lowerHalf.add(x); // 1
      } else {
        if (x >= lowerHalf.peek()) {
          upperHalf.add(x); // 2
        } else {
          lowerHalf.add(x); // 3
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
