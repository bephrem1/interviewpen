/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Integer> kSmallestElements(int[] elements, int k) {
    /*
      If we want the k smallest items, we don't care for large items. So max heap.
      If we want the k largest items, we don't care for small items. So min heap.
    */
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

    for (int element: elements) {
      maxHeap.add(element);

      if (maxHeap.size() == k + 1) {
        maxHeap.poll(); // over capacity by 1, eject largest item
      }
    }

    // Format to list just so we aren't returning a raw prioirty queue
    List<Integer> kSmallestElements = new ArrayList<>(maxHeap);

    return kSmallestElements;
  }
}
