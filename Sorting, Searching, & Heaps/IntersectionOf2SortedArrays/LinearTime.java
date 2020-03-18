/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
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
    for (int item : set) {
      array[index] = item;
      index++;
    }

    return array;
  }
}
