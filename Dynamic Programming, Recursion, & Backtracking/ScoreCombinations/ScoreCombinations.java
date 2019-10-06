/*
  You are given the final score of a football (American) game
  where there are 2, 3 & 7 point plays.

  Write a generic program that determines the total ways that
  the final score can be reached with the point values of each
  possible scoring event.
*/

public class ScoreCombinations {
  public static void main(String args[]) {
    int score = 12;
    int[] pointEvents = new int[]{ 2, 3, 7 };

    System.out.println(totalWaysToReachScore(score, pointEvents));
  }

  private static int totalWaysToReachScore(int finalScore, int[] pointEvents) {
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

    printCache(waysCache);

    return waysCache[pointEvents.length][finalScore];
  }

  private static void printCache(int[][] cache) {
    for (int[] row: cache) {
      for (int col: row) {
        System.out.print(col + " ");
      }
      System.out.println();
    }
  }
}
