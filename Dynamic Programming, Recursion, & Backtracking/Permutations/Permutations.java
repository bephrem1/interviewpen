/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<List<Integer>> permute(int[] originalArray) {
    List<List<Integer>> allPermutations = new ArrayList<>();
    generateAllPermutations(new ArrayList<>(), originalArray, allPermutations);
    return allPermutations;
  }

  private void generateAllPermutations(
    List<Integer> runningChoices,
    int[] originalArray,
    List<List<Integer>> allPermutations
  ) {
    if (runningChoices.size() == originalArray.length) {
      allPermutations.add(new ArrayList<>(runningChoices));
      return;
    }

    /*
      Every stack frame of this function call represents the expression of trying (almost) all items in every "slot" in the array.
      The recursion stops when we are choosing on 1 past the final "slot".
    */
    for (int i = 0; i < originalArray.length; i++) {
      int choice = originalArray[i];

      // Skip if element already exists in 'runningChoices'
      if (runningChoices.contains(choice)) {
        continue;
      }

      // 1.) Choose - Add the item to the 'runningChoices'
      runningChoices.add(choice);

      // 2.) Explore - Recurse on the choice
      generateAllPermutations(runningChoices, originalArray, allPermutations);

      // 3.) Unchoose - We have returned from the recursion, remove the choice we made.
      // The next iteration will try another item in the "slot" we are working on.
      runningChoices.remove(runningChoices.size() - 1);
    }
  }
}
