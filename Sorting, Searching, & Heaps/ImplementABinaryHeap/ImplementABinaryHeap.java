/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  /*
    A min heap implementation

    Array Form: [ 5, 7, 6, 10, 15, 17, 12 ]

    Complete Binary Tree Form:
                    5
                /         \
            7               6
        /     \          /     \
      10      15        17      12

    Mappings:
      Parent -> (childIndex - 1) / 2
      Left Child -> 2 * parentIndex + 1
      Right Child -> 2 * parentIndex + 2
  */

  private static class MinHeap {
    private int capacity = 5;
    private int heap[];
    private int size;

    public MinHeap() {
      heap = new int[capacity];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public int peek() {
      if (isEmpty()) {
        throw new NoSuchElementException("Heap is empty.");
      }

      return heap[0];
    }

    public int remove() {
      if (isEmpty()) {
        throw new NoSuchElementException("Heap is empty.");
      }

      /*
        -> Grab the min item. It is at index 0.
        -> Move the last item in the heap to the "top" of the
        heap at index 0.
        -> Reduce size.
      */
      int minItem = heap[0];
      heap[0] = heap[size - 1];
      size--;

      /*
        Restore the heap since it is very likely messed up now
        by bubbling down the element we swapped up to index 0
      */
      heapifyDown();

      return minItem;
    }

    public void add(int itemToAdd) {
      ensureExtraCapacity();

      /*
        -> Place the item at the bottom, far right, of the
        conceptual binary heap structure
        -> Increment size
      */
      heap[size] = itemToAdd;
      size++;

      /*
        Restore the heap since it is very likely messed up now
        by bubbling up the element we just put in the last empty
        position of the conceptual complete binary tree
      */
      siftUp();
    }

    /***********************************
          Heap restoration helpers
    ***********************************/

    private void heapifyDown() {
      /*
        We will bubble down the item just swapped to the "top" of the heap
        after a removal operation to restore the heap
      */
      int index = 0;

      /*
        Since a binary heap is a complete binary tree, if we have no left child
        then we have no right child. So we continue to bubble down as long as
        there is a left child.
        
        A non-existent left child immediately tells us that a right child does
        not exist.
      */
      while (hasLeftChild(index)) {
        /*
          By default assume that left child is smaller. If a right
          child exists see if it can overtake the left child by
          being smaller
        */
        int smallerChildIndex = getLeftChildIndex(index);
        if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
          smallerChildIndex = getRightChildIndex(index);
        }

        /*
          If the item we are sitting on is < the smaller child then
          nothing needs to happen & sifting down is finished.
          
          But if the smaller child is smaller than the node we are
          holding, we should swap and continue sifting down.
        */
        if (heap[index] < heap[smallerChildIndex]) {
          break;
        } else {
          swap(index, smallerChildIndex);
        }

        // Move to the node we just swapped down
        index = smallerChildIndex;
      }
    }

    // Bubble up the item we inserted at the "end" of the heap
    private void siftUp() {
      /*
        We will bubble up the item just inserted into to the "bottom"
        of the heap after an insert operation. It will be at the last index
        so index 'size' - 1
      */
      int index = size - 1;

      /*
        While the item has a parent and the item beats its parent in
        smallness, bubble this item up.
      */
      while (hasParent(index) && heap[index] < parent(index)) {
        swap(getParentIndex(index), index);
        index = getParentIndex(index);
      }
    }

    /************************************************
      Helpers to access our array easily, perform
      rudimentary operations, and manipulate capacity
    ************************************************/

    private void swap(int indexOne, int indexTwo) {
      int temp = heap[indexOne];
      heap[indexOne] = heap[indexTwo];
      heap[indexTwo] = temp;
    }

    // If heap is full then double capacity
    private void ensureExtraCapacity() {
      if (size == capacity) {
        heap = Arrays.copyOf(heap, capacity * 2);
        capacity *= 2;
      }
    }

    private int getLeftChildIndex(int parentIndex) {
      return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
      return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
      return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
      return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
      return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
      return index != 0 && getParentIndex(index) >= 0;
    }

    private int leftChild(int index) {
      return heap[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
      return heap[getRightChildIndex(index)];
    }

    private int parent(int index) {
      return heap[getParentIndex(index)];
    }
  }
}
