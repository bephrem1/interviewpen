/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  // This can be a hashtable, any structure to map 'number' to 'letters' it can manifest as
  private List<String> digitToPossibleLetters = Arrays.asList(
    "",     // 0
    "",     // 1
    "abc",  // 2
    "def",  // 3
    "ghi",  // 4
    "jkl",  // 5
    "mno",  // 6
    "pqrs", // 7
    "tuv",  // 8
    "wxyz"  // 9
  );

  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) {
      return new ArrayList<>();
    }

    List<String> mnemonics = new ArrayList<>();
    exploreCombinations(0, new StringBuilder(), digits, mnemonics);
    return mnemonics;
  }

  /*
    Whenever crafting recursive functions the #1 thing we consider
    is "what are the inputs?" / "what state do I need?".
    
    We need to know what digit we are working on -> 'currentDigit' (1st param)
    We know we need a working structure to append character to -> 'partialMnemonic' (2nd param)
    We need to know the original digits string to read from it -> 'digits' (3rd param)
    We need to capture answers when the base case is reached -> 'mnemonics' (4th param)
  */
  private void exploreCombinations(int currentDigit, StringBuilder partialMnemonic, String digits, List<String> mnemonics) {
    if (currentDigit == digits.length()) {
      mnemonics.add(partialMnemonic.toString());
      return;
    }

    // Get the digit we will express the possibilities for in this stack frame
    char digitCharacter = digits.charAt(currentDigit);
    int digitAsInt = digitCharacter - '0';

    // These are the possible letters that the digit maps to
    String letters = digitToPossibleLetters.get(digitAsInt);

    // We loop over the possibilities we just determined & recurse on each choice
    for (char possibleLetter: letters.toCharArray()) {
      
      // 1.) Choose - Append the letter that this digit can materialize into
      partialMnemonic.append(possibleLetter);

      // 2.) Explore - Recurse on the decision with changed state. We advance the digit we are working on.
      exploreCombinations(currentDigit + 1, partialMnemonic, digits, mnemonics);

      // 3.) Unchoose - We have returned to this stack frame of choice. Remove the choice, next loop will choose again.
      partialMnemonic.deleteCharAt(partialMnemonic.length() - 1);
    }
  }
}
