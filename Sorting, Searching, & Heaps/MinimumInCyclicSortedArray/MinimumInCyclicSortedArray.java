/*
  Find Minimum in Rotated Sorted Array - LeetCode: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
  This code passes all Leetcode test cases as of September 28, 2019
*/

public int findMin(int[] nums) {
  int left = 0;
  int right = nums.length - 1;

  while (left < right) {
    int mid = left + ((right - left) / 2);

    if (nums[mid] > nums[right]) {
      left = mid + 1;
    } else {
      right = mid;
    }
  }

  return nums[left];
}
