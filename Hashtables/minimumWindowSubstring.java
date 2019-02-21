/*
  Minimum Window Substring - LeetCode: https://leetcode.com/problems/minimum-window-substring/

  The video to explain this code is here: [a link will live here someday]
*/

/*
  Approach 1: Complete Search. Search all windows.
  This code passes all Leetcode test cases as of Feb. 21 2019
  Runtime: 
*/
public String minWindow(String searchString, String t) {

  int n = searchString.length();

  int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
  String minWindow = "";

  /*
    Explore all left boundaries for windows. AT EACH planting of
    a left boundary, explore all right boundaries.

    This is how we explore all windows of substrings we can take.
  */
  for (int left = 0; left < n; left++) {
    for (int right = left; right < n; right++) {

      /*
        Take the snippet that will give us the window we want to
        investigate. Do 'right + 1' since .substring excludes upper
        index, so basically we get a snippet from index 'left' ro 'right'
      */
      String windowSnippet = searchString.substring(left, right + 1);

      /*
        Test the window
      */
      boolean windowContainsAllChars = stringContainsAllCharacters(windowSnippet, t);

      /*
        If it satisfies and is smaller than the 'minWindowLengthSeenSoFar', update the
        'minWindowLengthSeenSoFar' and the minWindow string
      */
      if (windowContainsAllChars && windowSnippet.length() < minWindowLengthSeenSoFar) {
        minWindowLengthSeenSoFar = windowSnippet.length();
        minWindow = windowSnippet;
      }

    }
  }

  /*
    Return the minWindow. If no window satisfies we will end up returning the "" anyway
    since that was minWindow's default value and it would never have gotten set
  */
  return minWindow;
}

private boolean stringContainsAllCharacters(String searchString, String t) {

  /*
    For each character in t, ensure that it exists in the 'searchString'
  */
  for (char c : t.toCharArray()) {
    if (searchString.indexOf(c) == -1) {
      return false;
    }
  }

  return true;
}

/*
  Approach 2:
  This code passes all Leetcode test cases as of Feb. 21 2019
  Runtime:
*/


/*
  Approach 3:
  This code passes all Leetcode test cases as of Feb. 21 2019
  Runtime:
*/

