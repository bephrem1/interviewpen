/*
  The 0/1 Knapsack Problem solved with dynamic programming.

  Given n items, each with a weight & value, pick a subset of the items that
  maximizes value and stays below a weight constraint.
*/

public class Knapsack01 {
  public static void main(String args[]) {
    int[] values = new int[]{ 60, 100, 120, 80, 30 };
    int[] weights = new int[]{ 10, 20, 30, 40, 50 };

    int maxWeight = 400;

    int[][] dpCache = new int[values.length + 1][maxWeight + 1]; // rows => consider items 0...(total items), cols => weights 0...(max weight)
    System.out.println(knapsackBottomUp(values, weights, maxWeight, dpCache)); // 390
    printCache(dpCache);
    newline();

    System.out.println(knapsackTopDownNoMemoization(values, weights, maxWeight, values.length)); // 390
    newline();

    int[][] dpCache2 = new int[values.length + 1][maxWeight + 1];
    System.out.println(knapsackTopDown(values, weights, maxWeight, values.length, dpCache2)); // 390
    printCache(dpCache2);
    newline();
  }

  private static int knapsackBottomUp(int[] values, int[] weights, int maxWeightConstraint, int[][] cache) {
    for (int totalItems = 0; totalItems <= values.length; totalItems++) {
      for (int maxWeight = 0; maxWeight <= maxWeightConstraint; maxWeight++) {
        /*
          I cache the name 'currentItem' for clarity when accessing values & weights, but I
          leave 'totalItems - 1' "raw" when we access the cache so you can visualize:
            1.) Without Item -> Going up 1 row
            2.) With Item -> Go up 1 row & left 'weights[totalItems - 1]' columns
        */
        int currentItem = totalItems - 1;

        if (totalItems == 0 || maxWeight == 0) {
          cache[totalItems][maxWeight] = 0;
        } else if (weights[currentItem] > maxWeight) {
          cache[totalItems][maxWeight] = cache[totalItems - 1][maxWeight];
        } else {
          int withItem = values[currentItem] + cache[totalItems - 1][maxWeight - weights[totalItems - 1]];
          int withoutItem = cache[totalItems - 1][maxWeight];

          cache[totalItems][maxWeight] = Math.max(withItem, withoutItem);
        }
      }
    }

    return cache[values.length][maxWeightConstraint];
  }

  /*
    Remember, top-down just simulates the bottom-up except our base cases
    "catch us" with the recursion. Bottom up we "initializes" the table
    with the base cases so that we can do just that, solve bottom up.
  */
  private static int knapsackTopDownNoMemoization(int[] values, int[] weights, int maxWeight, int totalItems) {
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

  private static int knapsackTopDown(int[] values, int[] weights, int maxWeight, int totalItems, int[][] cache) {
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

  private static void printCache(int[][] cache) {
    for (int[] row: cache) {
      for (int col: row) {
        System.out.print(col + " ");
      }
      System.out.println();
    }
  }

  private static void newline() {
    System.out.println();
  }
}
