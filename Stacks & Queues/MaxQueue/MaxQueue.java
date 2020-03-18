/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public class MaxQueue {
    private Queue<Integer> queue = new LinkedList<>();

    /*
      We maintain this double-ended queue to keep maxes
      we have seen in order.

      dequeue():
        When removing an item we will see if it is the item
        at the head of the maxCache queue. If so, then it is
        a max item that needs to be removed. Then the next
        item at the head of the maxCache is the greatest item
        (because of how we will enqueue)

      enqueue():
        When we enqueue an item 't' to the tail of the actual
        queue we will remove from the maxCache's tail until the
        last item is >= to 't'. This is because 't' will dominate
        all of those cached maximums BUT still stay behind the
        cached maximums at the head of the queue which signify items
        still in the queue that are larger (and will be removed first
        before 't').

        If 't' is the largest item in the whole queue it will completely
        clear the maxCache.
    */
    private Deque<Integer> maxCache = new LinkedList<>();

    public void enqueue(int x) {
      queue.add(x);

      // Eject items 'beat' by this enqueued item
      while (!maxCache.isEmpty() && maxCache.peekLast() < x) {
        maxCache.removeLast();
      }

      maxCache.addLast(x);
    }

    public int dequeue() {
      if (queue.isEmpty()) {
        throw new NoSuchElementException("Queue is empty.");
      }

      /*
        Extract value and see if this was at the head of 'maxCache'
        and needs removal from the max history
      */
      int value = queue.poll();
      if (value == maxCache.peekFirst()) {
        maxCache.removeFirst();
      }

      return value;
    }

    public int max() {
      if (maxCache.isEmpty()) {
        throw new NoSuchElementException("Queue is empty.");
      }

      return maxCache.peek();
    }
  }
}
