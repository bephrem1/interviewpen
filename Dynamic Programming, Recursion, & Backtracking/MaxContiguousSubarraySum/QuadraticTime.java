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

    for (int left = 0; left < n; left++) {

      /*
        Reset our running window sum once we choose a new
        left bound to plant at. We then keep a new running
        window sum.
      */
      int runningWindowSum = 0;

      /*
        We improve by noticing we are performing duplicate
        work. When we know the sum of the subarray from
        0 to right - 1...why would we recompute the sum
        for the subarray from 0 to right?

        This is unnecessary. We just add on the item at
        nums[right].
      */
      for (int right = left; right < n; right++) {

        // We factor in the item at the right bound
        runningWindowSum += nums[right];

        // Does this window beat the best sum we have seen so far?
        maximumSubArraySum = Math.max(maximumSubArraySum, runningWindowSum);
      }
    }

    return maximumSubArraySum;
  }
}
