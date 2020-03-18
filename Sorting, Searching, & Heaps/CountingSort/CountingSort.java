/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int[] countingSort(int[] arr, int k) {
    /*
     * Our input is the 'arr' to sort and the total number of unique number values
     * in the array...'k'
     * 
     * 'k' happens to be 1 + maxNumberInTheArray because imagine the array [0, 3, 2,
     * 4]. Our 'counts' array would need to track occurrences for 0, 1, 2, 3, 4.
     * This is 5 values.
     * 
     * The maxNumberInTheArray is 4 ... 4 + 1 = 5 ... k is 5. This is because we
     * include 0 to the range from 1 to the maxNumberInTheArray.
     * 
     * Key things to help your understanding: - arr[i] -> A value in the original
     * array BEFORE AGGREGATION - counts[arr[i]] -> The # of occurrences of the
     * value arr[i] AFTER AGGREGATION - counts[arr[i]] -> The # of occurrences of
     * values <= the value arr[i]
     */
    int n = arr.length;
    int sortedOutput[] = new int[n];

    /*
     * The count array keeps track of the occurrences of the respective number
     * represented by index 'i'.
     * 
     * Index 0 has the number of occurrences for index 0. Index 1 has the number of
     * occurrences for index 1. and so on...
     */
    int counts[] = new int[k];
    for (int i = 0; i <= k - 1; i++) {
      counts[i] = 0;
    }

    /*
     * Iterate through the input array and update the 'counts' array tracking
     * occurrences
     */
    for (int i = 0; i < n; i++) {
      counts[arr[i]]++;
    }

    /*
     * Aggregate step. Reformat the counts array. Take running sums iteratively.
     * When we are done what each position counts[i] will mean is the number of
     * occurrences of values <= to the value i. Here is the transformation:
     * 
     * index 0 1 2 3 4 counts = [0, 3, 2, 1, 2]
     * 
     * index 0 1 2 3 4 counts = [0, 3, 5, 6, 8]
     * 
     * So for example counts[2]..there are 5 occurrences of #'s in 'arr' that are <=
     * 2. And we see that more specifically there are 3 occurrences of 1 and 2
     * occurrences of 2 so this is true.
     */
    for (int i = 1; i <= k - 1; i++) {
      counts[i] += counts[i - 1];
    }

    /*
     * Place items into the output array processing items from the ORIGINAL array
     * back to front. Watch the explanation video, the placement can be confusing.
     */
    for (int i = n - 1; i >= 0; i--) {
      sortedOutput[counts[arr[i]] - 1] = arr[i]; // Place an item
      counts[arr[i]]--; // Adjust the occurrence count for each # that we place into the final array.
    }

    return sortedOutput;
  }
}
