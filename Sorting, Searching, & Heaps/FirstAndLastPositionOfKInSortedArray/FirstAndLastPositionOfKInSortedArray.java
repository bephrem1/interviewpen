/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int[] searchRange(int[] nums, int target) {
    int[] rangeResult = { -1, -1 };

    int leftBoundIndex = search(nums, target, SearchType.FIRST);

    if (leftBoundIndex == -1) {
      return rangeResult;
    }

    /*
     * If first occurrence is found then add it to the result and search for the
     * last occurrence and store that result
     */
    rangeResult[0] = leftBoundIndex;
    rangeResult[1] = search(nums, target, SearchType.LAST);

    return rangeResult;
  }

  private int search(int[] nums, int target, SearchType searchType) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        if (searchType == SearchType.FIRST) {
          if (isInBounds(nums, mid - 1) && nums[mid - 1] == nums[mid]) {
            right = mid - 1;
          } else {
            return mid;
          }
        } else if (searchType == SearchType.LAST) {
          if (isInBounds(nums, mid + 1) && nums[mid + 1] == target) {
            left = mid + 1;
          } else {
            return mid;
          }
        }
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return -1; // item not found
  }

  private boolean isInBounds(int[] arr, int index) {
    return index >= 0 && index <= arr.length - 1;
  }

  private enum SearchType {
    FIRST, LAST
  }
}
