/*
  Super Egg Drop - LeetCode: https://leetcode.com/problems/super-egg-drop
  Egg Dropping Puzzle | DP-11 - GeeksforGeeks: https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/

  The video to explain this code is here: [a link to a video will live here someday]
*/

/*
  This is what the dynamic programming table looks like for 27 floors
  and 6 eggs.

  Each cell is the MINIMUM...WORST...amount of drops that we will need
  to guarantee that we can find the pivotal floor where eggs begin to
  break given 'row' eggs and 'col' floors.

  Eggs are on the left 'rows'. Floors are on the top 'cols'.

                                                  Floors
        0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27
     0  0  0  0  0  0  0  0  0  0  0  0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0   0
     1  0  1  2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27
  e  2  0  1  2  2  3  3  3  4  4  4  4   5   5   5   5   5   6   6   6   6   6   6   7   7   7   7   7   7
  g  3  0  1  2  2  3  3  3  3  4  4  4   4   4   4   4   5   5   5   5   5   5   5   5   5   5   5   6   6
  g  4  0  1  2  2  3  3  3  3  4  4  4   4   4   4   4   4   5   5   5   5   5   5   5   5   5   5   5   5
  s  5  0  1  2  2  3  3  3  3  4  4  4   4   4   4   4   4   5   5   5   5   5   5   5   5   5   5   5   5
     6  0  1  2  2  3  3  3  3  4  4  4   4   4   4   4   4   5   5   5   5   5   5   5   5   5   5   5   5

*/

/*
  This code passes all Leetcode test cases as of Feb. 14 2019
  Runtime: Time Limit Exceeded

  Watch Out. If you print the cache you won't see what I have above. This
  is because of the path the recursion takes to solve the specific subproblem
  we are asking the code to solve

  Print the cache if you are curious to see what I mean
*/
class TopDown {

  public int superEggDrop(int totalEggs, int totalFloors) {
    int[][] cache = new int[totalEggs + 1][totalFloors + 1];
    initializeCache(totalEggs, totalFloors, cache);
    return superEggDrop(totalEggs, totalFloors, cache);
  }

  public int superEggDrop(int totalEggs, int totalFloors, int[][] cache) {

    /*
      Base Case 1:

      If we have 0 floors we need 0 trials, no matter the egg amount given
      If we have 1 floor we need 1 trial, no matter the egg amount given

      We just return totalFloors since it matches up to that logic
    */
    if (totalFloors == 1 || totalFloors == 0) {
      return totalFloors;
    }

    /*
      Base Case 2:

      If I have 1 egg...I will have to be conservative and try every floor
      1 by 1 starting from floor 1 all the way up to totalFloors

      This entails 'totalFloors' drops as the BEST we can do to guarantee
      we find the pivotal floor
    */
    if (totalEggs == 1) {
      return totalFloors;
    }

    /*
      Do we already know the answer to this subproblem?
    */
    if (cache[totalEggs][totalFloors] != Integer.MAX_VALUE) {
      return cache[totalEggs][totalFloors];
    }

    /*
      Try all floors up to the floor we are working on. See the bottom explanation
      below for how the situations break down

      This does not change the asymptotic complexity of the algorithm
    */
    for (int floor = 1; floor <= totalFloors; floor++) {

        int costOfWorstOutcome = Math.max(superEggDrop(totalEggs - 1, floor - 1, cache),
                                          superEggDrop(totalEggs, totalFloors - floor, cache));

        int accountingForDroppingAtThisSubproblem = 1 + costOfWorstOutcome;
        cache[totalEggs][totalFloors] = Math.min(cache[totalEggs][totalFloors],
                                                 accountingForDroppingAtThisSubproblem);
    }

    return cache[totalEggs][totalFloors];
  }

  /*
    Initialize all subproblems that need to be solved. This does not change asymptotic
    complexity of the solution since the work stays within our upper bound.

    Not sure if we really need to do this...eh...whatever you get the point
  */
  private void initializeCache(int totalEggs, int totalFloors, int[][] cache) {
    for (int eggs = 2; eggs <= totalEggs; eggs++) {
      for (int floor = 2; floor <= totalFloors; floor++) {
        cache[eggs][floor] = Integer.MAX_VALUE;
      }
    }
  }

}

