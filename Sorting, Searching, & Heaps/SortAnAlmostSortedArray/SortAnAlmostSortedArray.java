/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void sortNearlySortedArray(int[] arr, int k) {

    /*
     * Create a min heap. Without a comparator Java (as of Java 10) defaults putting
     * the smallest items at the front of the priority queue.
     * 
     * Not sure if the Java PriorityQueue is implemented underneath with a binary
     * heap... but yeah. Remember that a heap is an implementation of the priority
     * queue ADT (abstract data type)
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int n = arr.length;

    /*
     * Add the first k elements to the min heap. Guard against the case that there
     * are less than k items in the whole array
     */
    for (int i = 0; i < k && i < n; i++) {
      minHeap.add(arr[i]);
    }

    /*
     * Add and place...add and place...add and place from the heap. Initially, we
     * added items from index 0 to index k - 1. We continue reading from index k and
     * begin our minimum element placements at index 0.
     * 
     * Continue while the read index stays within the index bounds of the array.
     * When it runs over we will have no more items to add to the heap for
     * consideration.
     */
    int readIndex = k;
    int placementIndex = 0;
    while (readIndex < n) {
      /*
       * Add the next element to be considered in the heap. The heap will hold at max
       * k + 1 items.
       */
      minHeap.add(arr[readIndex]);
      readIndex++;

      /*
       * Remove the smallest item to place into the array. This item will be placed
       * where it belongs by the definition of what k represents.
       */
      arr[placementIndex] = minHeap.poll();
      placementIndex++;
    }

    /*
     * Empty out the rest of the heap & do placements.
     */
    while (!minHeap.isEmpty()) {
      arr[placementIndex] = minHeap.poll();
      placementIndex++;
    }
  }
}
