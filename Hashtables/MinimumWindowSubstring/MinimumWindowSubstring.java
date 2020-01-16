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

public String minWindow(String searchString, String t) {

  // The characters a satisfiable window must cover mapped to their frequency
  Map<Character, Integer> requiredCharacters = buildMappingOfCharactersToOccurrences(t);

  /*
    For our window. Map all characters in the window to their occurrence count. You
    will see how we use this below.
  */
  Map<Character, Integer> windowCharacterMapping = new HashMap<Character, Integer>();

  /*
    2 pointers. Left holds the left index of the window we are inspecting and right
    holds the right index.

    The approach is simple. We keep moving right (don't touch left) until the window
    we hold satisfies all required characters. Then we take note whether the window
    we see beats the smallest satisfiable window we have found so far.

    We then contract the left pointer in while the window still satisfies all required
    characters (at every point continuing to check if we have beaten the smallest window
    ever seen to this point)

    As soon as the window no longer satisfies, go back to expanding the right pointer.

    We are finished when the right pointer runs over the array because we can't keep
    expanding the window to satisfy at that point.
  */
  int left = 0;
  int right = 0;

  /*
    'totalCharFrequenciesToMatch' is the total characters we need to match frequency for
    in the window. If I have 1 'a' in my window and I need 2 'a' chars...then the char
    frequencies don't match.

    'charFrequenciesInWindowThatMatch' is the count of frequencies that we have satisfied.

    When 'totalCharFrequenciesToMatch' == 'charFrequenciesInWindowThatMatch' then it can be
    said that the current window satisfies that property of having all characters with matching
    counts to the string t.
  */
  int totalCharFrequenciesToMatch = requiredCharacters.size();
  int charFrequenciesInWindowThatMatch = 0;

  /*
    We will keep track of the best window we have seen so far
  */
  int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
  String minWindow = "";

  while (right < searchString.length()) {

    /*
      Add the character on the right pointer to the hashtable that maps the characters seen
      in the window to their occurrence count
    */
    char characterAtRightPointer = searchString.charAt(right);
    addCharacterToHashtableMapping(windowCharacterMapping, characterAtRightPointer);

    /*
      Is this character part of the requirement?
    */
    boolean rightCharIsARequirement = requiredCharacters.containsKey(characterAtRightPointer);
    if (rightCharIsARequirement) {
      /*
        Does the current window frequency match the required frequency?
      */
      boolean requirementForCharacterMet = requiredCharacters.get(characterAtRightPointer).intValue() ==
                                            windowCharacterMapping.get(characterAtRightPointer).intValue();
      if (requirementForCharacterMet) {
        /*
          If so then we have one more frequency requirement that matches...remember when:
          'totalCharFrequenciesToMatch' == 'charFrequenciesInWindowThatMatch' then we know that
          we have a satisfying window
        */
        charFrequenciesInWindowThatMatch++;
      }
    }
    
    /*
      Does this window satisfy? Ok...if it does try contracting the left pointer inward until
      we go over the right pointer.
    */
    while (charFrequenciesInWindowThatMatch == totalCharFrequenciesToMatch && left <= right) {

      /*
        Put these things in plain English so you don't get confused
      */
      char characterAtLeftPointer = searchString.charAt(left);
      int windowSize = right - left + 1;

      /*
        Have we beat the best satisfiable window seen so far? Ok...if so then update
        the tracking variables
      */
      if (windowSize < minWindowLengthSeenSoFar) {
        minWindowLengthSeenSoFar = windowSize;
        minWindow = searchString.substring(left, right + 1);
      }

      /*
        This character will get contracted out. It won't be in the window anymore once
        left moves forward.
      */
      windowCharacterMapping.put(characterAtLeftPointer, windowCharacterMapping.get(characterAtLeftPointer) - 1);

      /*
        Was this character part of the requirement? If so then its frequency changing matters to us.
      */
      boolean leftCharIsARequirement = requiredCharacters.containsKey(characterAtLeftPointer);
      if (leftCharIsARequirement) {
        /*
          Does the character frequence count not fall below the threshold of satisfying?
        */
        boolean characterFailsRequirement = windowCharacterMapping.get(characterAtLeftPointer).intValue() <
                                            requiredCharacters.get(characterAtLeftPointer).intValue();

        if (characterFailsRequirement) {
          /*
            If so then we have one less character frequency mapping in the window that matches
          */
          charFrequenciesInWindowThatMatch--;
        }
      }

      /*
        Move the left point forward. We will keep going until the window no longer satisfies.
      */
      left++;
    }

    /*
      We have moved left as far as it could go. It either led to a window that no longer
      satisfied or left passed the right pointer. Either way...advance the right pointer.
    */
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

private void addCharacterToHashtableMapping(Map<Character, Integer> map, Character c) {
  int occurrences = map.getOrDefault(c, 0);
  map.put(c, occurrences + 1);
}
