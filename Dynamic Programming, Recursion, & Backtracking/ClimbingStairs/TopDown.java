/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int climbStairs(int n) {
    int memo[] = new int[n + 1];
    return climbStairsHelper(n, memo);
  }

  private int climbStairsHelper(int n, int memo[]) {
    /*
      0 distinct ways to climb negative steps if we
      can only take 1 or 2 steps
    */
    if (n < 0) {
      return 0;
    }

    /*
      1 distinct way to climb 1 if we can only take 1
      or 2 steps.

      We take 1 step.
    */
    if (n == 0) {
      return 1;
    }

    /*
      Do we already have an answer to this question (subproblem)?
      If not fall through and compute, BUT if we already know it
      just return it from the cache
    */
    if (memo[n] > 0) {
      return memo[n];
    }

    /*
      The answer to this subproblem is the sum of the answer to the
      subproblems n - 1 and n - 2

      This drills us towards our base cases that bring us back up with
      an answer

      We cache the answer before returning it so we have it later
    */
    memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);

    return memo[n];
  }
}
