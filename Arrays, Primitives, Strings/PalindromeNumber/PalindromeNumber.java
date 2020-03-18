/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false; // A negative number can't be symmetric
    }

    /*
      10^b = x ... what is b? That is what log base 10 asks.
      
      'logarithmAnswer' is b.
    */
    double logarithmAnswer = Math.log10(x);

    /*
      'logarithmAnswer' can be a float value. Like:

      log10(99) ≈ 1.99564 (meaning that 10^1.99564 ≈ 99)
      
      So to get rid of the decimal part we floor it & then add 1
    */
    int totalDigits = (int)(Math.floor(logarithmAnswer)) + 1;

    /*
      If x = 999, we have 3 digits, so we want a mask the length
      of the integer.

      100 = 10^2, so 10^(digits - 1), we will use this later
      to extract & remove the most significant digit
    */
    int mostSignificantExtractionMask = (int) Math.pow(10, totalDigits - 1);

    // Iterate half the total amount of digits, check both ends
    for (int i = 0; i < (totalDigits / 2); i++) {
      int mostSignificantDigit = x / mostSignificantExtractionMask;
      int onesPlaceDigit = x % 10;

      if (mostSignificantDigit != onesPlaceDigit) {
        return false;
      }

      // Removing most significant digit: 999 % 100 = 99
      x %= mostSignificantExtractionMask;

      /*
        Removing the ones place digit: 99 / 10 = 9.9 => 9
        (.9 truncated since this is not a float type, it is an integer)
      */
      x /= 10;

      // Remove 2 0's from the mask since we just lost 2 digits
      mostSignificantExtractionMask /= 100;
    }

    return true;
  }
}
