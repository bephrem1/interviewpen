/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  private String intToString(int num) {
    boolean isNegative = false;
    if (num < 0) {
      isNegative = true;
    }

    /*
      "Eat" away the string character by character:

      1.) Convert the ones place to a character
      2.) Append it to the target string
      3.) "Eat" away the ones place from the integer
    */
    StringBuilder sb = new StringBuilder();
    do {
      int onesPlace = num % 10;
      char onesPlaceAsChar = (char) ('0' + Math.abs(onesPlace));

      sb.append(onesPlaceAsChar);

      // remove ones place
      num /= 10;
    } while (num != 0);

    if (isNegative) {
      sb.append('-'); // Adds the negative sign to the end
    }

    /*
      By this point if the input was -123 then we will
      have "321-". Reversed we will get what we want, "-123"
    */
    sb.reverse();

    return sb.toString();
  }

  public int stringToInt(String s) {
    int result = 0;

    int startIndex = s.charAt(0) == '-' ? 1 : 0;
    for (int i = startIndex; i < s.length(); i++) {
      /*
        '0' has value 48 - see http://www.asciitable.com/, so if
        s.charAt(i) is '1', then:

        '1' -> 49
        '0' -> 48

        '1' - '0' = 49 - 48 = 1 (which is what '1' is in int form)
      */
      int digit = s.charAt(i) - '0';

      result *= 10; // Make room in the ones place
      result += digit; // Add it to the result's ones place
    }

    return s.charAt(0) == '-' ? -result : result;
  }
}
