/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int getSum(int a, int b) {

    /*
     * Keep adding until we have no carry left
     */
    while (b != 0) {

      /*
       * Take note of what positions will need a carry, we will left shift this below
       * and make b hold it. Remember: a carry is not applied where it is
       * discovered...it is applied 1 position to the left of where it was born
       */
      int carry = a & b;

      /*
       * a's job is to keep the sum we are going to be working on, '^' does bit
       * addition, see explanation below to fully understand this
       */
      a = a ^ b;

      /*
       * b will house the carry from the operation, we left shift by 1 because in the
       * next iteration we will add against the carry
       */
      b = carry << 1;
    }

    /*
     * Return a, it was used to house the running sum we were working on the whole
     * time
     */
    return a;
  }
}

/*
 * We add binary numbers the same way we add decimal numbers. It is just that we
 * are now under a new base. Watch:
 * 
 * Decimal
 * 
 * a = 123 b = 39
 * 
 * 123 + 39 --- 2 (carry a 1 to the left)
 * 
 * 1 <--- the carry is applied 1 position to the LEFT 123 + 39 --- 62
 * 
 * good...carry applied successfully. Proceed. 123 + 39 --- 162 done.
 * 
 * 
 * Binary (nothing changes, all that changes is that we need to know
 * bitshifting) But now we need to know our bitshifting operators used for
 * addition.
 * 
 * We can get into half and full adders, what they are, and so on but that would
 * be a very very very long story just to explain this problem (although they
 * are easy to understand and relate exactly to this problem).
 * 
 * Imagine that 1 means 'true' and 0 means 'false'. Think of these like
 * booleans.
 * 
 * 
 * They are simple:
 * 
 * '&' the AND operator, both need to be 'true' for a 1 to come out
 * 
 * 0101 1101 ---- 0101
 * 
 * Notice how this ONLY happens at positions that will need a carry.
 * 
 * 
 * '^' the XOR (exclusive or) operator, either needs to be true, BOTH CANNOT BE
 * TRUE (hence exclusive or) for a 1 to come out
 * 
 * 1010 0110 ---- 1100
 * 
 * Notice something...this operator basically does addition...look:
 * 
 * 1 0 - 1
 * 
 * 0 1 - 1
 * 
 * 1 1 - 0 (we would carry from this position to the left, but at the position
 * it should be 0)
 * 
 * 0 0 - 0
 * 
 * Interesting...
 * 
 * 
 * '<<' the left shift operator, it shifts all bits to the left in a binary
 * representation (and all data is recorded as binary data at the fundamental
 * level)
 * 
 * 1010 ---- 0100 (see how the farthest left 1 got shifted out and the 2nd 1 got
 * shifted to the left 1 spot?)
 * 
 * 
 * We will need all 3 of these tools. Back to the example.
 * 
 * We need to be able to: 1.) Know what positions need a carry (the & operator
 * can do this for us...think about it) 2.) Be able to add 'a' and 'b' after we
 * know what positions yielded a carry (the '^' operator is perfect for
 * this...this one is harder to grasp...just think hard on it)
 * 
 * a = 1 b = 3
 * 
 * a = (0001) base 2 b = (0011) base 2
 * 
 * What we do is...we will have 'a' keep the running results of our additions,
 * and we will have 'b' hold the carrys that we will add against over...and
 * over...and over...until there is nothing left to carry.
 * 
 * '&' result [this is to see where we need to carry values over] 0001 0011 ----
 * 0001 (this bit sequence...which is just a number...represents positions that
 * yield a carry...it needs to go to the left...we will do that later)
 * 
 * '^' result [this is to get the 'sum' between the 2 bit sequences...this is
 * addition] 0001 0011 ---- 0011
 * 
 * << the carry [we do this since carries must be applied to 1 position to the
 * left] 0001 ---- 0010 (in the next iteration...b will hold this...and it will
 * be added against 'a' using '^'...which makes total sense. The carry we
 * recorded before should be moved 1 left so it can simply be added...then more
 * carry's will pop up...and so on until there are no carries left and we are
 * done with the addition.)
 */
