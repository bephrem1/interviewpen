/*
  Selection Sort. Hey there.

  The video to explain this code is here: https://www.youtube.com/watch?v=TNRRoYCzlFw
*/

public void selectionSort(int[] arr) {

  int n = arr.length;

  for (int i = n; i >= 2; i--) {

    /*
      k wants to sit at the item that belongs
      at i so we can perform a swap
    */
    int k = 1;

    /*
      Use j as an iteration pointer to find where
      k belongs in the unsorted portion of the array
    */
    for (int j = 2; j <= i; j++) {

      /*
        Tried keeping this similar to the example
        discussed, but here we must index back from
        0.

        We only need to adjust back to 0 indexing when
        we touch the array so things don't blow up

        Indexing off of 1:
          arr[j - 1] > arr[k - 1]

        Indexing off of 0: (no adjustment necessary)
          arr[j] > arr[k]
      */
      if (arr[j - 1] > arr[k - 1]) {
        k = j;
      }

    }

    /*
      Indexing off of 1:
        swap(arr, k - 1, i - 1);

      Indexing off of 0: (no adjustment necessary)
        swap(arr, k, i);
    */
    swap(arr, k - 1, i - 1);

  }

}

private void swap(int[] arr, int first, int second) {
  int temp = arr[first];
  arr[first] = arr[second];
  arr[second] = temp;
}
