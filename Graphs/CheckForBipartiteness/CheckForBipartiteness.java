/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean isBipartite(int[][] adjList) {
    if (adjList.length == 0) {
      return false;
    }

    // Initialize the coloring
    Color[] coloring = getInitialColoring(adjList);

    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();

    /*
      Graph may not be connected, we will start a BFS from each vertex,
      but once a vertex is visited it can't be processed again so this
      still stays to O(|V|+|E|)
    */
    for (int i = 0; i < adjList.length; i++) {
      if (!visited.contains(i)) {
        int start = i;

        // BFS so we use a queue
        coloring[start] = Color.WHITE;
        queue.add(start);

        // Undirected graph so avoid infinite cycles
        visited.add(start);

        while (!queue.isEmpty()) {
          int vertex = queue.poll();

          for (int adj: adjList[vertex]) {
            Color adjColor = coloring[adj];

            if (adjColor == Color.UNCOLORED) {
              coloring[adj] = oppositeColor(vertex, coloring);
            } else if (adjColor == coloring[vertex]) {
              return false;
            }

            if (!visited.contains(adj)) {
              queue.add(adj);
              visited.add(adj);
            }
          }
        }
      }
    }

    return true;
  }

  private Color oppositeColor(int adjacent, Color[] coloring) {
    return coloring[adjacent] == Color.BLACK ? Color.WHITE : Color.BLACK;
  }

  private Color[] getInitialColoring(int[][] adjList) {
    Color[] coloring = new Color[adjList.length];

    for (int i = 0; i < adjList.length; i++) {
      coloring[i] = Color.UNCOLORED;
    }

    return coloring;
  }

  private enum Color {
    BLACK,
    WHITE,
    UNCOLORED
  }
}