/*
  This code passes all Leetcode test cases as of Feb. 14 2019
  Runtime*: 11339 ms, faster than 1.11% of Java online submissions for Super Egg Drop.
  Memory Usage: 40.4 MB, less than 100.00% of Java online submissions for Super Egg Drop.

  *Note: Leetcode offers solutions that are whole classes faster in terms of asymptotic
  complexity so do not be worried by this speed. The fastest appraoches are the most
  complex and most impossible to attain in an interview.
*/
class BottomUp {

  public int superEggDrop(int totalEggs, int totalFloors) {

    /*
      We do +1 to index off of 1. So that the final answer that
      we want will be at cache[totalEggs][totalFloors]...remember
      we index off of 0 so this is for convenience

      cache[totalEggs][totalFloors] is literally the answer to the
      subproblem given those literal amounts...'totalEggs' and
      'totalFloors'
    */
    int cache[][] = new int[totalEggs + 1][totalFloors + 1];

    /*
      If we have 0 floors we need 0 trials, no matter the egg amount given
      If we have 1 floor we need 1 trial, no matter the egg amount given
    */
    for (int eggs = 1; eggs <= totalEggs; eggs++) {
      cache[eggs][0] = 0;
      cache[eggs][1] = 1;
    }

    /*
      If we have 1 egg...no matter what floors we get, our approach will
      be to do 'floorAmount' drops...this is because we want to start from
      floor 1, drop...then go to floor 2, drop...and so on until we get to
      in the worst case 'floorAmount'

      Remember, we want to know the minimum amount of drops that will always
      work. So we want to MINIMIZE...to the absolute LEAST...worst...amount
      of drops so that our drop count ALWAYS works

      Any worse then the MINIMIZED WORST will be suboptimal
    */
    for (int floor = 1; floor <= totalFloors; floor++) {
      cache[1][floor] = floor;
    }

    /*
      Solve the rest of the subproblems now that we have base cases defined
      for us
    */
    for (int eggs = 2; eggs <= totalEggs; eggs++) {
      for (int floor = 2; floor <= totalFloors; floor++) {

        /*
          Initialize the answer to this subproblem to a very large
          value that will be easily overtaken and provide a hard upper
          comparison wall
        */
        cache[eggs][floor] = Integer.MAX_VALUE;

        /*
          We do a theoretical test on every floor from 1 to the 'floor'
          amount for this subproblem.

          At each 'attemptFloor' we express both possibilities described below
        */
        for (int attemptFloor = 1; attemptFloor <= floor; attemptFloor++) {

          /*
            We want to know the cost of the 2 outcomes:

            1.) We drop an egg and it breaks.
              We move 1 floor down. We have 1 less egg.

            2.) We drop an egg and it doesn't break.
              We have this many floors left: the difference between the total floors and our current
              floor. We have the same number of eggs.
          */
          int costOfWorstOutcome = Math.max(cache[eggs - 1][attemptFloor - 1],
                                            cache[eggs][floor - attemptFloor]);

          /*
            After we get the cost of the WORST outcome we add 1 to that worst outcome
            to simulate the fact that we are going to do a test from THIS subproblem.

            The answer to this problem is 1 PLUS the cost of the WORST SITUATION that
            happens after our action at this subproblem.
          */
          int accountingForDroppingAtThisSubproblem = 1 + costOfWorstOutcome;

          /*
            Did we reach a BETTER (lower) amount of drops that guarantee that we can
            find the pivotal floor where eggs begin to break?
          */
          cache[eggs][floor] = Math.min(cache[eggs][floor], accountingForDroppingAtThisSubproblem);

        }

      }
    }

    /*
      Remember we added +1 so we are indexed off of 1 now. We can reap our answer from
      cache[totalEggs][totalFloors] instead of cache[totalEggs - 1][totalFloors - 1]
    */
    return cache[totalEggs][totalFloors];
  }

}

/*
  This code passes all Leetcode test cases as of Feb. 14 2019
  Runtime:
*/
class TopDownWithBinarySearch {
  
}
