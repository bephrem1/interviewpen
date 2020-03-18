/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public void selectionSort(int[] arr) {
    int lastIndex = arr.length - 1;

    for (int i = lastIndex; i >= 1; i--) {
      int indexOfItemToPlace = 0;

      for (int j = 1; j <= i; j++) {
        if (arr[j] > arr[indexOfItemToPlace]) {
          indexOfItemToPlace = j;
        }
      }

      swap(arr, indexOfItemToPlace, i);
    }
  }

  private void swap(int[] arr, int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }
}
