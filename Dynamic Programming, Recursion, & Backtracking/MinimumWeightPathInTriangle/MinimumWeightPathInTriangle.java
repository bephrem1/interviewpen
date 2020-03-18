/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
    int totalLayers = triangle.size();

    /*
      We have 2 choices at each cell as we traverse the triangle,
      go left or go right.

      All that matters to know the path to take in row 'i'
      is the results of the minimum possible path sums achieved
      from each cell in row 'i + 1'

      Original Triangle;
        [2]
        [3,4]
      [6,5,7] <- we start processing here
      [4,1,8,3]

      Processing the 2nd to last layer:
      6: Going right to 1 is best.
      5: Going left to 1 is best.
      7: Going right to 3 is best.

      So:
      Best minimum sum from 6 is 6 + 1, 7.
      Best minimum sum from 5 is 5 + 1, 6.
      Best minimum sum from 7 is 7 + 3, 10.

      So cache after 1st iteration:
      [ 7, 6, 10 ]

      Then when we process the row with [3, 4] we now understand
      how to make the best decision.

      Note: Layer 1 (the top layer of the triangle) will have 1 item,
      Layer 2 will have 2 items, etc. The cache needs to be as wide
      as the wides layer, which is the final layer

      Also Note: We will overwrite values in the cache and perform
      destructive operations. It won't hurt us, we just read from index
      0 at the end anyway. No operations get in the way of each other.
    */
    int[] dpLayerCache = new int[totalLayers];
    initializeCacheWithLastLayer(dpLayerCache, triangle.get(triangle.size() - 1));

    // Start from the 2nd to last layer
    for (int i = totalLayers - 2; i >= 0; i--) {
      List<Integer> currentLayer = triangle.get(i);

      // Iterate over each item in row 'i'
      for(int j = 0; j < currentLayer.size(); j++) {
        int minSumGoingLeft = dpLayerCache[j];
        int minSumGoingRight = dpLayerCache[j + 1];

        int minSumDirection = Math.min(minSumGoingLeft, minSumGoingRight);
        int thisCellsValue = currentLayer.get(j);

        dpLayerCache[j] = minSumDirection + thisCellsValue;
      }
    }

    return dpLayerCache[0];
  }

  private void initializeCacheWithLastLayer(int[] cache, List<Integer> lastRow) {
    for (int i = 0; i < cache.length; i++) {
      cache[i] = lastRow.get(i);
    }
  }
}
