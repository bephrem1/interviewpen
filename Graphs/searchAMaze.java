/*
  Authorship: ALL credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.
  
  I have just added explanatory comments, reformatted the code, & changed
  variable names for understanding.

  The video to explain this code is here: https://www.youtube.com/watch?v=W9F8fDQj7Ok
*/

/*
  Black cells are walls. We cannot "walk" on them.
  White cells are traversable. We can "walk" on white cells.
*/
public static enum Color { WHITE, BLACK }

/*
  A standard coordinate to represent a cell in the maze with
  an row and col position.
*/
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
    return Objects.hash(row , col);
  }

}

/*
  This is the driver function. It will return the path that we find as
  a list of Coordinate objects.
*/
public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                          Coordinate start, Coordinate end) {

  /*
    This will hold the path.
  */
  List<Coordinate> path = new ArrayList<>();

  /*
    Flip the item at start'shift position to black. We will start the search
    from here.
    
    Add the coordinate to the start of the path. It is our literal start
  */
  maze.get(start.row).set(start.col, Color.BLACK);
  path.add(start);

  /*
    If we do not find a path then remove the start node we added to the
    path and return an empty list down below
  */
  if (!searchMazeHelper(maze, start, end, path)) {
    path.remove(path.size() - 1);
  }

  /*
    If we DO find a path, the 'path' variable will have all nodes in the
    path from start to end, otherwise it will be an empty list
  */
  return path;
}

/*
  A standard DFS for the path that we want to find.
*/
private static boolean searchMazeHelper(List<List<Color>> maze, Coordinate current,
                                        Coordinate end, List<Coordinate> path) {

  if (current.equals(end)) {
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
    {1, 0}, // going up
    {0, -1}, // going left
    {-1, 0} // going down
  };

  /*
    We:

    1.) Apply each shift. next is the coordinate that is next to process according
    to the shift we are on
  */
  for (int[] shift : SHIFTS) {

    /*
      The next item to possibly add to our path and search
    */
    Coordinate next = new Coordinate(current.row + shift[®], current.col + shift[1]);

    /*
      Can this item be walked on?
    */
    if (isWalkable(next , maze)) {

      // Yes. Set it to black and add it to the path
      maze.get(next.row).set(next.col, Color.BLACK);
      path.add(next);

      /*
        Can we finish a path from here now, recurse and find out. If we can
        we will bubble up the result and this Coordinate will be in the path.
      */
      if (searchMazeHelper(maze, next, end, path)) {
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
    No 'next' Coordinates worked from this 'current' Coordinate. Return false to our
    caller. Path not possible, caller will have to keep searching.
  */
  return false;
}

/*
  Validates a given cell, it ensures it is in the maze and is white (can
  be "walked on")
*/
private static boolean isWalkable(Coordinate current, List<ListcColor>> maze) {
  return current.row >= 0 && current.row < maze.size() &&
         current.col >= 0 && current.col < maze.get(current.row).size() &&
         maze.get(current.row).get(current.col) == Color.WHITE;
}
