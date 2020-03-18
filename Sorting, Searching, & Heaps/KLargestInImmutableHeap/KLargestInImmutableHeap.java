/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Integer> kLargestInImmutableHeap(int[] immutableHeap, int k) {
    if (k <= 0) {
      return new ArrayList<>();
    }

    int initialHeapCapacity = 10;
    List<Integer> kLargest = new ArrayList<>();

    PriorityQueue<HeapItem> maxCandidates = new PriorityQueue<>(initialHeapCapacity, new MaxHeapComparator());
    maxCandidates.add(new HeapItem(0, immutableHeap[0]));

    /*
      We will do k iterations and add the left and right child of each item touched to
      the 'maxCandidates' max heap. Since this is a max heap, we know that both children
      will be <= the parent item.
      
      If that item every gets added as an item in the max k items, it "unlocks" its children
      to be now under consideration since we "make room" for them not to be dominated by their
      parent in value.
    */
    for (int i = 0; i < k; i++) {
      HeapItem candidate = maxCandidates.poll();
      kLargest.add(candidate.value);

      int leftChildIndex = candidate.getLeftChildIndex();
      if (leftChildIndex < immutableHeap.length) {
        maxCandidates.add(new HeapItem(leftChildIndex, immutableHeap[leftChildIndex]));
      }

      int rightChildIndex = candidate.getRightChildIndex();
      if (rightChildIndex < immutableHeap.length) {
        maxCandidates.add(new HeapItem(rightChildIndex, immutableHeap[rightChildIndex]));
      }
    }

    return kLargest;
  }

  /*
    Priority Queue's in Java default to keeping smallest item at top, we need to
    implement a custom compare function to reverse this to have max heap behaviour
  */
  private class MaxHeapComparator implements Comparator<HeapItem> {
    @Override
    public int compare(HeapItem o1, HeapItem o2) {
      return Integer.compare(o2.value, o1.value);
    }
  }

  private class HeapItem {
    int index;
    int value;

    public HeapItem(int index, int value) {
      this.index = index;
      this.value = value;
    }

    private int getParentIndex() {
      return (index - 1) / 2;
    }

    private int getLeftChildIndex() {
      return 2 * index + 1;
    }

    private int getRightChildIndex() {
      return 2 * index + 2;
    }
  }
}
