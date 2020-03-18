/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> results = new ArrayList<>();

    solveNQueens(0, n, new ArrayList<>(), results);

    return results;
  }

  private void solveNQueens(int row, int n, List<Integer> colPlacements, List<List<String>> results) {
    if (row == n) {
      /*
        All n queens have been placed in the n rows. We have
        reached the bottom of our recursion. We can now add
        the colPlacements to the results.
      */
      results.add(generateBoardFromPlacements(colPlacements, n));
      return;
    }

    /*
      Try all columns in the current row that we are making
      a choice on.

      The colPlacements list will hold the column we place a
      queen for the i'th row.

      So if I have [ 1, 3, 0, 2 ] that means:

      It is a 4 x 4 board.

      row index 0 has a queen placed in column index 1
      row index 1 has a queen placed in column index 3
      row index 2 has a queen placed in column index 0
      row index 3 has a queen placed in column index 2
    */
    for (int col = 0; col < n; col++) {

      /*
        Record a column placement for this row
      */
      colPlacements.add(col);

      /*
        If it is a valid placement we recurse to work on
        the next row (row + 1) in a recursive call
      */
      if (isValid(colPlacements)) {
        solveNQueens(row + 1, n, colPlacements, results);
      }

      /*
        We are done exploring with that placement and now we
        will remove it from our colPlacements. We will loop
        back around and try more column placements for this
        row (if there are any left)
      */
      colPlacements.remove(colPlacements.size() - 1);
    }
  }

  /*
    Check if a column placement that we just put in the colPlacements
    list is actually valid to recurse on
  */
  private boolean isValid(List<Integer> colPlacements) {

    /*
      rowWeAreValidatingOn is the row that we just placed a queen on
      and we need to validate the placement
    */
    int rowWeAreValidatingOn = colPlacements.size() - 1;

    /*
      Loop and check our placements in every row previous against
      the placement that we just made
    */
    for (int ithQueenRow = 0; ithQueenRow < rowWeAreValidatingOn; ithQueenRow++) {

      /*
        Get the absolute difference between:

        1.) The column of the already placed queen we are comparing against -> colPlacements.get(ithQueenRow)

        2.) The column of the queen we just placed -> colPlacements.get(rowWeAreValidatingOn)
      */
      int absoluteColumnDistance = Math.abs(colPlacements.get(ithQueenRow) - colPlacements.get(rowWeAreValidatingOn));

      /*
        1.) absoluteColumnDistance == 0
          If the absolute difference in columns is 0 then we placed in a column being
          attacked by the i'th queen.

        2.) absoluteColumnDistance == rowDistance
          If the absolute difference in columns equals the distance in rows from the
          i'th queen we placed, then the queen we just placed is attacked diagonally.

        For Constraint #2 imagine this:

        [
          ". . Q .",  <--- row 0 (Queen 1)
          "Q . . .",  <--- row 1 (Queen 2)
          ". Q . .",  <--- row 2 (Queen 3)
          ". . . ."
        ]

        1.) 
          Absolute Column Distance Between Queen 2 & 3 == 1.
          Queen 2 is in col 0, Queen 3 is in col 1. 1 - 0 = 1.

        2.)
          Absolute Row Distance Between Queen 2 & 3 == 1
          Queen 2 is in row 1, Queen 3 is in row 2. 2 - 1 = 1.
      */
      int rowDistance = rowWeAreValidatingOn - ithQueenRow;
      if (absoluteColumnDistance == 0 || absoluteColumnDistance == rowDistance) {
        return false;
      }
    }

    return true;
  }

  /*
  [
    ".Q..",
    "...Q",
    "Q...",
    "..Q."
    ]

    Generate a board from the list of column placements for each of the n rows.
  */
  private List<String> generateBoardFromPlacements(List<Integer> colPlacements, int n) {
    List<String> board = new ArrayList<>();
    int totalItemsPlaced = colPlacements.size();

    // Materialize a row for each queen that we placed
    for (int row = 0; row < totalItemsPlaced; row++) {

      StringBuilder sb = new StringBuilder();

      /*
        Go through all columns in the row and populate the string.

        If the column has a queen in it place a 'Q', otherwise place
        a '.'
      */
      for (int col = 0; col < n; col++) {
        if (col == colPlacements.get(row)) {
          sb.append('Q');
        } else {
          sb.append('.');
        }
      }

      // Add the row to the board
      board.add(sb.toString());
    }

    return board;
  }
}
