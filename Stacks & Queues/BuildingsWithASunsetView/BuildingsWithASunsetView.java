/*
  We are given a list of buildings where index i in the list
  indicates the height of building i.

  The sun is to the left.
  
  index 0 is furthest left, index len(arr) - 1 is furthest right.

  If building 'b' is anywhere to the right of building 'a', and
  building 'a' is >= in height to building 'b', then building 'b'
  cannot see thensunset.

  Calculate the buildings that have a sunset view from right to left.
*/

import java.util.*;

public class BuildingsWithASunsetView {
  public static void main(String args[]) {
    int[] buildingSetA = new int[]{ 1, 2, 3, 4, 5 };
    int[] buildingSetB = new int[]{ 5, 3, 4, 2, 1 };
    int[] buildingSetC = new int[]{ 7, 4, 8, 2, 9 };
    
    System.out.println(getBuildingsWithAView(buildingSetA));
    System.out.println(getBuildingsWithAView(buildingSetB));
    System.out.println(getBuildingsWithAView(buildingSetC));
  }

  private static List<Integer> getBuildingsWithAView(int[] buildings) {
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
