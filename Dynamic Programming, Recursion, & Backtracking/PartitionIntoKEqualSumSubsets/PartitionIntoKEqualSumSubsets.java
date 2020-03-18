/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean canPartitionKSubsets(int[] arr, int k) {
    /*
      Get the sum of all items in the array. We will use this to
      divide by k to get the sum that each bucket needs to hit
    */
    int sumOfAllArrayItems = 0;
    for (int num : arr) {
        sumOfAllArrayItems += num;
    }

    /*
      1.) k can not be negative or 0 because we can not fill 0
      or negative buckets

      2.) k can not be greater than the length of the array,
      we can't partition more buckets than there are elements
      in the array

      3.) sumOfAllArrayItems % k must be 0. If it is not then
      we would have to have to fill buckets to a floating point
      sum which would be impossible with only integers
    */
    if (k <= 0 || k > arr.length || sumOfAllArrayItems % k != 0) {
      return false;
    }

    return canPartition(0, arr, new boolean[arr.length], k, 0, sumOfAllArrayItems / k);
  }

  private boolean canPartition(int iterationStart, int[] arr, boolean[] used, int k,
                        int inProgressBucketSum, int targetBucketSum) {
    /*
      If we have filled all k - 1 buckets up to this point and we are now on
      our last bucket, we can stop and be finished.
      
      Example:

      arr = [4, 3, 2, 3, 5, 2, 1]
      k = 4

      targetBucketSum = 5

      If we get to the point in our recursion that k = 1 that means we have filled
      k - 1 buckets (4 - 1 = 3). 3 buckets have been filled, each a value of 5 meaning
      we have "eaten" 15 "points" of value from an array that sums to 20.

      This means we have 5 "points" to extract from the array and that for sure will fill
      the last bucket. So at the point there is 1 bucket left, we know we can complete the
      partitioning (we don't have to though, we just want to know whether we can or not).
    */
    if (k == 1) {
      return true;
    }

    /*
      Bucket full. continue the recursion with k - 1 as the new k value, BUT the
      targetBucketSum stays the same. We just have 1 less bucket to fill.
    */
    if (inProgressBucketSum == targetBucketSum) {
      return canPartition(0, arr, used, k - 1, 0, targetBucketSum);
    }

    /*
      Try all values from 'iterationStart' to the end of the array ONLY if:
      
      1.) They have not been used up to this point in the recursion's path
      2.) They do not overflow the current bucket we are filling
    */
    for (int i = iterationStart; i < arr.length; i++) {
      if (!used[i] && inProgressBucketSum + arr[i] <= targetBucketSum) {
        used[i] = true;
        /*
          See if we can partition from this point with the item added
          to the current bucket progress
        */
        if (canPartition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)) {
          return true;
        }
        used[i] = false;
      }
    }

    /*
      Partitioning from this point is impossible. Whether we are at the
      top level of the recursion or deeper into it.
    */
    return false;
  }
}
