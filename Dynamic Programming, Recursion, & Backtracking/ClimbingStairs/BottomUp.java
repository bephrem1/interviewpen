/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int climbStairs(int n) {
    /*
      In programming we all know we index off of 0. This is why
      we create an array of size n + 1. It is so we can just return
      dp[n] at the end instead of fumbling with dp[n - 1].

      If n = 4 we will get an array like this if we just did "new int[n];":
      [0, 0, 0, 0]
       0  1  2  3

      If we instead do "new int[n + 1" we have:
      [0, 0, 0, 0, 0]
       0  1  2  3  4

       And now we can be literal in how we access the nth subproblem
    */
    int[] dp = new int[n + 1];

    // n = 0, the answer is 1. We can only take no steps
    dp[0] = 1;

    // n = 1, the answer is 1. We can only take 1 step
    dp[1] = 1;

    /*
      The answer to the ith subproblem is the sum between the answer
      to the subproblems of climbing i - 1 stairs and i - 2 stairs
    */
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }

    /*
      This is what we want and built to the while way. The answer for
      the total unique ways to climb n steps when we can either take a
      1 step or 2 step
    */
    return dp[n];
  }
}
