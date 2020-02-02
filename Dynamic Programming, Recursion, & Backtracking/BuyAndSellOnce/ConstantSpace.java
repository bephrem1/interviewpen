class Solution {
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }

    // Only 1 thing matters: lowest price seen so far, 'globalMin'
    int globalMax = 0;
    int globalMin = prices[0];

    for (int i = 1; i < prices.length; i++) {
      globalMin = Math.min(globalMin, prices[i]);
      globalMax = Math.max(globalMax, prices[i] - globalMin);
    }

    return globalMax;
  }
}
