/*
	Credit: A huge thanks to Tuschar Roy for teaching me this problem. I am only
	building on top of his explanation and code.

	The Key To This Problem:

	For every startColIndex and endColIndex of the POSSIBLE maximal rectangle,
	we want to know the largest sum we can yield VERTICALLY so that we can
	deduce the best startRowIndex and endRowIndex.

	This is why we keep the array of running sums for each row. We are interested
	in best vertical value to start and end at.
	
	We know that we can achieve the sum of the Max Contiguous Subarray Sum for the
	vertical array if we choose the top bound of startRowIndex and the lower bound
	of endRowIndex (and of course keep our startColIndex & endColIndex that got us
	there the same)

	The video to explain this code is here: [a link will live here someday]
*/

