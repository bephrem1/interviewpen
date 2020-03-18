/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int numTrees(int n) {
    /*
     * We will answer every subproblem from 0 to n hence the n + 1 to accomodate the
     * 0 subproblem
     */
    int[] G = new int[n + 1];

    /*
     * The answer to the subproblem when n = 0 is 0.
     */
    G[0] = 1;

    /*
     * The answer to the subproblem when n = 1 (we can only place a 1 as a value and
     * have a single node) is 1. A single node can only make 1 unique tree.
     */
    G[1] = 1;

    /*
     * We will solve every subproblem from 2 to our target subproblem n
     */
    for (int i = 2; i <= n; ++i) {
      /*
       * The answer to the ith subproblem will be the summation of F(i, n) for i = 0
       * to i = n (we plant every number we have available at the root)
       * 
       * Remember that we expressed: F(i, n) = G(i - 1) * G(n - i)
       * 
       * The answer to the total unique BST's we can construct with values from 1...n
       * with i representing what is rooted at the root of the tree is F(i, n).
       * 
       * We attain this value by taking the Cartesian Product (fancy word meaning all
       * possible cross products) between all possible unique left BST's and unique
       * right BST's.
       * 
       * All possible unique left BST's count is G[j - 1] if we plant at i.
       * 
       * All possible unique right BST's count is G[i - j] if we plant at i.
       * 
       * Taking a product is the same as taking all pairing between the two sets of
       * possibilites.
       * 
       * If still confused, watch the video above, take your time to let it sink in.
       */
      for (int j = 1; j <= i; ++j) {
        G[i] += G[j - 1] * G[i - j];
      }
    }
    return G[n];
  }
}
