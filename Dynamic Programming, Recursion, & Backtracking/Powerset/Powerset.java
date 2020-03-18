/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<List<Integer>> subsets(int[] inputSet) {
    List<List<Integer>> powerset = new ArrayList<>();
    generatePowerset(0, new ArrayList<>(), inputSet, powerset);
    return powerset;
  }

  /*
    Our fundamental choice is on each index of the array. These are our
    "slots".

    We have 2 choices: include the item in the final powerset or don't
    include the item in the final powerset.

    We express the first choice, recurse, remove the item we added to
    represent that choice, & then we recurse again without the item in
    the powerset.

    One path example:

    [ 1, 2, 3 ]

    n = 3 & we need to choose on each slot. The "slots" are like so:

    [ _ _ _ ]

    So the first path expressed will look like so:

    [ _ _ _ ]   currentIndex = 0
    Include 1
    [ 1 _ _ ]

    [ 1 _ _ ]   currentIndex = 1
    Include 2
    [ 1 2 _ ]

    [ 1 2 _ ]   currentIndex = 2
    Include 3
    [ 1 2 3 ]

    [ 1 2 3 ]   currentIndex = 3
    Final powerset = { 1, 2, 3 }

    Then we backtrack to this state:

    [ 1 2 _ ]   currentIndex = 2

    and we choose to then NOT include 3, the 2nd choice at that state:

    [ 1 2 _ ]   currentIndex = 3
    Final powerset = { 1, 2 }

    The backtracking continues in this fashion until the whole decision
    space is expressed.
  */
  private void generatePowerset(
    int currentIndex,
    List<Integer> selectedSoFar,
    int[] inputSet,
    List<List<Integer>> powerset
  ) {
    /*
      Base case: when we have made n decisions then we have
      expressed a 'path'. We reap that path here and return
      to continue the recursion.
    */
    if (currentIndex == inputSet.length) {
      powerset.add(new ArrayList<>(selectedSoFar));
      return;
    }
    
    /*
      Recurse WITH the item at 'currentIndex' in the powerset we
      are working on.
    */
    selectedSoFar.add(inputSet[currentIndex]);
    generatePowerset(currentIndex + 1, selectedSoFar, inputSet, powerset);
    
    // When the recursion returns, remove the choice we made
    selectedSoFar.remove(selectedSoFar.size() - 1);
    
    /*
      Recurse WITHOUT the item at 'currentIndex' in the powerset
      we are working on.
    */
    generatePowerset(currentIndex + 1, selectedSoFar, inputSet, powerset);
  }
}
