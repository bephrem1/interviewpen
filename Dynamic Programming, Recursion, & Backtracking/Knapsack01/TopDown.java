/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int knapsackTopDown(int[] values, int[] weights, int maxWeight, int totalItems, int[][] cache) {
    if (totalItems == 0 || maxWeight == 0) {
      return 0;
    }

    if (cache[totalItems][maxWeight] != 0) {
      return cache[totalItems][maxWeight];
    }

    int currentItemIndex = totalItems - 1;
    if (weights[currentItemIndex] > maxWeight) {
      cache[totalItems][maxWeight] = knapsackTopDown(values, weights, maxWeight, totalItems - 1, cache);

      return cache[totalItems][maxWeight];
    }

    int withItem = values[currentItemIndex] + knapsackTopDown(values, weights, maxWeight - weights[currentItemIndex], totalItems - 1, cache);
    int withoutItem = knapsackTopDown(values, weights, maxWeight, totalItems - 1, cache);

    cache[totalItems][maxWeight] = Math.max(withItem, withoutItem);

    return cache[totalItems][maxWeight];
  }
}
