/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
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

    // The result will be the only item remaining on the stack
    return stack.pop();
  }
}
