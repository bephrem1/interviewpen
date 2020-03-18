/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Integer> getBuildingsWithAView(int[] buildings) {
    /*
      Stacks are useful to keep "histories". The LIFO ("last in, first out")
      behaviour allow us to access the most recent buildings in the order
      that we saw them.

      We will eliminate all buildings in our "history" that the just added
      building blocks out.

      If we do this every time we see a building we will guarantee that the
      final stack will only have buildings that were never obstructed.
    */
    Stack<Integer> buildingsWithView = new Stack<>();

    for (int i = buildings.length - 1; i >= 0; i--) {
      int buildingHeight = buildings[i];

      // Remove buildings in out history that are "blocked" from the sunset
      while (!buildingsWithView.isEmpty() && buildingHeight >= buildings[buildingsWithView.peek()]) {
        buildingsWithView.pop();
      }

      /*
        We have eliminated all buildings that this building blocks. Now we can
        add it to our history of buildings seen.

        It can be eliminated by later buildings, but for now we just note it since
        it does have a view at the current moment in iteration.
      */
      buildingsWithView.push(i);
    }

    Collections.reverse(buildingsWithView); // just so output is in increasing order

    return new ArrayList<>(buildingsWithView);
  }
}
