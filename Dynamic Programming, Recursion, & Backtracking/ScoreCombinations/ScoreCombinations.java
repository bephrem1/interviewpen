/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public int totalWaysToReachScore(int finalScore, int[] pointEvents) {
    /*
      The row indicates considering the row'th item for use. The column
      indicates the score amount to determine total combinations for.
    */
    int[][] waysCache = new int[pointEvents.length + 1][finalScore + 1];

    // There are no ways to make any combinations for any score having no point events
    for (int col = 0; col <= finalScore; col++) {
      waysCache[0][col] = 0;
    }

    // There is 1 way to reach score 0 given any amount of items, to not score at all
    for (int row = 1; row <= pointEvents.length; row++) {
      waysCache[row][0] = 1;
    }

    for (int row = 1; row <= pointEvents.length; row++) {
      for (int score = 1; score <= finalScore; score++) {
        int eventValue = pointEvents[row - 1];

        // Don't use this score value, the 'totalScore' to "make change" for remains intact
        int withoutThisScore = waysCache[row - 1][score];

        /*
          We use this score value, we can continue to use it so we don't go up a row,
          the 'totalScore' to "make change" decreases
        */
        int withThisScore = 0;
        if (eventValue <= score) {
          withThisScore = waysCache[row][score - eventValue];
        }

        waysCache[row][score] = withoutThisScore + withThisScore;
      }
    }

    return waysCache[pointEvents.length][finalScore];
  }
}
