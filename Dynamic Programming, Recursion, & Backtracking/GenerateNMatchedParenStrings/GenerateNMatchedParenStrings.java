/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<String> generateParentheses(int numPairs) {
    List<String> result = new ArrayList<>();

    directedGenerateBalancedParentheses(numPairs, numPairs, "", result); // kick off the recursion

    return result;
  }

  private void directedGenerateBalancedParentheses(
    int numLeftParensNeeded,
    int numRightParensNeeded,
    String parenStringInProgress,
    List<String> result
  ) {
    /*
      The recursion has bottomed out.

      We have used all left and right parens necessary within constraints up
      to this point. Therefore, the answer we add will be a valid paren string.
      
      We can add this answer and then backtrack so the previous call can exhaust
      more possibilities and express more answers...and then return to its caller,
      etc. etc.

      Yeah...this is what backtracking is all about.
    */
    if (numLeftParensNeeded == 0 && numRightParensNeeded == 0) {
      result.add(parenStringInProgress);
      return;
    }

    /*
      At each frame of the recursion we have 2 things we can do:

      1.) Insert a left parenthesis
      2.) Insert a right parenthesis

      These represent all of the possibilities of paths we can take from this
      respective call. The path that we can take all depends on the state coming
      into this call.
    */

    /*
      Can we insert a left parenthesis? Only if we have lefts remaining to insert
      at this point in the recursion
    */
    if (numLeftParensNeeded > 0) {
      /*
        numLeftParensNeeded - 1 ->       We are using a left paren
        numRightParensNeeded ->          We did not use a right paren
        parenStringInProgress + "(" ->   We append a left paren to the string in progress
        result ->                        Just pass the result list along for the next call to use
      */
      directedGenerateBalancedParentheses(
        numLeftParensNeeded - 1,
        numRightParensNeeded,
        parenStringInProgress + "(",
        result
      );
    }

    /*
      Can we insert a right parenthesis? Only if the number of left parens needed
      is less than then number of right parens needed.
      
      This means that there are open left parenthesis to close OTHERWISE WE CANNOT
      USE A RIGHT TO CLOSE ANYTHING. We would lose balance.
    */
    if (numLeftParensNeeded < numRightParensNeeded) {
      /*
        numLeftParensNeeded ->           We did not use a left paren
        numRightParensNeeded - 1 ->      We used a right paren
        parenStringInProgress + ")" ->   We append a right paren to the string in progress
        result ->                        Just pass the result list along for the next call to use
      */
      directedGenerateBalancedParentheses(
        numLeftParensNeeded,
        numRightParensNeeded - 1,
        parenStringInProgress + ")",
        result
      );
    }
  }
}
