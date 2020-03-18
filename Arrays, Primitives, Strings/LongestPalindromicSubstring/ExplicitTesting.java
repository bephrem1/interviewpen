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

      for (int i = 0; i < s.length() - 1; i++) {
        expandPalindrome(i, i); // 1 char in middle
        
        if (s.charAt(i) == s.charAt(i + 1)) {
          expandPalindrome(i, i + 1); // 2 chars in middle
        }
      }
    }

    public String longestPalindrome() {
      if (s.length() == 0) {
        return "";
      }

      return s.substring(maxPalindromeLeft, maxPalindromeRight + 1);
    }

    private void expandPalindrome(int left, int right) {
      while (
        left - 1 >= 0 &&
        right + 1 <= s.length() - 1 &&
        s.charAt(left - 1) == s.charAt(right + 1)
      ) {
        left--;
        right++;
      }

      int currentBestLength = maxPalindromeRight - maxPalindromeLeft + 1;
      int extendedPalindromeLength = right - left + 1;
      if (extendedPalindromeLength > currentBestLength) {
        maxPalindromeLeft = left;
        maxPalindromeRight = right;
      }
    }
  }
}