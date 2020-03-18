/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int coinChange(int[] coins, int amount) {
    if (amount < 1) {
      return 0;
    }

    return coinChange(coins, amount, new int[amount + 1]);
  }

  private int coinChange(int[] coins, int remainder, int[] dp) {
    /*
      Minimum coins to make change for a negative amount is -1.
      This is just a base case we arbitrarily define.
    */
    if (remainder < 0) {
      return -1;
    }
  
    /*
      The minimum coins needed to make change for 0 is always 0
      coins no matter what coins we have.
    */
    if (remainder == 0) {
      return 0;
    }

    // We already have an answer cached. Return it.
    if (dp[remainder] != 0) {
      return dp[remainder];
    }

    /*
      No answer yet. Try each coin as the last coin in the change that
      we make for the amount
    */
    int minimum = Integer.MAX_VALUE;
    for (int coin: coins) {
      int changeResult = coinChange(coins, remainder - coin, dp);

      /*
        If making change was possible (changeResult >= 0) and 
        the change result beats our present minimum, add one to
        that smallest value
        
        We accept that coin as the last coin in our change making
        sequence up to this point since it minimizes the coins we
        need
      */
      if (changeResult >= 0 && changeResult < minimum) {
        minimum = 1 + changeResult;
      }
    }

    /*
      If no answer is found (minimum == Integer.MAX_VALUE) then the
      sub problem answer is just arbitrarily made to be -1, otherwise
      the sub problem's answer is "minimum"
    */
    dp[remainder] = (minimum == Integer.MAX_VALUE) ? -1 : minimum;

    return dp[remainder];
  }
}