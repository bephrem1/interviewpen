/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int knapsackBottomUp(int[] values, int[] weights, int maxWeightConstraint, int[][] cache) {
    for (int totalItems = 0; totalItems <= values.length; totalItems++) {
      for (int maxWeight = 0; maxWeight <= maxWeightConstraint; maxWeight++) {
        /*
          I cache the name 'currentItem' for clarity when accessing values & weights, but I
          leave 'totalItems - 1' "raw" when we access the cache so you can visualize:
            1.) Without Item -> Going up 1 row
            2.) With Item -> Go up 1 row & left 'weights[currentItem]' columns
        */
        int currentItem = totalItems - 1;

        if (totalItems == 0 || maxWeight == 0) {
          cache[totalItems][maxWeight] = 0;
        } else if (weights[currentItem] > maxWeight) {
          cache[totalItems][maxWeight] = cache[totalItems - 1][maxWeight];
        } else {
          int withItem = values[currentItem] + cache[totalItems - 1][maxWeight - weights[currentItem]];
          int withoutItem = cache[totalItems - 1][maxWeight];

          cache[totalItems][maxWeight] = Math.max(withItem, withoutItem);
        }
      }
    }

    return cache[values.length][maxWeightConstraint];
  }
}
