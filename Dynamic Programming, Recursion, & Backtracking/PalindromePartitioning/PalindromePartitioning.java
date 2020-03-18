/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<List<String>> partition(String s) {
    List<List<String>> decompositions = new ArrayList();

    decomposeString(0, s, new ArrayList<>(), decompositions);

    return decompositions;
  }

  private void decomposeString(
    int workingIndex,
    String s,
    List<String> partialDecomposition,
    List<List<String>> decompositions
  ) {
    /*
      If we have decomposed the whole string then reap the
      'partialDecomposition', it is now complete.
    */
    if (workingIndex == s.length()) {
      decompositions.add(new ArrayList<>(partialDecomposition));
      return;
    }

    /*
      Take every snippet take from the 'workingIndex' to the end of the
      string. This is out 'possibility space' that we can recurse into.
    */
    for (int i = workingIndex; i < s.length(); i++) {
      /*
        Only recurse if the snippet from 'workingIndex' (inclusive) to
        s.length() (inclusive) is a palindrome
      */
      if (isPalindrome(workingIndex, i, s)) {

        // 1.) Choose - Take the snippet & add it to our decomposition 'path'
        String palindromicSnippet = s.substring(workingIndex, i + 1);
        partialDecomposition.add(palindromicSnippet);

        /*
          2.) Explore - Recurse and advance progress 1 past right bound of 
          the 'palindromicSnippet' which is 'i + 1'
        */
        decomposeString(i + 1, s, partialDecomposition, decompositions);

        /*
          3.) Unchoose - We are done searching, remove the snippet from our
          'path'. Next loop iteration will try another snippet in this stack
          frame.
        */
        partialDecomposition.remove(partialDecomposition.size() - 1);
      }
    }
  }

  /*
    Checks if the region from left (inclusive) to right (inclusive) is
    a palindromic.
  */
  private boolean isPalindrome(int left, int right, String s) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }

      left++;
      right--;
    }

    return true;
  }
}
