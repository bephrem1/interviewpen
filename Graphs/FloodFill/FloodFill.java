/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int[][] floodFill(int[][] image, int row, int col, int newColor) {
    int regionOriginalValue = image[row][col];
    
    Queue<Coordinate> queue = new LinkedList<>();
    Set<Coordinate> visited = new HashSet<>();
    
    Coordinate start = new Coordinate(row, col, image[row][col]);
    queue.add(start);
    visited.add(start);
    
    while (!queue.isEmpty()) {
      Coordinate curr = queue.poll();
      
      // Perform work
      image[curr.row][curr.col] = newColor;
      
      /*
          going right - col +1
          going down - row +1
          going left - col -1
          going up - row -1
      */
      int[][] directions = { {0 , 1}, {1, 0}, {0, -1}, {-1, 0} };

      for (int[] direction: directions) {
        int rowShift = direction[0];
        int colShift = direction[1];
        
        int newRow = curr.row + rowShift;
        int newCol = curr.col + colShift;
        
        if (!isInBounds(newRow, newCol, image)) {
          continue;
        }
        
        Coordinate next = new Coordinate(newRow, newCol, image[newRow][newCol]);

        if (!visited.contains(next) && next.pixelValue == regionOriginalValue) {
          queue.add(next);
          visited.add(next);
        }
      }
    }
    
    return image;
  }

  private boolean isInBounds(int row, int col, int[][] image) {
    return row >= 0 && row < image.length && col >= 0 && col < image[row].length;
  }

  class Coordinate {
    private int row;
    private int col;
    private int pixelValue;
    
    Coordinate(int row, int col, int pixelValue) {
      this.row = row;
      this.col = col;
      this.pixelValue = pixelValue;
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
      if (this.row != that.row || this.col != that.col) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.row, this.col);
    }
  }
}
