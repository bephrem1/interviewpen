/*
  Implement Queue using Stacks - LeetCode: https://leetcode.com/problems/implement-queue-using-stacks/

  Authorship: ALL credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.

  This code passes all Leetcode test cases as of Feb. 4 2019
  Runtime: 54 ms, faster than 95.59% of Java online submissions for Implement Queue using Stacks.
  Memory Usage: 25.7 MB, less than 81.58% of Java online submissions for Implement Queue using Stacks.

  The video to explain this code is here: [a link will live here someday]
*/

class MyQueue {

  private Stack<Integer> pushStack;
  private Stack<Integer> popStack;

  int queueSize;

  /*
    Initialize the 2 stacks and the size of the queue
  */
  public MyQueue() {
    pushStack = new Stack<>();
    popStack = new Stack<>();
    queueSize = 0; // ints in java are 0 by default but just being clear
  }
  
  /*
    Push element to the pushStack and increase the size of the queue
    ...should be called something like "enqueue" but whatever
  */
  public void push(int item) {
    pushStack.push(item);
    queueSize++;
  }
  
  /*
    Get the element from the front of the queue...should be
    called "dequeue" but oh well (we are conforming to the naming
    from Leetcode)
  */
  public int pop() {

    /*
      If the stack we can pop items from is empty then we need
      to populate it.

      All we do is put all items from the pushStack into the
      popStack.
    */
    ensureThereAreItemsInPopStack();

    /*
      If we now have elements to pop then pop the item.
      
      If the popStack is empty by this point then the overarching
      queue is empty.

      Throw an exception seems to make the most sense. Returning
      -1 would be misleading since that is a valid return value.
    */
    if (!popStack.isEmpty()) {
      queueSize--;
      return popStack.pop();
    }

    // We assume all valid operations as the problem description says...
    // Here we would handle an empty queue...I wouldn't return -1 becuase
    // that'd be a misleading value since it is a valid int (and entries
    // could be negative)...but we have to since the function returns an int
    // and we can't compile without a default return statement
    return -1;
  }
  
  /*
    Peek the item at the front of the queue
  */
  public int peek() {

    /*
      Ensure that the pop stack has an item to peek
    */
    ensureThereAreItemsInPopStack();

    /*
      Peek the item if the queue is not empty
    */
    if (!popStack.isEmpty()) {
      return popStack.peek();
    }

    // Again...I'd throw an exception here. Returning -1 might be misleading.
    // But...we have to return -1 to compile
    return -1;
  }
  
  /*
    Is the queue empty?
  */
  public boolean empty() {
    return queueSize == 0;
  }

  private void ensureThereAreItemsInPopStack() {
    if (popStack.isEmpty()) {
      populatePopStack();
    }
  }

  private void populatePopStack() {
    while (!pushStack.isEmpty()) {
      popStack.push(pushStack.pop());
    }
  }

}
