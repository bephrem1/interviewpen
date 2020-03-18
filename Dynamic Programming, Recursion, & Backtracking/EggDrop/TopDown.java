/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int eggDrop(int totalEggs, int totalFloors) {
    int[][] cache = new int[totalEggs + 1][totalFloors + 1];
    initializeCache(totalEggs, totalFloors, cache);

    return eggDrop(totalEggs, totalFloors, cache);
  }

  private int eggDrop(int totalEggs, int totalFloors, int[][] cache) {
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

        int costOfWorstOutcome = Math.max(eggDrop(totalEggs - 1, floor - 1, cache),
                                          eggDrop(totalEggs, totalFloors - floor, cache));

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