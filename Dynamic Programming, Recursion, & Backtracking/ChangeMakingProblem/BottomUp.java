/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int coinChange(int[] coins, int amount) {
    // We use this to fill the dp table with default values
    int max = amount + 1;

    // This table will store the answer to our sub problems
    int[] dp = new int[amount + 1];

    // Initialize the dp table
    Arrays.fill(dp, max);  

    /*
      The answer to making change with minimum coins for 0
      will always be 0 coins no matter what the coins we are
      given are
    */
    dp[0] = 0;

    // Solve every subproblem from 1 to amount
    for (int i = 1; i <= amount; i++) {
      // For each coin we are given
      for (int j = 0; j < coins.length; j++) {
        // If it is less than or equal to the sub problem amount
        if (coins[j] <= i) {
          // Try it. See if it gives us a more optimal solution
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }

    /*
      dp[amount] has our answer. If we do not have an answer then dp[amount]
      will be amount + 1 and hence dp[amount] > amount will be true. We then
      return -1.

      Otherwise, dp[amount] holds the answer
    */
    return dp[amount] > amount ? -1 : dp[amount];
  }
}
