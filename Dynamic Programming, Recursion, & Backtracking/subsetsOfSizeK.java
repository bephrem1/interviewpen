/*

  Leetcode Question: https://leetcode.com/problems/combinations/submissions/

  ALL credit for this code goes to the authors of "Elements of Programming
  Interviews" Adnan Aziz, Tsung-Hsien Lee, and Amit Prakash.

  As of Jan. 3 this code passes all Leetcode test cases.
  Runtime: 3 ms, faster than 97.74% of Java online submissions for Combinations.

  The video to explain this code is here: [a link will live here someday]

*/

public static List<List<Integer>> combine(int n, int k) {
  List<List<Integer>> result = new ArrayList<>();
  exploreCombinations(n, k, 1, new ArrayList<Integer>(), result);
  return result;
}

private static void exploreCombinations(int n, int k, int offset,
                                        List<Integer> snippet,
                                        List<List<Integer>> result) {

  if (snippet.size() == k) {
      result.add(new ArrayList <>(snippet));
      return;
  }
  
  final int numRemaining = k - snippet.size();
  for (int i = offset; i <= n && numRemaining <= n - i + 1 ; ++i) {
      snippet.add(i);
      exploreCombinations(n, k, i + 1, snippet , result);
      snippet.remove(snippet.size() - 1);
  }
  
}