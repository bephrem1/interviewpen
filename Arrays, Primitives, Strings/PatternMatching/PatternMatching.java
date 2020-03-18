/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> matches = new ArrayList<>();

    for (String word: words) {
      if (isAligned(word, pattern)) {
        matches.add(word);
      }
    }

    return matches;
  }

  private boolean isAligned(String word, String pattern) {
    if (word.length() != pattern.length()) {
      return false;
    }

    // See https://www.ascii-code.com/ for ascii values
    int[] wordToPattern = new int[256];
    int[] patternToWord = new int[256];

    // Every step we build a cross mapping and see if it breaks
    for (int i = 0; i < word.length(); i++) {
      int wordChar = Character.getNumericValue(word.charAt(i));
      int patternChar = Character.getNumericValue(pattern.charAt(i));

      if (wordToPattern[wordChar] == 0) {
        wordToPattern[wordChar] = patternChar;
      }

      if (patternToWord[patternChar] == 0) {
        patternToWord[patternChar] = wordChar;
      }

      if (
        wordToPattern[wordChar] != patternChar ||
        patternToWord[patternChar] != wordChar
      ) {
        return false;
      }
    }

    return true;
  }
}
