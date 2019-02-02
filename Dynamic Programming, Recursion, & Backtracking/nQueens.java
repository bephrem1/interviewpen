/*
	Authorship: Credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.
  
  I have just adapted the solution to pass on Leetcode, added explanatory
  comments, reformatted the code, & changed variable names for understanding.

	N-Queens - LeetCode: https://leetcode.com/problems/n-queens/

  This code passes all Leetcode test cases as of Feb. 2 2019
	Runtime*: 7 ms, faster than 41.80% of Java online submissions for N-Queens.
	Memory Usage: 27.7 MB, less than 70.21% of Java online submissions for N-Queens.

	*Using a char array will be faster than using a Strings

	The video to explain this code is here: [a link will live here someday]
*/

public List<List<String>> solveNQueens(int n) {
	List<List<String>> result = new ArrayList<>();
	solveNQueens(n, 0, new ArrayList<Integer>(), result);
	return result;
}

private void solveNQueens(int n, int row, List<Integer> colPlacements,
																	List<List<String>> result){
	if (row == n) {

		/*
			All n queens have been placed in the n rows. We have
			reached the bottom of our recursion. We can now add
			the colPlacements to the result.
		*/
		result.add(generateBoardFromPlacements(colPlacements, n));

	} else {

		/*
			Try ALL columns in the current row that we are making
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
				solveNQueens(n, row + 1, colPlacements, result);
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
}

/*
	Check if a column placement that we just put in the colPlacements
	list is actually valid to recurse on
*/
private static boolean isValid(List<Integer> colPlacements) {

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

			1.) The column of the already placed queen we are comparing against:
				colPlacements.get(i)

			2.) The column of the queen we just placed 
				colPlacements.get(rowWeAreValidatingOn)
		*/
		int absoluteColumnDistance = Math.abs(colPlacements.get(ithQueenRow) -
																	colPlacements.get(rowWeAreValidatingOn));

		/*
			1.) If the absolute difference in columns is 0 then we placed in a column being
			attacked by the i'th queen.
				absoluteColumnDistance == 0

			2.) If the absolute difference in columns equals the distance in rows from the
			i'th queen we placed then the queen we just placed is attacked diagonally.
				absoluteColumnDistance == rowWeAreValidatingOn - i

			For Constraint #2 imagine this:

			[
				". . Q .",  <--- row 0 (Queen 1)
				"Q . . .",  <--- row 1 (Queen 2)
				". Q . .",  <--- row 2 (Queen 3)
				". . . ."
			]

			Absolute Column Distance Between Queen 2 & 3 == 1.
			Queen 2 is in col 0, Queen 3 is in col 1. 1 - 0 = 1.

			Absolute Row Distance Between Queen 2 & 3 == 1
			Queen 2 is in row 1, Queen 3 is in row 2. 2 - 1 = 1.
		*/
		if (absoluteColumnDistance == 0 || absoluteColumnDistance == rowWeAreValidatingOn - ithQueenRow) {
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

	/*
		Materialize a row for each queen that we placed
	*/
	for (int row = 0; row < totalItemsPlaced; row++) {

		StringBuilder sb = new StringBuilder();

		/*
			Go through all columns in the row and populate the string
		*/
		for (int col = 0; col < n; col++) {
			if (col == colPlacements.get(row)) {
				sb.append('Q');
			} else {
				sb.append('.');
			}
		}

		/*
			Add the row to the board
		*/
		board.add(sb.toString());

	}

	return board;
}
