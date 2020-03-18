/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int maxSubArray(int[] nums) {
    int n = nums.length;
    int maximumSubArraySum = Integer.MIN_VALUE;

    /*
      We will use these outer 2 for loops to investigate all
      windows of the array.
      
      We plant at each 'left' value and explore every
      'right' value from that 'left' planting.

      These are our bounds for the window we will investigate.
    */
    for (int left = 0; left < n; left++) {
      for (int right = left; right < n; right++) {
        // Let's investigate this window
        int windowSum = 0;

        // Add all items in the window
        for (int k = left; k <= right; k++) {
          windowSum += nums[k];
        }

        // Did we beat the best sum seen so far?
        maximumSubArraySum = Math.max(maximumSubArraySum, windowSum);
      }
    }

    return maximumSubArraySum;
  }
}
