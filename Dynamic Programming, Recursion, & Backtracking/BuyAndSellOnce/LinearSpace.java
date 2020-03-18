/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }

    /*
      opt[i] denotes the best profit that we can make
      if we sell on day i. The answer can be 1 of 2 options:

      1.) Lengthen opt[i - 1]: The best achieved yesterday
      with the profit we can reap waiting 1 more day (to
      sell today - day i)

      2.) Don't lengthen opt[i - 1]: If we decide to sell today
      (day i) we will lose money (go below $0 profit).

      To answer opt[i] we just decide to buy today (buy on day i)
      and we are back to a total profit of 0 ending at day i as
      we could not extend yesterday's profit without losing money.

      The Bigger Picture: We know that an optimal answer will end
      on one of these days. If we answer opt[i] for all days, we
      know we will capture the optimal solution when the algorithm
      is run.
    */
    int[] opt = new int[prices.length];
    opt[0] = 0;

    int globalMax = 0;
    for (int i = 1; i < prices.length; i++) {
      int profitDelta = prices[i] - prices[i - 1];

      opt[i] = Math.max(0, opt[i - 1] + profitDelta);
      globalMax = Math.max(globalMax, opt[i]);
    }

    return globalMax;
  }
}
