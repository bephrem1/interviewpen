/*
  Authorship: ALL credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.
  
  I have just adapted the code to pass on Leetcode, added explanatory comments,
  reformatted the code, & changed variable names for understanding.

  Generate Parentheses - LeetCode: https://leetcode.com/problems/generate-parentheses

  This code passes all Leetcode test cases as of Jan. 13 2019
  Runtime: 2 ms, faster than 95.74% of Java online submissions for Generate Parentheses.

  The video to explain this code is here: https://www.youtube.com/watch?v=sz1qaKt0KGQ
*/

public static List<String> generateParenthesis(int numPairs) {
  List<String> result = new ArrayList<>();
  StringBuilder sb = new StringBuilder("");
  directedGenerateBalancedParentheses(numPairs, numPairs, sb, result); // kick off the recursion
  return result;
}

private static void directedGenerateBalancedParentheses(int numLeftParensNeeded , int numRightParensNeeded,
                                                        StringBuilder parenStringInProgress, List<String> result) {

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
    result.add(parenStringInProgress.toString());
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
      parenStringInProgress.append("(") ->   We append a left paren to the string in progress
      result ->                        Just pass the result list along for the next call to use
    */
    directedGenerateBalancedParentheses(numLeftParensNeeded - 1, numRightParensNeeded,
                                        parenStringInProgress.append("("), result);
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
      parenStringInProgress.append(")") ->   We append a right paren to the string in progress
      result ->                        Just pass the result list along for the next call to use
    */
    directedGenerateBalancedParentheses(numLeftParensNeeded, numRightParensNeeded - 1,
                                          parenStringInProgress.append(")"), result);
  }

}
