/*
  Kth Largest Element in an Array - LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/

  This optimal solution has the same fundamental reasoning that QuickSort uses. (the partitioning subroutine)

  Check out that QuickSort video here: https://www.youtube.com/watch?v=uXBnyYuwPe8

  This code passes all Leetcode test cases as of April 18 2019
  Runtime: 1 ms, faster than 99.86% of Java online submissions for Kth Largest Element in an Array

  The video to explain this code is here: [a link will live here someday]
*/

/*
  We make the assumption k is between 1 and n (where n is the length of the array)

  The 1st largest element is the largest element.
  The n'th largest element is the smallest element.
*/
public int findKthLargest(int[] arr, int k) {

  /*
    Keep track of the 'left' and 'right' space in which the
    k'th largest element can possibly be, we will use these bounds
    to know what section to actually partition around a choosen pivot
  */
  int n = arr.length;
  int left = 0;
  int right = n - 1;

  /*
    A Random object we will use later repeatedly to choose random pivots
  */
  Random rand = new Random(0);

  /*
    While the bounds stay valid continue doing directed partitioning
  */
  while (left <= right) {

    /*
      Pick a random pivot. Bounds are [left, right].
    */
    int choosenPivotIndex = rand.nextInt(right - left + 1) + left;

    /*
      Execute the actual partitioning and get back the final positition
      of the pivot we choose after partitioning is over
    */
    int finalIndexOfChoosenPivot = partition(arr, left, right, choosenPivotIndex);

    /*
      What does the 'finalIndexOfChoosenPivot' tell us?
    */
    if (finalIndexOfChoosenPivot == n - k) {

      /*
        Found. The pivot is index on index n - k. This is literally its final position if
        the array we were given had been sorted. See how we saved work? We don't
        need to sort the whole array
      */
      return arr[finalIndexOfChoosenPivot];

    } else if (finalIndexOfChoosenPivot > n - k) {

      /*
        k'th largest must be in the left partition. We "overshot" and need to go left
        (and we do this by narrowing the right bound)
      */
      right = finalIndexOfChoosenPivot - 1;

    } else {

      /*
        finalIndexOfChoosenPivot < n - k

        k'th largest must be in the right partition. We "undershot" and need to go right
        (and we do this by narrowing the left bound)
      */
      left = finalIndexOfChoosenPivot + 1;

    }

  }

  return -1; // this will never be reached, necessary to compile
}

/*
  So this subroutine is exactly what we do in QuickSort...partition around the value
  that the 'pivotIndex' holds

  The result is a "sandwich" at the end.

  [ items < than the pivot ... pivotItem ... items > than the pivot]
*/
private int partition(int[] arr, int left, int right, int pivotIndex) {

  // Grab the value at the pivot index we passed in
  int pivotValue = arr[pivotIndex];

  /*
    Remember how partitioning works? We need to keep track of where
    we last placed an item in the section of items "less than the
    pivot"
    
    We keep track of the tail index of this section. We will
    need this later
  */
  int lesserItemsTailIndex = left;

  /*
    Move the item at the 'pivotIndex' OUT OF THE WAY. We are about to
    bulldoze through the partitioning space and we don't want it in
    the way
  */
  swap(arr, pivotIndex, right);

  /*
    Iterate from the left bound to 1 index right before the right bound
    (where the choosen pivot value is now sitting in saftey)
  */
  for (int i = left; i < right; i++) {

    /*
      If this item is less than the 'pivotValue' then we need to move
      this item to the section of items "less than the pivot"

      'lesserItemsTailIndex' keeps track of where we need to swap into
      next...after doing the swap we advance it...you see how this is
      coming together?
    */
    if (arr[i] < pivotValue) {
      swap(arr, i, lesserItemsTailIndex);
      lesserItemsTailIndex++;
    }
  }

  /*
    Ok...partitioning is done. Swap the pivot item BACK into the space we just
    partitioned at the 'lesserItemsTailIndex'...that's where the pivot item
    belongs

    In the middle of the "sandwich".
  */
  swap(arr, right, lesserItemsTailIndex);
  
  /*
    Return the index of where we just put the pivot so that the caller knows its
    final resting place (so that the caller can make the decisions it needs)
  */
  return lesserItemsTailIndex;
}

private void swap(int[] arr, int first, int second) {
  int temp = arr[first];
  arr[first] = arr[second];
  arr[second] = temp;
}
