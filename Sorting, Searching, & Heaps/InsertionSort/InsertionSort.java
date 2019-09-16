/*
  Insertion Sort.

  The video to explain this code is here: https://www.youtube.com/watch?v=ufIET8dMnus
*/

private void insertionSort(int arr[]) {

  int n = arr.length;

  for (int i = 1; i < n; i++) {

    /*
      Extract the variable at arr[i] into
      the temp variable t so we can trample
      over the value at index i without fearing
      losing anything

      This is the item we want to find a place for
      in the growing sorted section so that we can
      "insert" it

      Hence the name "insertion sort"
    */
    int t = arr[i];

    /*
      The j iteration variable starts 1 index behind
      where i stands
    */
    int j = i - 1;

    /*
      Walk j backwards until we know where to
      place the item we extracted to the temp
      variable t
    */
    while (j >= 0 && t < arr[j]) {
        arr[j + 1] = arr[j];
        j = j - 1;
    }

    /*
      We now place the item that we stashed
      in the temp variable
    */
    arr[j + 1] = t;

  }

}
