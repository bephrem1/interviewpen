/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public String minWindow(String searchString, String t) {
    int n = searchString.length();

    int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
    String minWindow = "";

    for (int left = 0; left < n; left++) {
      for (int right = left; right < n; right++) {
        String windowSnippet = searchString.substring(left, right + 1);

        boolean windowContainsAllChars = stringContainsAllCharacters(windowSnippet, t);

        if (windowContainsAllChars && windowSnippet.length() < minWindowLengthSeenSoFar) {
          minWindowLengthSeenSoFar = windowSnippet.length();
          minWindow = windowSnippet;
        }
      }
    }

    return minWindow;
  }

  private boolean stringContainsAllCharacters(String searchString, String t) {
    Map<Character, Integer> requiredCharacters = new HashMap<Character, Integer>();

    for (int i = 0; i < t.length(); i++) {
      int occurrencesOfCharacter = requiredCharacters.getOrDefault(t.charAt(i), 0);

      requiredCharacters.put(t.charAt(i), occurrencesOfCharacter + 1);
    }

    for (int i = 0; i < searchString.length(); i++) {
      char curChar = searchString.charAt(i);

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
}
