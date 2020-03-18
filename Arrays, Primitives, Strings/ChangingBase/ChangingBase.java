/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public String changeBase(String numAsString, int b1, int b2) {
    boolean isNegative = numAsString.startsWith("-");

    /*
      start:
        If the number has a minus sign "-", then we start decomposing
        'numAsString' from index 1 instead of from index 0.
      
      maxPower:
        The power applied to the base that the most significant digit
        will be multiplied by. Ex: "324" -> maxPower will be 2 since
        "324" = (3 x 10^2) + (2 x 10^1) + (4 x 10^0)
                        ^
                     maxPower
      
      numberUnderBase10: The number we are slowly going to build
    */
    int start = isNegative ? 1 : 0;
    int maxPower = numAsString.length() - 1;
    int numberUnderBase10 = 0;

    /*
      Loop over every digit & add it to the base 10 integer representation
      we are building. We will later take this base 10 integer and convert
      it into b2.
    */
    for (int i = start; i < numAsString.length(); i++) {

      /*
        Get the integer value that this place "contributes", or in
        other words, the value that will be multiplied by (base)^(digit's position).
      */
      boolean isPlaceADigit = Character.isDigit(numAsString.charAt(i));
      int valueContributedByPlace =
        isPlaceADigit ? numAsString.charAt(i) - '0' : numAsString.charAt(i) - 'A' + 10;

      /*
        So if we have "895", it means:

        (8 x 10^2) + (9 x 10^1) + (5 x 10^0)
        (800) + (90) + (5)
        895

        If we have "1AB" (under base 16, hex), it means:

                    A => 10      B => 11
        (1 x 16^2) + (10 x 16^1) + (11 x 16^0)
        (256) + (160) + (11)
        427
      */
      int positionPowerWeight = maxPower - i;
      numberUnderBase10 += (int) valueContributedByPlace * Math.pow(b1, positionPowerWeight);
    }

    if (numberUnderBase10 == 0) {
      return "0";
    } else {
      return (isNegative ? "-" : "") + base10ToNewBase(numberUnderBase10, b2);
    }
  }

  private String base10ToNewBase(int numberUnderBase10, int base) {
    if (numberUnderBase10 == 0) {
      return "";
    }

    // lsd => "least significant digit"
    char lsdAsChar;
    int lsdUnderFinalBase = numberUnderBase10 % base;

    boolean needsHexConversion = lsdUnderFinalBase >= 10;
    if (needsHexConversion) {
      /*
        Convert digit into a letter (theoretically 'A'-'Z') because
        we can't express values 10 and above as single digit values.

        If we go past base 36 this will break.
      */
      lsdAsChar = (char) ('A' + lsdUnderFinalBase - 10);
    } else {
      // 'lsdUnderFinalBase' can be expressed as a single digit
      lsdAsChar = (char) ('0' + lsdUnderFinalBase);
    }

    int base10NumberWithoutLsd = numberUnderBase10 / base;
    String everythingElseToOurLeft = base10ToNewBase(base10NumberWithoutLsd, base);

    return everythingElseToOurLeft + lsdAsChar;
  }
}

/*
  This code can handle bases between 2 - 36, this is because for
  each digit we can represent:

  0, 1, 2, 3, 4, 5, 6, 7, 8, 9 => 10 uniques
  A, B, C, D, E, F, G, H, I, J, K, L, N, O, P, Q, R, S, T, U, V, W, X, Y, Z => 26 uniques

  So 36 unique digits in each place can be handled at maximum. 0 - 'Z'

  This code isn't thoroughly tested & I'm not an expert on this, but
  this should get the general point across.
*/