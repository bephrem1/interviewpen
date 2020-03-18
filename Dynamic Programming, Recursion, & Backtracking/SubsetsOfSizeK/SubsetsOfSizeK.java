/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> combinations = new ArrayList<>();
    exploreCombinations(1, n, k, new ArrayList<>(), combinations);
    return combinations;
  }

  private void exploreCombinations(
    int offset,
    int n,
    int k,
    List<Integer> partialCombination,
    List<List<Integer>> combinations
  ) {
    /*
      If the partial combination we are building reaches the
      maximum size (which is k), collect the result and return
      so that the function that called us can express more decisions
      and possibilities.
    */
    if (partialCombination.size() == k) {
      combinations.add(new ArrayList<>(partialCombination));
      return;
    }
    
    /*
      This problem is the same as the 'powerset' problem except it
      introduces the concept of 'pruning' our recursion tree. We could
      generate all 2^n subsets and only pick the subsets of size k.

      Instead of having n 'slots' to populate, we now have k 'slots'
      to populate. Each slot can contain any number from 1 to n.

      But we are restricted.
      
      If we choose 3 as the first item, then the possible items that
      can be placed in the next slot will be from 4...n.
      
      This mirrors the reduction of decision space we see in the 'permutations'
      problem.

      Thus, in each stack frame we loop from a number called 'offset' to
      n (because our decision space is i...n, if I choose i, the next recursive
      call can choose any number from (i+1)...n)

      k - partialCombination.size():
        This is the # of unfilled 'slots' in the size k combination set we
        are building.

      n - i + 1:
        This expresses the total numbers between i and n (including i & n). So
        the number of for loop iterations left to run.
        So if:
          n = 4 & i = 2 ->
          4 - 2 + 1 = 3 ->
          { 2, 3, 4 } which is length 3

      spaceLeftInSnippet <= n - i + 1:
        This is to assert that we can continue looping as long there are more
        choices left than 'slots' to fill. This is choice abundance. Imagine:

        n = 100
        k = 10
        
        and our state is:

        [ 95 _ _ _ _ _ _ _ _ _ ]
            ^
        Our for loop will start at 96 when expressing the possibility space for
        this "slot".

        We are now choosing for that slot. But now our possibility space is
        96...100

        spaceLeftInSnippet = 10 - 1 = 9
        n - i + 1 = 100 - 96 + 1 = 5 -> { 96, 97, 98, 99, 100 } which is length 5

        So if we recurse we will waste time. It is impossible to finish the k slots
        from this position, this conditional prunes the recursion.
    */
    final int spaceLeftInSnippet = k - partialCombination.size();
    for (int i = offset; i <= n && spaceLeftInSnippet <= n - i + 1; i++) {

      // 1.) Choose - Add the i'th number in the iteration
      partialCombination.add(i);

      // 2.) Explore - Recurse into our decision
      exploreCombinations(i + 1, n, k, partialCombination, combinations);

      // 3.) Unchoose - Remove the item we just explored on
      partialCombination.remove(partialCombination.size() - 1);
    }
  }
}
