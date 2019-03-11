/**
  Decode Ways - LeetCode: https://leetcode.com/problems/decode-ways

  @author Benyam Ephrem
  
  This code is by NO MEANS cleanly or well done. I just threw this together
  for teaching purposes and it is generally a sloppy example but gets the point
  across

  This code passes all Leetcode test cases as of Mar. 10 2019
  Runtime: 1 ms, faster than 87.88% of Java online submissions for Decode Ways.
	Memory Usage: 34.4 MB, less than 77.38% of Java online submissions for Decode Ways.

  The video to explain this code is here: https://www.youtube.com/watch?v=YcJTyrG3bZs
*/

public int numDecodings(String s) {

  /*
    We will cache the answers to our subproblems
  */
  int[] previousAnswers = new int[s.length()];
  Arrays.fill(previousAnswers, -1);

  return numDecodings(s, 0, previousAnswers);
}

public int numDecodings(String s, int decodePointer, int[] previousAnswers) {

  /*
    If our decoding pointer out of bounds then we know that we have
    exhausted our ability to decode the string
  */
  if (decodePointer >= s.length()) {
    return 1;
  }

  /*
    If we already know the answer to this subproblem then just return it,
    don't waste time calculating it
  */
  if (previousAnswers[decodePointer] > -1) {
    return previousAnswers[decodePointer];
  }

  /*
    We don't already know the answer to this subproblem, calculate it
    by taking the sum of the total ways for a single character decoring
    or 2 character decoding
  */
  int totalWaysFromHere = 0;
  
  /*
    These checks are sloppy but they ensure we don't substring out of bounds
  */
  if (decodePointer + 1 <= s.length()) {
      String firstWay = s.substring(decodePointer, decodePointer + 1); // single character decoding
      if (isValid(firstWay)) {
        totalWaysFromHere += numDecodings(s, decodePointer + 1, previousAnswers);
      }
  }

  if (decodePointer + 2 <= s.length()) {
    String secondWay = s.substring(decodePointer, decodePointer + 2); // 2 character decoding
    if (isValid(secondWay)) {
      /*
        If this is a valid decoding then recurse on it since it is ONE valid way to decode
        a piece of the string off. If it is INVALID we will not factor this way of decoing
        in and the path in the "tree" of recursion is cut short
      */
      totalWaysFromHere += numDecodings(s, decodePointer + 2, previousAnswers);
    }
  }

  /*
    CACHE THE SUBPROBLEM ANSWER. We will need this later when asked more subproblems
  */
  previousAnswers[decodePointer] = totalWaysFromHere;

  return previousAnswers[decodePointer]; // The answer to this subproblem
}

/*
  Simple helper function to checks if a substring is a valid
  decoding. This function is probably not totally optimal but eh.
*/
public boolean isValid(String s){

  if (s.length() == 0) {
    return false;
  }

  if (s.charAt(0) == '0') {
    return false;
  }

  int value = Integer.parseInt(s);

  return value >= 1 && value <= 26;
}
