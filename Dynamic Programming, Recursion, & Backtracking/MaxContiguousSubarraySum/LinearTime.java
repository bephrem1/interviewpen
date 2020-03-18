/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int maxSubArray(int[] nums) {
    /*
      We default to say the the best maximum seen so far is the first
      element.

      We also default to say the the best max ending at the first element
      is...the first element.
    */
    int maxSoFar = nums[0];
    int maxEndingHere = nums[0];

    /*
      We will investigate the rest of the items in the array from index
      1 onward.
    */
    for (int i = 1; i < nums.length; i++) {
      /*
        We are inspecting the item at index i.

        We want to answer the question:
        "What is the Max Contiguous Subarray Sum we can achieve ending at index i?"

        We have 2 choices:

        maxEndingHere + nums[i] (extend the previous subarray best whatever it was)
          1.) Let the item we are sitting at contribute to this best max we achieved
          ending at index i - 1.

        nums[i] (start and end at this index)
          2.) Just take the item we are sitting at's value.

        The max of these 2 choices will be the best answer to the Max Contiguous
        Subarray Sum we can achieve for subarrays ending at index i.

        Example:

        index     0  1   2  3   4  5  6   7  8
        Input: [ -2, 1, -3, 4, -1, 2, 1, -5, 4 ]
                 -2, 1, -2, 4,  3, 5, 6,  1, 5    'maxEndingHere' at each point
        
        The best subarrays we would take if we took them:
          ending at index 0: [ -2 ]           (snippet from index 0 to index 0)
          ending at index 1: [ 1 ]            (snippet from index 1 to index 1) [we just took the item at index 1]
          ending at index 2: [ 1, -3 ]        (snippet from index 1 to index 2)
          ending at index 3: [ 4 ]            (snippet from index 3 to index 3) [we just took the item at index 3]
          ending at index 4: [ 4, -1 ]        (snippet from index 3 to index 4)
          ending at index 5: [ 4, -1, 2 ]     (snippet from index 3 to index 5)
          ending at index 6: [ 4, -1, 2, 1 ]  (snippet from index 3 to index 6)
          ending at index 7: [ 4, -1, 2, 1, -5 ]    (snippet from index 3 to index 7)
          ending at index 8: [ 4, -1, 2, 1, -5, 4 ] (snippet from index 3 to index 8)

        Notice how we are changing the end bound by 1 everytime.
      */
      maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);

      // Did we beat the 'maxSoFar' with the 'maxEndingHere'?
      maxSoFar = Math.max(maxSoFar, maxEndingHere);  
    }

    return maxSoFar;
  }
}
