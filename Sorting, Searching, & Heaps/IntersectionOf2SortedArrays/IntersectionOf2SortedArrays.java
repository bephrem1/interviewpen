/*
  Intersection of Two Arrays - LeetCode: https://leetcode.com/problems/intersection-of-two-arrays/
*/

// This code passes all Leetcode test cases as of September 28, 2019
class BruteForce {
  public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> intersection = new HashSet<>();

    for (int i = 0; i < nums1.length; i++) {
      if (i == 0 || nums1[i] != nums1[i - 1]) {
        for (int j = 0; j < nums2.length; j++) {
          if (nums1[i] == nums2[j]) {
            intersection.add(nums1[i]);
          }
        }
      }
    }

    return setToArray(intersection);
  }

  private int[] setToArray(Set<Integer> set) {
    int[] array = new int[set.size()];

    int index = 0;
    for (int item: set) {
      array[index] = item;
      index++;
    }

    return array;
  }
}

class BinarySearch {
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

  public boolean binarySearch(int[] nums, int target) {
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
    for (int item: set) {
      array[index] = item;
      index++;
    }

    return array;
  }
}

class Linear {
  public int[] intersection(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    Set<Integer> intersection = new HashSet<>();

    int ptr1 = 0;
    int ptr2 = 0;

    while (ptr1 < nums1.length && ptr2 < nums2.length) {
      if (nums1[ptr1] == nums2[ptr2]) {
        intersection.add(nums1[ptr1]);

        ptr1++;
        ptr2++;
      } else if (nums1[ptr1] < nums2[ptr2]) {
        ptr1++;
      } else {
        ptr2++;
      }
    }

    return setToArray(intersection);
  }

  private int[] setToArray(Set<Integer> set) {
    int[] array = new int[set.size()];

    int index = 0;
    for (int item: set) {
      array[index] = item;
      index++;
    }

    return array;
  }
}
