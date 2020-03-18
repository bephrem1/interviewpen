/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
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
    for (int item : set) {
      array[index] = item;
      index++;
    }

    return array;
  }
}
