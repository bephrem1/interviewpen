/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<Coordinate> findPath(
    List<List<Color>> maze,
    Coordinate start,
    Coordinate end
  ) {
    List<Coordinate> path = new ArrayList<>();

    /*
      Flip the item start position to black. We will start the search
      from here.
      
      Add the coordinate to the start of the path. It is our literal start
    */
    maze.get(start.row).set(start.col, Color.BLACK);
    path.add(start);

    /*
      If we do not find a path then remove the start node we added to the
      path and return an empty list down below
    */
    if (!hasPathToEnd(maze, start, end, path)) {
      path.remove(path.size() - 1);
    }

    /*
      If we DO find a path, the 'path' variable will have all nodes in the
      path from start to end, otherwise it will be an empty list
    */
    return path;
  }

  // A standard DFS for the path that we want to find.
  private boolean hasPathToEnd(
    List<List<Color>> maze,
    Coordinate node,
    Coordinate end,
    List<Coordinate> path
  ) {
    if (node.equals(end)) {
      return true;
    }

    /*
      SHIFTS codifies different shifts.

      Indexes:

          0          1
      {rowDelta, colDelta}

    */
    final int[][] SHIFTS = {
      {0 , 1}, // going right
      {1, 0}, // going down
      {0, -1}, // going left
      {-1, 0} // going up`
    };

    /*
      We:

      1.) Apply each shift. next is the coordinate that is next to process according
      to the shift we are on
    */
    for (int[] shift: SHIFTS) {
      // The next item to possibly add to our path and search
      Coordinate next = new Coordinate(node.row + shift[0], node.col + shift[1]);

      // Can this item be walked on?
      if (canTraverse(next , maze)) {

        // Yes. Set it to black and add it to the path
        maze.get(next.row).set(next.col, Color.BLACK);
        path.add(next);

        /*
          Can we finish a path from here now, recurse and find out. If we can
          we will bubble up the result and this Coordinate will be in the path.
        */
        if (hasPathToEnd(maze, next, end, path)) {
          return true;
        }

        /*
          Sadly...things didn't work out, walking from this cell we cannot complete
          a path. We remove the Coordinate from the path and continue the loop trying
          to root ourselves at a new "next" position in the path
        */
        path.remove(path.size() - 1);
      }
    }

    /*
      No 'next' Coordinates worked from this Coordinate. Return false to our
      caller. Path not possible, caller will have to keep searching.
    */
    return false;
  }

  /*
    Validates a given cell, it ensures it is in the maze and is white (can
    be "walked on")
  */
  private static boolean canTraverse(Coordinate node, List<List<Color>> maze) {
    return node.row >= 0 && node.row < maze.size() &&
          node.col >= 0 && node.col < maze.get(node.row).size() &&
          maze.get(node.row).get(node.col) == Color.WHITE;
  }


  public static class Coordinate {
    public int row, col;

    public Coordinate (int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate) o;
      if (row != that.row || col != that.col) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public static enum Color { WHITE, BLACK }
}
