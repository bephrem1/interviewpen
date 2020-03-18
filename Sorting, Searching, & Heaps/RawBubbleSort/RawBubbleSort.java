/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class RawBubbleSort {
  public void bubbleSort(int[] arr) {
    int lastIndex = arr.length - 1;

    for (int i = lastIndex; i >= 1; i--) {
      for (int j = 1; j <= i; j++) {
        if (arr[j - 1] > arr[j]) {
          swap(arr, j - 1, j);
        }
      }
    }
  }

  private void swap(int[] arr, int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }
}
