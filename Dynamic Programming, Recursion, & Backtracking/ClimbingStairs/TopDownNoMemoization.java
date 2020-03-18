/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int climbStairs(int n) {
    return climbStairsHelper(n);
  }

  private int climbStairsHelper(int n) {
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
      The answer to this subproblem is the sum of the answer to the
      subproblems n - 1 and n - 2

      This drills us towards our base cases that bring us back up with
      an answer
    */
    return climbStairsHelper(n - 1) + climbStairsHelper(n - 2);
  }
}
