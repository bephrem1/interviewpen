/*
  Longest Increasing Subsequence - LeetCode: https://leetcode.com/problems/longest-increasing-subsequence

  This code passes all Leetcode test cases as of Jan. 6 2019
  Runtime: 23 ms*, faster than 29.91% of Java online submissions for Longest Increasing Subsequence.

  * Note: There is a O(n * log(n)) solution to this problem. But I cover this methods because it is
  more practical that one would come up with this approach in a real interview.

  The video to explain this code is here: [a link will live here someday]

  PS: The only difference between LIS and LNDS (Longest Non-Decreasing Subsequence) is ONE CHANGE.
  We change "nums[i] > nums[j]" to "nums[i] >= nums[j]". We basically just change the conditions that
  allow us to consider an item for lengthening any longest subsequence already found.
*/

public int lengthOfLIS(int[] nums) {
  
  /*
    Leetcode throws empty states at us in test cases. We can check
    them here or at return with a ternary operator. Either.
  */
  if (nums.length == 0) {
    return 0;
  }

  // Array to store our subproblems, default answer is 1
  int[] maxLength = new int[nums.length];
  Arrays.fill(maxLength , 1);

  // By default the best answer is a length of 1
  int maximumSoFar = 1;
  
  /*
    Solve the LIS subproblem for each snippet
    of the array ending between 1, 2, 3, ... and
    so on until nums.length - 1 (inclusive)
    
    Ex:
    
    [-2, 1, 2, 3]
    
    [-2] from index 0 to index 0
    [-2, 1] from index 0 to index 1
    [-2, 1, 2] from index 0 to index 2
    [-2, 1, 2, 3] from index 0 to index 3
    
    Our answer is the maximum LNDS found between
    all subproblems we solve along the way.
  */
  for (int i = 1; i < nums.length; i++) {

    /*
      At each snippet previous to the present subproblem, we
      see if we can append the item at nums[i].
      
      If we can we see which is greater between:

      maxLength[i]: The best answer so far for the snippet
      from 0 to i (includes both boundaries)

      and

      maxLength[j] + 1): We append this item to the LIS found
      from 0 to j (includes both boundaries). maxLength[j] has
      the LIS for this snippet. We add 1 because we extend the
      sequence by one item. The item we are solving the subproblem
      for.
    */
    for (int j = 0; j < i; j++){
      if (nums[i] > nums[j]) {
        maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
      }
    }
    
    /*
      We now have an answer for the subproblem going from
      0 to i (inclusive). Compete it against the LIS found
      so far
    */
    maximumSoFar = Math.max(maximumSoFar, maxLength[i]);
  }

  return maximumSoFar;
}
