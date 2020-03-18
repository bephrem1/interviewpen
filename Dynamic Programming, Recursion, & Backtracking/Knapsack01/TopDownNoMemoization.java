/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int knapsackTopDownNoMemoization(int[] values, int[] weights, int maxWeight, int totalItems) {
    /*
      If we have no items to use or have to solve the problem with
      a weight upper bound of 0 then the total value that can be
      made is 0. Not items can be choosen.
    */
    if (totalItems == 0 || maxWeight == 0) {
      return 0;
    }

    /*
      Can the current item be used? If it is heavier than the remaining capacity
      to fill, it cannot be considered. We just move on to reducing the subproblem
      to only consider all items behind this item in the knapsack (the array).
    */
    int currentItemIndex = totalItems - 1;
    if (weights[currentItemIndex] > maxWeight) {
      return knapsackTopDownNoMemoization(values, weights, maxWeight, totalItems - 1);
    }

    /*
      The answer to this subproblem is the better weight between:
        1.) Using the current item:
          a.) Add its value
          b.) Determine subproblem answer with target weight reduced & total items considered reduced
          (this item no longer considered)
        2.) Not using the current item
          a.) Don't use the item's value
          b.) Determine subproblem answer with total items considered reduced (this item no longer considered)
    */
    int withItem = values[currentItemIndex] + knapsackTopDownNoMemoization(values, weights, maxWeight - weights[currentItemIndex], totalItems - 1);
    int withoutItem = knapsackTopDownNoMemoization(values, weights, maxWeight, totalItems - 1);

    return Math.max(withItem, withoutItem);
  }
}
