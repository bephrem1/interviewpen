/** 
  Range Sum Query - Immutable - LeetCode: https://leetcode.com/problems/range-sum-query-immutable/

  This code passes all Leetcode test cases as of Feb. 6th 2019
  Runtime: 66 ms, faster than 96.82% of Java online submissions for Range Sum Query - Immutable.
  Memory Usage: 31.2 MB, less than 88.74% of Java online submissions for Range Sum Query - Immutable.

  The video to explain this code is here: [a link will live here someday]
*/

class NumArray {

  int[] runningSumCache;

  public NumArray(int[] nums) {

    /*
      The running sum cache will be the size of the array that we are given.
      We do nums.length + 1 for a reason, see below.
    */
    runningSumCache = new int[nums.length + 1];

    /*
      Populate the running sum cache. This is wha this looks like:

      start...
      index        0  1  2
      nums:      [ 1, 2, 3 ]
      cache:  [ 0, 0, 0, 0 ]
      index     0  1  2  3

      i = 0
      index        0  1  2
      nums:      [ 1, 2, 3 ]
      cache:  [ 0, 1, 0, 0 ]   index 1 of the cache becomes nums[0] + runningSumCache[0]
      index     0  1  2  3

      i = 1
      index        0  1  2
      nums:      [ 1, 2, 3 ]
      cache:  [ 0, 1, 3, 0 ]   index 2 of the cache becomes nums[1] + runningSumCache[1]
      index     0  1  2  3

      i = 2
      index        0  1  2
      nums:      [ 1, 2, 3 ]
      cache:  [ 0, 1, 3, 6 ]   index 3 of the cache becomes nums[2] + runningSumCache[2]
      index     0  1  2  3

      i = 3 stop. the length is 3.
      3 < 3 = false.
    */
    for (int i = 0; i < nums.length; i++) {
      runningSumCache[i + 1] += nums[i] + runningSumCache[i];
    }

  }
  
  /*
    After building the cache we have O(1) access to range sums.

    index        0  1  2
    nums:      [ 1, 2, 3 ]
    cache:  [ 0, 1, 3, 6 ]
    index     0  1  2  3

    Give me the sum from index 1 to index 2.

    j = 2
    i = 1

    Look at the cache:

    cache:  [ 0, 1, 3, 6 ]
    index     0  1  2  3

    We know the sum will be 5. What do we need to subtract?

    6 is at index 3. 1 is at index 1.

    j = 2
    i = 1

    So our answer will be A[j + 1] - A[i].

    If we were indexing off of 0 then we would have to do
    A[j] - A[i - 1] but we choose to add a position at the
    front of the array to make things easier.
  */
  public int sumRange(int i, int j) {
    return runningSumCache[j + 1] - runningSumCache[i];
  }

}
