/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void insertionSort(int arr[]) {
    int lastIndex = arr.length - 1;

    for (int i = 1; i <= lastIndex; i++) {
      /*
        Extract the arr[i] into the temp so we can trample over
        the value at index i as we shift items over

        This is the item we want to find a place for in the growing
        sorted section at the end of the array so that we can "insert"
        it there

        Hence the name "insertion sort"
      */
      int temp = arr[i];

      // The j iteration variable starts 1 index behind where i stands
      int j = i - 1;

      /*
        Walk j backwards until we know where to place the item we
        extracted to the temp variable
      */
      while (j >= 0 && temp < arr[j]) {
        arr[j + 1] = arr[j];
        j--;
      }

      // We now place the item that we stashed in the temp variable
      arr[j + 1] = temp;
    }
  }
}
