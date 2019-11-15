/*
  Task Scheduler - LeetCode: https://leetcode.com/problems/task-scheduler/
  This code passes all Leetcode test cases as of Nov. 14 2019

  Credit to Leetcode for the Solution: https://leetcode.com/problems/task-scheduler/solution
*/

public int leastInterval(char[] tasks, int n) {
  int[] tasks = new int[26]; // tasks only 'A' - 'Z'

  // Populate the task count mapping - we will break this later
  for (char c: tasks) {
    int characterIndex = c - 'A'; // http://www.asciitable.com/ - 'A' is 65 as an int
    tasks[characterIndex]++;
  }

  /*
    Constant size array so sorting is O((26)*lg(26)) = O(1), this is
    because the array does not grow. So it does not provide a varying
    parameter that will weight our asymptotic complexity down.
  */
  Arrays.sort(tasks);

  // Track total CPU intervals that have happened
  int clockCycles = 0;

  /*
    This confuses at first but remember that the array is now sorted.
    So if the last index is 0 (so 0 !> 0) then the whole array is 0 meaning
    we have no more tasks to do. Since we sorted the array the index has no
    meaning now.
  */
  while (tasks[25] > 0) {
    int batchOpsPerformed = 0;

    /*
      As soon as we let a task 'cool off' long enough, we want to return
      to doing it. This is what these conditions say:

      batchOpsPerformed <= n
        To maximize interleaving of tasks, we stop this loop when it has been
        n cycles since the first task happened. It means that we are open to now
        schedule the first task we scheduled
      
      tasks[25] != 0
        If there are no more operations to perform we will stop the loop.
    */
    while (batchOpsPerformed <= n && tasks[25] != 0) {
      int countOfTaskLeftToDo = map[25 - i]; // extracted to a variable for name to provide clarity

      /*
        batchOpsPerformed < 26:
          If the cool off is >= 26 then that means we will have to stay idle
          and cannot perform a task. This iteration is an idle iteration.
        
        countOfTaskLeftToDo > 0:
          There are operations left to do in this task. If batchOpsPerformed < 26 but
          we marched back from the end of the array to reach the region of 0's, then
          that means that this is an idle iteration where no tasks can be done. We
          must stay idle until batchOpsPerformed > n - the cool off time has been satisfied
      */
      if (batchOpsPerformed < 26 && countOfTaskLeftToDo > 0) {
        map[25 - i]--; // perform the task
      }

      batchOpsPerformed++; // this iteration is an operation or an idle cycle
      clockCycles++; // count a clock cycle
    }

    /*
      Now that the cooldown has been satisfied, we will sort the array again to bring
      unfinished tasks to the back
    */
    Arrays.sort(tasks);
  }

  return clockCycles;
}
