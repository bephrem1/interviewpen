/*
  Similar question on Leetcode: Third Maximum Number
  Link: https://leetcode.com/problems/third-maximum-number/

  Thanks to GeeksForGeeks for this code sample. I just cleaned it up
  and added explanatory comments: https://www.geeksforgeeks.org/find-second-largest-element-array/

  This code has mediocre style. Just take away the concepts and ignore the sloppiness from me.

  The video to explain* this code is here: https://www.youtube.com/watch?v=NheWPxGpoxQ
*/

public static int secondLargest(int arr[]) { 

  int firstLargest = Integer.MIN_VALUE;
  int secondLargest = Integer.MIN_VALUE; 

  if (arr.length < 2) { 
    throw new NoSecondLargestException("Array too small to have 2nd largest item");
  } 

  /*
    We will check every element and compete it for a position amongst
    firstLargest and secondLargest
  */
  for (int i = 0; i < arr.length; i++) { 
      
      /*
        If this item beats the largest seen so far, make the
        firstLargest the new secondLargest.

        arr[i] the becomes the new firstLargest.
      */
      if (arr[i] > firstLargest) { 
        secondLargest = firstLargest; 
        firstLargest = arr[i]; 
      } else if (arr[i] > secondLargest && arr[i] != firstLargest) {

        /*
          This element becomes the secondLargest if it does not beat
          the firstLargest BUT it beats the secondLargest AND is NOT
          the same as the firstLargest item (so we don't unnecessarily
          eject the secondLargest value if this item IS the same as the
          firstLargest value)
        */
        secondLargest = arr[i]; 
      }
  } 

  if (secondLargest == Integer.MIN_VALUE) {
    throw new NoSecondLargestException("There is no second largest item");
  } else {
    return secondLargest;
  }

}
