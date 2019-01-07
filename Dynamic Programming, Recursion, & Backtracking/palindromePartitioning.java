/*
  Palindrome Partitioning - LeetCode: https://leetcode.com/problems/palindrome-partitioning/

  An adaption of the answer from user "UpTheHell" on Leetcode.
  Link: https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java%3A-Backtracking-solution.

  Revision by Benyam Ephrem (Jan. 7th 2019)
    > Spacing and naming fixes
    > Tiny style changes

  This code passes all Leetcode test cases as of Jan. 7th 2019
  Runtime: 7 ms, faster than 38.34% of Java online submissions for Palindrome Partitioning.

  The video to explain this code is here: [a link will live here someday]
*/

public List<List<String>> partition(String s) {
  List<List<String>> validDecompositions = new ArrayList();
  List<String> decompInProgress = new ArrayList<String>();
  decomposeString(s, 0, decompInProgress, validDecompositions); // Kick off the recursion
  return validDecompositions;
}

/*
  1.) Take all palindrome snippets from where we are
  2.) Recurse on them
  3.) When base case is hit, we add the answer and backtrack to keep going
*/
public void decomposeString(String s, int buildPointer, List<String> decompInProgress,
                List<List<String>> validDecompositions){

  if(buildPointer == s.length()) {
    validDecompositions.add(new ArrayList<>(decompInProgress));
  } else {

    /*
      Check ever snippet take from the buildPointer to the end of the
      string.
    */
    for(int i = buildPointer; i < s.length(); i++){

      /*
        Only recurse if the snippet we COULD take is a palindrome
      */
      if(isPalindrome(s, buildPointer, i)){

        /*
          Take the snippet now that we know it will be palindromic.
          WATCH OUT FOR OFF BY 1! We add 1 to i for the upper bound
          since substring() in the Java API EXCLUDES the right bound.
        */
        String palindromicSnippet = s.substring(buildPointer, i + 1);

        // Add the snippet to our decomposition that we are working on
        decompInProgress.add(palindromicSnippet);

        // Recurse and advance progress
        decomposeString(s, i + 1, decompInProgress,validDecompositions);

        /*
          We are done searching, remove the snippet from our progress. Next
          loop iteration continues our progress in this stack frame
        */
        decompInProgress.remove(decompInProgress.size() - 1);
      }

    }

  }
}

/*
  Helper. Just checks if the region from low (inclusive) to
  high (inclusive) is a palindrome.
*/
public boolean isPalindrome(String s, int low, int high){

  while(low < high) {
    if(s.charAt(low++) != s.charAt(high--)) {
      return false;
    }
  }

  return true;
}
