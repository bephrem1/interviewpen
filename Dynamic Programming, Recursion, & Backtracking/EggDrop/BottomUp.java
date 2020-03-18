/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int eggDrop(int totalEggs, int totalFloors) {
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
