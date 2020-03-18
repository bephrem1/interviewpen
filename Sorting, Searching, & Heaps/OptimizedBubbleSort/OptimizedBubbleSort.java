/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void bubbleSort(int arr[]) {
    /*
      n: the array length
      i: the element 1 position/index behind the target position/index we are bubbling up to
      t: the position/index that the LAST swap happened

      Off by one is annoying for this but...just stay with it and this will make sense.
    */
    int n = arr.length;
    int i = n - 1;

    while (i > 0) {
      // This variable remembers the LAST POSITION
      int t = 1;

      /*
        We will start j at position 1 (index 0) and move
        it up to (and including) position i (index i - 1)

        Our goal is to bubble up the items until we have the item
        that belongs at POSITION i + 1 (which maps to index i + 2)
        where it is supposed to be.
      */
      for (int j = 1; j <= i; j++) {
        /*
          Tried keeping this similar to the example
          discussed, but here we must index back from
          0.

          Adjust back to indexing off of 0 so we
          don't out of bounds. Any operation touching
          the actual array must index back to 0.

          Off of 1:
            arr[j] > arr[j + 1]
              swap(arr, j, j + 1);

          Off of 0:
            arr[j - 1] > arr[j]
              swap(arr, j - 1, j);

          t = j; stays the same since this does not access
          the array. We can still say the swap happened at
          position j (really means index j - 1).
        */

        if (arr[j - 1] > arr[j]) {
          swap(arr, j - 1, j);
          t = j;
        }
      }

      /*
        We now can put i one position behind where the
        LATEST swap happened (that is what t represents).

        We know that everything past (and including) POSITION
        i + 2 is already sorted.
      */
      i = t - 1;
    }
  }

  private void swap(int arr[], int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }
}

/*
  This is an implementation of Bubble Sort that improves
  the best case to O(n) time.

  It also improves the average case's amount of comparisons
  but it does NOT change the asymptotic complexity of the
  average case.

  When I say "position" I mean indexing off of 1.
  When I say "index" I mean indexing off of 0.
*/

/*
  Why does this improve the best case?

  [ 10 20 30 40 50 ]   how we start. compare 10 & 20. no swap happens.
     j        i
     t

  [ 10 20 30 40 50 ]   compare 20 & 30. no swap happens.
        j     i
     t

  [ 10 20 30 40 50 ]   compare 30 & 40. no swap happens.
           j  i
     t

  [ 10 20 30 40 50 ]   compare 40 & 50. no swap happens.
              i
     t        j

  [ 10 20 30 40 50 ]   i becomes t - 1. i == POSITION 0. i is not > 0. we stop.
 i            
     t        j

  This is the best case input. We processed it with n - 1 comparisons.

  This is linear time. O(n), where n is the length of the array.
*/
