/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public String longestPalindrome(String s) {
    PalindromeFinder pf = new PalindromeFinder(s);
    pf.search();

    return pf.longestPalindrome();
  }

  private class PalindromeFinder {
    String s;
    int maxPalindromeLeft;
    int maxPalindromeRight;

    public PalindromeFinder(String s) {
      this.s = s;
      maxPalindromeLeft = 0;
      maxPalindromeRight = 0;
    }

    public void search() {
      if (s.length() == 0) {
        return;
      }

      /*
        If s(i...j) (inclusive of both i & j) is a palindrome then the subproblem
        is 'true', 'false' otherwise.
      */
      boolean[][] isPalindrome = new boolean[s.length()][s.length()];
      for (int i = s.length() - 1; i >= 0; i--) {
        for (int j = i; j < s.length(); j++) {
          if (s.charAt(i) == s.charAt(j)) {
            int palindromeCandidateLength = j - i + 1;
            isPalindrome[i][j] = palindromeCandidateLength <= 3 || isPalindrome[i + 1][j - 1];

            int currentBestLength = maxPalindromeRight - maxPalindromeLeft + 1;
            if (isPalindrome[i][j] && palindromeCandidateLength > currentBestLength) {
              maxPalindromeLeft = i;
              maxPalindromeRight = j;
            }
          }
        }
      }
    }

    public String longestPalindrome() {
      if (s.length() == 0) {
        return "";
      }

      return s.substring(maxPalindromeLeft, maxPalindromeRight + 1);
    }
  }
}
