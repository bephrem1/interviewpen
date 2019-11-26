/**
  The code below addresses these 2 problems:

  Search a 2D Matrix - LeetCode: https://leetcode.com/problems/search-a-2d-matrix/
  > Every row is sorted left to right in increasing order. The first integer of
  each row is greater than the last integer of the previous row.

  Search a 2D Matrix II - LeetCode: https://leetcode.com/problems/search-a-2d-matrix-ii/
  > Every row is sorted left to right in increasing order
  > Every column is sorted top to bottom in increasing order
  > There are no guarantees that the first item of any row or column relates to the
  last item in the previous row or column

  The video to explain this code is here: https://www.youtube.com/watch?v=FOa55B9Ikfg
*/

/***********************************************
              Search a 2D Matrix
************************************************/

/*
  Here is a very pure 2D matrix to exemplify our mapping system. each cell has its conceptual
  index if we flattened the 2D matrix into a 1D sorted array.

  [ 0, 1, 2 ]
  [ 3, 4, 5 ]
  [ 6, 7, 8 ]

  totalRows = 3
  totalColumns = 3

  Coordinate Mappings -> (row, col)

  [ (0, 0), (0, 1), (0, 2) ]
  [ (1, 0), (1, 1), (1, 2) ]
  [ (2, 0), (2, 1), (2, 2) ]

  Given a 1D index position .. we declare our mapping equations:

  row in 2D matrix: 1DIndex / totalRows
  col in 2D matrix: 1DIndex % totalColumns

  Example:

  We want to find the coordinate for element 6.
  row in 2D matrix: (6) / 3 = 2
  col in 2D matrix: (6) % 3 = 0
  
  Output: (2, 0)

  We are correct.
*/

/*
  This code passes all Leetcode test cases as of Mar. 8 2019
  Runtime: 4 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
  Memory Usage: 39.9 MB, less than 15.59% of Java online submissions for Search a 2D Matrix.
*/

public boolean searchMatrix(int[][] matrix, int target) {

  /*
    We can't search a matrix with no rows.
    If we have rows then we can continue.
  */
  if (matrix.length == 0) {
    return false;
  }
  
  int totalRows = matrix.length;
  int totalColumns = matrix[0].length;
  
  /*
    The left and right pointer to our search. We imagine a visualization
    of a 1D array and will use our mapping system to convert these indices
    into concrete locations in the 2D matrix.

    The the last position in the matrix if we consider it as array will be
    rows * cols - 1 indices.

    Same as an array...an array's last index is arr.length - 1.
  */
  int left = 0;
  int right = totalRows * totalColumns - 1;
  
  /*
    Continue searching while the left has not passed the right
  */
  while (left <= right) {

    /*
      Prevent overflow, find the middle this way instead of doing: (right + left) / 2
    */
    int middle = left + (right - left) / 2;

    /*
      We need to map from our conceptualized 1D array back to our 2D matrix to index
      into it.

      Look above for the mapping explanation
    */
    int middleElementValue = matrix[middle / totalRows][middle % totalColumns];
    
    /*
      3 possibilities:
        1.) We found the target
        2.) We went too high in value...go "left"
        3.) We went too low in value...go "right"
    */
    if (middleElementValue == target) {
      return true; // element found
    } else if (middleElementValue < target) {
      left = middle + 1; // go right
    } else {
      right = middle - 1; // go left
    }

  }
  
  /*
    If no element is found, we will reach here and return false
  */
  return false;
}

/***********************************************
              Search a 2D Matrix II
************************************************/

/*
  This code passes all Leetcode test cases as of Mar. 4 2019
  Runtime: 5 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
  Memory Usage: 45.8 MB, less than 73.92% of Java online submissions for Search a 2D Matrix II.

  Linear with respect to both the rows and columns. Starting at top right.
*/

public boolean searchMatrix(int[][] matrix, int target) {

  /*
    We can't search a matrix with no rows.
    If we have rows then we can continue.
  */
  if (matrix.length == 0) {
    return false;
  }

  /*
    Start at farthest right column in the first row
  */
  int col = matrix[0].length - 1;
  int row = 0;

  /*
    Keep going while inbounds
  */
  while (col >= 0 && row <= matrix.length - 1) {

    /*
      Cases:
        target == item : We found the item. Return true.
        target < item : Target must be to our left...go down in value
        target > item : Target must be to our below...go up in value
    */
    if (target == matrix[row][col]) {
      return true;
    } else if (target < matrix[row][col]) {
      col--;
    } else if (target > matrix[row][col]) {
      row++;
    }

  }

  return false;
}

/*
  This code passes all Leetcode test cases as of Mar. 4 2019
  Runtime: 5 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
  Memory Usage: 46.3 MB, less than 32.09% of Java online submissions for Search a 2D Matrix II.

  Linear with respect to both the rows and columns. Starting at bottom left.
*/
public boolean searchMatrix(int[][] matrix, int target) {

  /*
    We can't search a matrix with no rows.
    If we have rows then we can continue.
  */
  if (matrix.length == 0) {
    return false;
  }

  /*
    Start at farthest left column in the last row. Bottom left corner.
  */
  int col = 0;
  int row = matrix.length - 1;

  /*
    Keep going while inbounds
  */
  while (col <= matrix[0].length - 1 && row >= 0) {

    /*
      Cases:
        target == item : We found the item. Return true.
        target < item : Target must be above...go down in value
        target > item : Target must be to our right...go up in value
    */
    if (target == matrix[row][col]) {
      return true;
    } else if (target < matrix[row][col]) {
      row--;
    } else if (target > matrix[row][col]) {
      col++;
    }

  }

  return false;
}

/*
  There is an even faster approach addressed in "Cracking The Coding Interview" that operates
  even faster. It is lengthy. For further investigation it is problem 10.9 in chapter 10.
*/
