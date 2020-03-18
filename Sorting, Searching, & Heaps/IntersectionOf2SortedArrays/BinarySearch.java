/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int[] intersection(int[] nums1, int[] nums2) {
    // Sorting an array of integers can run in linear time
    Arrays.sort(nums2);

    Set<Integer> intersection = new HashSet<>();

    for (int i = 0; i < nums1.length; i++) {
      if (i == 0 || nums1[i] != nums1[i - 1]) {
        boolean found = binarySearch(nums2, nums1[i]);
        System.out.println(found);
        if (found) {
          intersection.add(nums1[i]);
        }
      }
    }

    return setToArray(intersection);
  }

  private boolean binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + ((right - left) / 2);

      if (nums[mid] == target) {
        return true;
      } else if (target < nums[mid]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return false;
  }

  private int[] setToArray(Set<Integer> set) {
    int[] array = new int[set.size()];

    int index = 0;
    for (int item : set) {
      array[index] = item;
      index++;
    }

    return array;
  }
}
