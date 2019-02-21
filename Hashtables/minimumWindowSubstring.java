/**
  Minimum Window Substring - LeetCode: https://leetcode.com/problems/minimum-window-substring/

  @author Benyam Ephrem

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
  Build a mapping to put all of t's characters inside
  */
  Map<Character, Integer> requiredCharacters = new HashMap<Character, Integer>();

  /*
    Iterate over every character in t
  */
  for (int i = 0; i < t.length(); i++) {

    /*
      Get the current character from the hashtable. If it exists we will get the
      current occurrence count. If it doesn't exist we will get 0 (the default).
    */
    int occurrencesOfCharacter = requiredCharacters.getOrDefault(t.charAt(i), 0);

    /*
      Increment the occurrences of the character and update the mapping
    */
    requiredCharacters.put(t.charAt(i), occurrencesOfCharacter + 1);

  }

  /*
    Go over the search string and eliminate characters from the hashtable
  */
  for (int i = 0; i < searchString.length(); i++) {

    // Extract the current character
    char curChar = searchString.charAt(i);

    // Is there a match to the required characters?
    if (requiredCharacters.containsKey(curChar)) {

      // Calculate what the new occurrence count will be
      int newOccurrenceCount = requiredCharacters.get(curChar) - 1;

      /*
        If we have satisfied all of the characters for this character,
        remove the key from the hashtable.

        Otherwise, just update the mapping with 1 less occurrence to
        satisfy for
      */
      if (newOccurrenceCount == 0) {
        requiredCharacters.remove(curChar);
      } else {
        requiredCharacters.put(curChar, newOccurrenceCount);
      }

    }

  }

  /*
    If we satisfied all characters the the required characters hashtable
    will be empty
  */
  return requiredCharacters.isEmpty();
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

