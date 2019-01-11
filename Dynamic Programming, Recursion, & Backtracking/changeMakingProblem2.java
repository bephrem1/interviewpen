/*
  Coin Change 2 - LeetCode: https://leetcode.com/problems/coin-change-2/

  All credit for code goes to user @tankztc:
  https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space

  This code passes all Leetcode test cases as of Jan. 11 2019
  Runtime: 13ms

  Note: This code shows the use of O(mn) space. This problem can be solved using
  only O(m) space. See the links above.

  The video to explain this code is here: [a link will live here someday]
*/

public int change(int amount, int[] coins) {

  /*
    Each row represents using the denominations up to that point in
    the denominations array (see the video explanation)
  */
  int[][] dp = new int[coins.length+1][amount+1];

  /*
    Max ways to make change for 0 will be 1, doing nothing.
  */
  dp[0][0] = 1;
  
  for (int i = 1; i <= coins.length; i++) {

    /*
      Set the subproblem for the amount of 0 to 1 when
      solving this row
    */
    dp[i][0] = 1;

    for (int j = 1; j <= amount; j++) {
      
      int currentCoinValue = coins[i-1];

      /*
        dp[i][j] will be the sum of the ways to make change not considering
        this coin (dp[i-1][j]) and the ways to make change considering this
        coin (dp[i][j] - currentCoinValue] ONLY if currentCoinValue <= j, otherwise
        this coin can not contribute to the total # of ways to make change at this
        sub problem target amount)
      */
      int withoutThisCoin = dp[i-1][j];
      int withThisCoin = currentCoinValue <= j ? dp[i][j] - currentCoinValue] : 0;

      dp[i][j] = withoutThisCoin + withThisCoin;
    }
  }

  /*
    The answer considering ALL coins for the FULL amount is what
    we want to return as the answer
  */
  return dp[coins.length][amount];
}
