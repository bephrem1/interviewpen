class TopDownWithoutMemoization {

  public int climbStairs(int n) {
    return climbStairsHelper(n);
  }

  public int climbStairsHelper(int n) {

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

class TopDownWithMemoization {

  public int climbStairs(int n) {
    int memo[] = new int[n + 1];
    return climbStairsHelper(n, memo);
  }

  public int climbStairsHelper(int n, int memo[]) {

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

class BottomUp {

  public int climbStairs(int n) {

    /*
      In programming we all know we index off of 0. This is why
      we create an array of size n + 1. It is so we can just return
      dp[n] at the end instead of fumbling with dp[n - 1].

      If n = 4 we will get an array like this if we just did "new int[n];":
      [0, 0, 0, 0]
       0  1  2  3

      If we instead do "new int[n + 1]" we have:
      [0, 0, 0, 0, 0]
       0  1  2  3  4

       And now we can be literal in how we access the nth subproblem
    */
    int[] dp = new int[n + 1];

    /*
      n = 0, the answer is 1. We can only take no steps.
    */
    dp[0] = 1;

    /*
      n = 1, the answer is 1. We can only take 1 step.
    */
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
