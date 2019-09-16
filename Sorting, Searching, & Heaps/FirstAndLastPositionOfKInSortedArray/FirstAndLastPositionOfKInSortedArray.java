/**
  @author Benyam Ephrem

  This code is by no means clean. I threw it together for teaching
  purposes on how we differentiate our searches. Learn the techniques
  and do not take style notes for the most part.

  This solution is also longer than the one presented in the Leetcode
  "Solutions" section. It takes a different approach (but very similar)
  approach, watch the video for an explanation.

  Find First and Last Position of Element in Sorted Array - LeetCode:
  https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array

  This code passes all Leetcode test cases as of Jan. 13 2019
  Runtime: 6 ms, faster than 91.67% of Java online submissions for Find First and Last Position of Element in Sorted Array.

  The video to explain this code is here: https://www.youtube.com/watch?v=gOkNq8Co6B8
*/

private enum SearchType {
  FIRST,
  LAST
}

public int[] searchRange(int[] nums, int target) {

  /*
    Initialize our search results
  */
  int[] rangeResult = {-1, -1};

  /*
    We search for the first occurrence
  */
  int leftBoundIndex = search(nums, target, SearchType.FIRST);

  /*
    If first occurrence is not found, stop processing
  */
  if (leftBoundIndex == -1) {
    return rangeResult;
  }

  /*
    If first occurrence is found then add it to the result and
    search for the last occurrence and store that result
  */
  rangeResult[0] = leftBoundIndex;
  rangeResult[1] = search(nums, target, SearchType.LAST);

  return rangeResult;
}

private int search(int[] nums, int target, SearchType searchType) {

  /*
    Grab the first and last element in the array
  */
  int left = 0;
  int right = nums.length - 1;

  /*
    Search while left stays to the left (or on top) of the right
    pointer
  */
  while (left <= right) {

    /*
      Get the middle, doing: int mid = (left + right) / 2; is
      prone to overflow because we add left and right.

      This way of getting the middle avoids a possible overflow
      in that intermediary addition operation
    */
    int mid = left + (right - left) / 2;

    /*
      If we hit the target we will take action depending on what
      search type this is

      The only time we return a found equivalence is when the item is
      either at the bounds of the array, or the next item to the found
      equivalence is different meaning we have reached the first or
      last occurence of that element in the sorted sequence
    */
    if (nums[mid] == target) {

      /*
        We use an enum to diferentiate search types. I think booleans are like
        "magic numbers" (that is actually a term) and enums really codify our
        intentions better than a boolean or random number we choose
      */
      if (searchType == SearchType.FIRST) {

        if (isInBounds(nums, mid - 1) && nums[mid - 1] == nums[mid]) {
          right = mid - 1;
        } else {

          /*
            Only time this search stops is if we hit the left of the array or
            if the element to our left is not us therefore we have the first
            occurrence of k
          */
          return mid;

        }

      } else if (searchType == SearchType.LAST) {

        if (isInBounds(nums, mid + 1) && nums[mid + 1] == target) {
          left = mid + 1;
        } else {

          /*
            Only time this search stops is if we hit the right of the array or
            if the element to our right is not us therefore we have the last
            occurrence of k
          */
          return mid;

        }

      }

    } else if (nums[mid] < target) {

      /*
        Where we sit undershot the target.
        Go right, we need more value.
        We do this by narrowing the left bound.
      */
      left = mid + 1;

    } else {

      /*
        Where we sit overshot the target.
        Go left, we need less value.
        We do this by narrowing the right bound.
      */
      right = mid - 1;

    }

  }

  return -1; // item not found
}

/*
  Checks if a given 'index' is in bounds for a given array 'arr'
*/
private boolean isInBounds(int[] arr, int index) {
  return index >= 0 && index <= arr.length - 1;
}
