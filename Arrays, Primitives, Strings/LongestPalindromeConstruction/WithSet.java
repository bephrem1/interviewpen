class Solution {
 public int longestPalindrome(String s) {
    int matchings = 0;
    Set<Character> unmatchedCharacters = new HashSet<Character>();

    for (char c: s.toCharArray()) {
      if (unmatchedCharacters.contains(c)) {
        unmatchedCharacters.remove(c);
        matchings++;
      } else {
        unmatchedCharacters.add(c);
      }
    }

    int longestPalindromeLength = matchings * 2;
    if (!unmatchedCharacters.isEmpty()) {
      longestPalindromeLength++; // place 1 unmatched odd character in the middle
    }

    return longestPalindromeLength;
  }
}
