/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int[] plusOne(int[] digits) {
    /*
     * Grab the last index and then perform the increment
     */
    int lastIndex = digits.length - 1;
    digits[lastIndex] = digits[lastIndex] + 1;

    /*
     * While the position that we just incremented results in a 10, set the position
     * at i to 0 and then increment the position at i - 1
     * 
     * The next iteration will handle if THAT resulted in a 10, and so on and so
     * on......until we reach the front of the array. Then we HAVE to stop.
     */
    for (int i = lastIndex; i > 0 && digits[i] == 10; i--) {
      digits[i] = 0;
      digits[i - 1] = digits[i - 1] + 1;
    }

    /*
     * If the very front of the array got incremented and it resulted in a 10, we
     * have to resize and adjust for the carried value
     */
    if (digits[0] == 10) {

      /*
       * Since we cannot resize an array we must create a new array with one more
       * index
       */
      int[] resized = new int[digits.length + 1];

      /*
       * Set the first position in the digits array to 0 (it was 10 before), the 1
       * will carry into the place we will have to our left after we copy digits over
       * into the resized array
       */
      digits[0] = 0;

      /*
       * Make the copy over to 'resized' starting from the 1st index of 'resized' so
       * that we can place a 1 at index 0.
       * 
       * System.arraycopy ->
       * https://www.tutorialspoint.com/java/lang/system_arraycopy.ht
       */
      System.arraycopy(digits, 0, resized, 1, digits.length);

      /*
       * Place a 1 at index 0 in the new resized array
       */
      resized[0] = 1;

      /*
       * NOW. Point the digits reference to POINT to the new resized array. The OLD
       * array that 'digits' was POINTING to will be cleaned up by Java's garbage
       * collector.
       */
      digits = resized;
    }

    /*
     * Return the array that 'digits' points to (possibly the resized array we may
     * have had to make but may just be the original array if no resizing occurred)
     */
    return digits;
  }
}
