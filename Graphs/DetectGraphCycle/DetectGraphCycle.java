/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean isCyclic(List<GraphVertex> graph) {
    /*
      Since the graph my not be connected, we must initiate a DFS from each
      node so that we still investigate disjoint regions
    */
    for (GraphVertex vertex: graph) {
      if (vertex.color == GraphVertex.Color.WHITE && hasCycleFromVertex(vertex)) {
        return true;
      }
    }

    return false;
  }

  private boolean hasCycleFromVertex(GraphVertex vertex) {
    /*
      If this vertex is currently being visited and we just returned to
      it that means that there is a cycle
    */
    if (vertex.color == GraphVertex.Color.GRAY) {
      return true;
    }

    /*
      Perform work - our work here is marking the vertex as being visited.
      We will undo this once we search all paths reachable from this vertex.
    */
    vertex.color = GraphVertex.Color.GRAY;

    /*
      Process adjacents - only search from the node if it is either:
        - White: The node is unprocessed
        - Gray: The cycle will be caught in the next call and 'false' will bubble
          up the call stack
    */
    for (GraphVertex adjacent: vertex.adjacents) {
      if (adjacent.color != GraphVertex.Color.BLACK) {
        if (hasCycleFromVertex(adjacent)) {
          return true;
        }
      }
    }

    // If we get here then all paths were explored and no path was found - mark this vertex as finished
    vertex.color = GraphVertex.Color.BLACK;

    // No cycle found from this vertex
    return false;
  }

  /*
    Raw alogorithm using 3 colors:
      White -> Unvisited (we can visit this node, it is not on a pending path & hasn't been processed)
      Gray -> Visiting (currently on the path being traversed)
      Black -> Visited (do not traverse again)
  */
  static class GraphVertex {
    enum Color { WHITE, GRAY, BLACK };
    Color color;
    List<GraphVertex> edges;
  }
}
