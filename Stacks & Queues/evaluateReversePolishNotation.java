/*
  Evaluate Reverse Polish Notation - LeetCode: https://leetcode.com/problems/evaluate-reverse-polish-notation/

  This code passes all Leetcode test cases as of Feb. 23 2019
  Runtime: 5 ms, faster than 97.02% of Java online submissions for Evaluate Reverse Polish Notation.

  The video to explain this code is here: https://www.youtube.com/watch?v=qN8LPIcY6K4
*/

/*
  We assume we get a valid Reverse Polish Notation expression encoded
  in the string array
*/
public int evalRPN(String[] tokens) {

  /*
    We will use these to hold the operands when we need to hold
    them somewhere temporary for operations where we want to make
    order obvious
  */
  int firstOperand;
  int secondOperand;

  Stack<Integer> stack = new Stack<Integer>();

  for (String token : tokens) {
    switch (token) {
      case "+":
        stack.push(stack.pop() + stack.pop());
        break;
      case "/":
        secondOperand = stack.pop();
        firstOperand = stack.pop();
        stack.push(firstOperand / secondOperand);
        break;
      case "*":
        stack.push(stack.pop() * stack.pop());
        break;
      case "-":
        secondOperand = stack.pop();
        firstOperand = stack.pop();
        stack.push(firstOperand - secondOperand);
        break;
      default:
        stack.push(Integer.parseInt(token)); // Must be a number if it isn't a "+", "-", "/", or "*"
    }
  }

  /*
    The result will be the 1 and only item on the stack
  */
  return stack.pop();
}
