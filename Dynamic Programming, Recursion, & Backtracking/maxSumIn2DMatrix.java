/*
  Credit: A huge thanks to Tuschar Roy for teaching me this problem. I am only
  building on top of his explanation and code.

  The Key To This Problem:

  For every leftBorderIndex and rightBorderIndex of the POSSIBLE maximal rectangle,
  we want to know the largest sum we can yield VERTICALLY so that we can
  deduce the best topBorderIndex and bottomBorderIndex.

  This is why we keep the array of running sums for each row. We are interested
  in best vertical value to start and end at (at each attempt of all of the
  combinations of leftBorderIndex and rightBorderIndex values).
  
  We know that we can achieve the sum of the Max Contiguous Subarray Sum for the
  vertical array if we choose the top bound of topBorderIndex and the lower bound
  of bottomBorderIndex.

  The video to explain this code is here: https://www.youtube.com/watch?v=-FgseNO-6Gk
*/

/*
  You can stick this driver function in a class and run the code below
*/
public static void main(String args[]){

  int matrix[][] = {
    {   6, -5,  -7,  4, -4 },
    {  -9,  3,  -6,  5,  2 },
    { -10,  4,   7, -6,  3 },
    {  -8,  9,  -3,  3, -7 }
  };
  
  Rectangle maxSumRectangle = maxSum(matrix);
  
  System.out.println("Rectangle Sum: " + maxSumRectangle.interiorSum);

  System.out.println("Left Index: " + maxSumRectangle.leftBorderIndex);
  System.out.println("Right Index: " + maxSumRectangle.rightBorderIndex);
  System.out.println("Top Index: " + maxSumRectangle.topBorderIndex);
  System.out.println("Bottom Index: " + maxSumRectangle.bottomBorderIndex);
}

private Rectangle maxSum(int matrix[][]) {

  /*
    Record the total amount of rows and columns
  */
  int rows = matrix.length;
  int cols = matrix[0].length;

  /*
    Create an array that will be a "vertical" array and record
    the running sums for each row in the each iteration of the
    left bound
  */
  int runningRowSums[] = new int[rows];

  /*
    This is the max rectangle that we will update along the way
  */
  Rectangle maxRectangle = new Rectangle();

  /*
    We will try all left bound plantings from index 0
    to index cols - 1
  */
  for (int left = 0; left < cols; left++) {

    /*
      We will reset the running row sums all to 0 since
      this is a new planting of the left bound
    */
    for (int i = 0; i < rows; i++) {
        runningRowSums[i] = 0;
    }

    /*
      For each left bound, we will try all of the right bounds
      starting at the left bound we are planted at.
    */
    for (int right = left; right < cols; right++) {

      /*
        Add all items in column 'right' to their respective
        row's running sum
      */
      for (int i = 0; i < rows; i++) {
        runningRowSums[i] += matrix[i][right];
      }

      /*
        Perform Kadane's algorithm on the running sum array
        so that we can determine the best top and bottom
        bound to choose for the rectangle.
      */
      KadaneResult kadaneResult = kadane(runningRowSums);

      /*
        If we notice that this rectangle can achieve a better
        maxSum than the best we have done so far then we need
        to adjust our new best
      */
      if (kadaneResult.maxSum > maxRectangle.interiorSum) {

        /*
          Set a new interiorSum for our maxRectangle
        */
        maxRectangle.interiorSum = kadaneResult.maxSum;

        /*
          The left and the right of the maxRectangle become
          the 'left' and 'right' where our for loop pointers
          are sitting
        */
        maxRectangle.leftBorderIndex = left;
        maxRectangle.rightBorderIndex = right;

        /*
          Our top and bottom bounds for the max rectangle are
          going to be the startIndex and endIndex of the max
          subarray region in the 'runningRowSums' sum cache
          (respectively).
        */
        maxRectangle.topBorderIndex = kadaneResult.startIndex;
        maxRectangle.bottomBorderIndex = kadaneResult.endIndex;
      }

    }

  }

  return maxRectangle;
}

/*
  An implementation of Kadane's algorithm that remembers the
  start and end of the Max Contiguous Subarray Sum region
  in the KadaneResult object returned

  This video explains Kadane's algorithm: https://www.youtube.com/watch?v=2MmGzdiKR9Y
*/
private KadaneResult kadane(int arr[]) {

  /*
    The best sum achieved for a region so far
  */
  int bestMaxSoFar = 0;

  /*
    maxRegionStart: start index of the max sum region
    maxRegionEnd: end index of the max sum region
  */
  int maxRegionStart = -1;
  int maxRegionEnd = -1;

  int currentStart = 0;
  int bestMaxAtThisIndex = 0;

  /*
    We will process every
  */
  for (int i = 0; i < arr.length; i++) {

    /*
      Add this item to the best subarray achieved at
      index i - 1. Then we will decided what to do
      after this.
    */
    bestMaxAtThisIndex += arr[i];

    /*
      If 'bestMaxAtThisIndex' is < 0 then we will
      decide to not extend the best subarray at i - 1.

      We will just set the best we can achieve for subarrays
      ending at this index i to 0.

      The new 'currentStart' to the max subarray region becomes
      i + 1
    */
    if (bestMaxAtThisIndex < 0) {
      bestMaxAtThisIndex = 0;
      currentStart = i + 1;
    }

    /*
      If the best max (now the best max at this index) beats the
      best global max achieved so far then we need to adjust:

      'maxRegionStart' becomes the 'currentStart' we were keeping track
      of all along.

      'maxRegionEnd' becomes the index we are sitting at 'i'.

      The 'bestMaxSoFar' becomes the 'bestMaxAtThisIndex'.
    */
    if (bestMaxAtThisIndex > bestMaxSoFar) {
      maxRegionStart = currentStart;
      maxRegionEnd = i;
      bestMaxSoFar = bestMaxAtThisIndex;
    }

  }

  return new KadaneResult(bestMaxSoFar, maxRegionStart, maxRegionEnd);
}

/*
  Holds the result of running Kadan's algorithm

  maxSum: the actual sum of the Max Contiguous Subarray Sum region
  startIndex: start of Max Contiguous Subarray Sum region
  endIndex: end of Max Contiguous Subarray Sum region
*/
private class KadaneResult {

  int maxSum;
  int startIndex;
  int endIndex;

  public KadaneResult(int maxSum, int startIndex, int endIndex) {
    this.maxSum = maxSum;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

}

/*
  A rectangle with left, right, top, and bottom bounds. The sum
  of all items contained within the rectangle are also recorded
  in the 'interiorSum' variable.
*/
private class Rectangle {

  int interiorSum;

  int leftBorderIndex;
  int rightBorderIndex;
  int topBorderIndex;
  int bottomBorderIndex;

}
