/*
  Bubble Sort. Hey.
*/

private static void bubbleSort(int arr[]) {

  int n = arr.length;

  for (int i = n; i >= 2; i--) {
    for (int j = 1; j <= i - 1; j++) {

      /*
        Adjust back to indexing off of 0 so we
        don't out of bounds.

        Tried keeping this similar to the example
        discussed, but here we must index back from
        0.

        Off of 1:
          arr[j] > arr[j + 1]
            swap(arr, j, j + 1);

        Off of 0:
          arr[j - 1] > arr[j]
            swap(arr, j - 1, j);
      */
      if (arr[j - 1] > arr[j]) {
        swap(arr, j - 1, j);
      }

    }
  }

}

private static void swap(int arr[], int first, int second) {
  int temp = arr[first];
  arr[first] = arr[second];
  arr[second] = temp;
}
