/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public void printListInReverse(ListNode node) {
    if (node == null) {
      return;
    }

    /*
      Recursion first - drill down to the end of the list. Base
      case will catch us.
    */
    printListInReverse(node.next);

    /*
      Our work. This will be fired by the last node first, then
      the 2nd to last node, and so on...because we recursed first,
      got a ton of nodes on the stack in recursive calls, and now
      they will run this as the recursion goes "back up"
    */
    System.out.println(node.value);
  }
}
