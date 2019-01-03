/*

  Leetcode Question: https://leetcode.com/problems/combinations/submissions/

  ALL credit for this code goes to the authors of "Elements of Programming
  Interviews" Adnan Aziz, Tsung-Hsien Lee, and Amit Prakash.

  As of Jan. 3 this code passes all Leetcode test cases.
  Runtime: 6 ms, faster than 92.25% of Java online submissions for Combinations.

  The video to explain this code is here: [a link will live here someday]

*/

public static List<List<Integer>> combine(int n, int k) {
  List<List<Integer>> result = new ArrayList<>();
  directedCombinations(n, k, 1, new ArrayList<Integer>(), result);
  return result;
}

private static void directedCombinations(int n, int k, int offset,
                                        List<Integer> partialCombination,
                                        List<List<Integer>> result) {

  if (partialCombination.size() == k) {
      result.add(new ArrayList <>(partialCombination));
      return;
  }
  
  final int numRemaining = k - partialCombination.size();
  for (int i = offset; i <= n && numRemaining <= n - i + 1 ; ++i) {
      partialCombination.add(i);
      directedCombinations(n, k, i + 1, partialCombination , result);
      partialCombination.remove(partialCombination.size() - 1);
  }
  
}