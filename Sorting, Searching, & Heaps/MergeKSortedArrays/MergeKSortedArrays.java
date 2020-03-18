/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    /*
      Create a list of iterators to keep track of what item we are at
      in each array. The list has no values until we add iterators to it
    */
    List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());

    /*
      Populate the iterator list with a respective iterator for each list
      (we will use these later to fetch the next items in processing)
    */
    for (List<Integer> array : sortedArrays) {
      iters.add(array.iterator());
    }

    /*
      We create a PriorityQueue. A heap is an implementation of the abstract
      data type (ADT) "priority queue". We pass a comparator so it knows how
      to order items so that the minimum item is at the top
    */
    Queue<ArrayEntry> minHeap = new PriorityQueue<>(
      ((int) sortedArrays.size()), new Comparator<ArrayEntry>() {
        @Override
        public int compare(ArrayEntry o1 , ArrayEntry o2) {
          return Integer.compare(o1.value , o2.value);
        }
      });

    /*
      Add the FIRST item from EACH list via the iterator we "juiced" out
      of it before IF it has an item to add (is not empty).
    */
    for (int i = 0; i < iters.size(); i++) {
      if (iters.get(i).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(i).next(), i));
      }
    }

    /*
      While the min heap is not empty we:

      Step 1.) Get the minimum item from the heap
      Step 2.) Add the ArrayEntry's value to the result array
      Step 3.) If the ejected entry has a next value, add that next item
      as an annotated ArrayEntry object

      And iteration continues...

      When the min heap is entry we will have processed all
      items in every array and have a full sorted result
    */
    List<Integer> result = new ArrayList<>();
    while(!minHeap.isEmpty()){

      // Step 1
      ArrayEntry ejectedMinItem = minHeap.poll();

      // Step 2
      result.add(ejectedMinItem.value);

      // Step 3
      if(iters.get(ejectedMinItem.arrayId).hasNext()){
        minHeap.add(new ArrayEntry(iters.get(ejectedMinItem.arrayId).next(), ejectedMinItem.arrayId));
      }

    }

    return result; // result now holds all k sorted arrays merged into one sorted array
  }

  private class ArrayEntry {
    public Integer value;
    public Integer arrayId;

    public ArrayEntry(Integer value, Integer arrayId) {
      this.value = value;
      this.arrayId = arrayId;
    }
  }
}
