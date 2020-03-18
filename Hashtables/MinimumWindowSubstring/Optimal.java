/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public String minWindow(String searchString, String t) {
    Map<Character, Integer> requiredCharacters = buildMappingOfCharactersToOccurrences(t);
    Map<Character, Integer> windowCharacterMapping = new HashMap<Character, Integer>();

    int left = 0;
    int right = 0;

    int totalCharFrequenciesToMatch = requiredCharacters.size();
    int charFrequenciesInWindowThatMatch = 0;

    int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
    String minWindow = "";

    while (right < searchString.length()) {
      char characterAtRightPointer = searchString.charAt(right);
      addCharacterToHashtableMapping(windowCharacterMapping, characterAtRightPointer);

      boolean rightCharIsARequirement = requiredCharacters.containsKey(characterAtRightPointer);
      if (rightCharIsARequirement) {
        boolean requirementForCharacterMet =
          requiredCharacters.get(characterAtRightPointer).intValue() ==
          windowCharacterMapping.get(characterAtRightPointer).intValue();

        if (requirementForCharacterMet) {
          charFrequenciesInWindowThatMatch++;
        }
      }

      while (charFrequenciesInWindowThatMatch == totalCharFrequenciesToMatch && left <= right) {
        char characterAtLeftPointer = searchString.charAt(left);
        int windowSize = right - left + 1;

        if (windowSize < minWindowLengthSeenSoFar) {
          minWindowLengthSeenSoFar = windowSize;
          minWindow = searchString.substring(left, right + 1);
        }

        windowCharacterMapping.put(
          characterAtLeftPointer,
          windowCharacterMapping.get(characterAtLeftPointer) - 1
        );

        boolean leftCharIsARequirement = requiredCharacters.containsKey(characterAtLeftPointer);
        if (leftCharIsARequirement) {
          boolean characterFailsRequirement =
            windowCharacterMapping.get(characterAtLeftPointer).intValue() <
            requiredCharacters.get(characterAtLeftPointer).intValue();

          if (characterFailsRequirement) {
            charFrequenciesInWindowThatMatch--;
          }
        }

        left++;
      }

      right++;
    }
    
    return minWindow;
  }

  private Map<Character, Integer> buildMappingOfCharactersToOccurrences(String s) {
    Map<Character, Integer> map = new HashMap<Character, Integer>();

    for (int i = 0; i < s.length(); i++) {
      int occurrencesOfCharacter = map.getOrDefault(s.charAt(i), 0);
      map.put(s.charAt(i), occurrencesOfCharacter + 1);
    }

    return map;
  }

  private void addCharacterToHashtableMapping(
    Map<Character, Integer> map,
    Character c
  ) {
    int occurrences = map.getOrDefault(c, 0);
    map.put(c, occurrences + 1);
  }
}
