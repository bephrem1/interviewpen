/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public static void main(String args[]) {
    List<Edge> edges = new ArrayList<>();

    edges.add(new Edge('a', 'b', 5));
    edges.add(new Edge('a', 'e', 17));

    edges.add(new Edge('b', 'a', 5));
    edges.add(new Edge('b', 'e', 12));
    edges.add(new Edge('b', 'f', 8));
    edges.add(new Edge('b', 'c', 13));

    edges.add(new Edge('c', 'b', 13));
    edges.add(new Edge('c', 'f', 11));
    edges.add(new Edge('c', 'g', 6));

    edges.add(new Edge('d', 'e', 10));
    edges.add(new Edge('d', 'f', 4));

    edges.add(new Edge('e', 'a', 17));
    edges.add(new Edge('e', 'b', 12));
    edges.add(new Edge('e', 'd', 10));
    edges.add(new Edge('e', 'f', 8));

    edges.add(new Edge('f', 'd', 4));
    edges.add(new Edge('f', 'e', 8));
    edges.add(new Edge('f', 'b', 8));
    edges.add(new Edge('f', 'c', 11));
    edges.add(new Edge('f', 'g', 14));

    edges.add(new Edge('g', 'c', 6));
    edges.add(new Edge('g', 'f', 14));

    /*
                  'b'
      'a'                     'c'


                'e'
                               'g'
        'd'             'f'
    */

    lazyPrims(edges);
  }

  /*
    Prim's w/ lazy edge elimination.
    (elimination when an edge connects a tree vertex to another tree vertex)
  */
  private void lazyPrims(List<Edge> edges) {
    List<Edge> mstEdges = new ArrayList<>();
    Set<Character> mstNodes = new HashSet<>();
    Character startNode = 'a';

    Map<Character, List<Edge>> vertexToAdjacents = buildAdjacencyMapping(edges);
    PriorityQueue<Edge> candidates = new PriorityQueue<>(10, new EdgeComparator()); // min heap on weight

    // Initialize the algorithm with the node denoted as 'a', an arbitrarily choosen start
    List<Edge> edgesAdjacentToStart = vertexToAdjacents.get(startNode);
    for (Edge e: edgesAdjacentToStart) {
      candidates.add(e);
    }
    mstNodes.add(startNode);

    while (!candidates.isEmpty()) {
      Edge lowestCostEdge = candidates.poll();
      Character nonTreeVertex = lowestCostEdge.end;

      // Does this edge connect to a non-tree vertex?
      if (!mstNodes.contains(nonTreeVertex)) {
        mstEdges.add(lowestCostEdge);
        mstNodes.add(nonTreeVertex);

        // Add edges coming off the just added vertex
        List<Edge> nonTreeVertexAdjacents = vertexToAdjacents.get(nonTreeVertex);
        for (Edge e: nonTreeVertexAdjacents) {
          candidates.add(e);
        }
      }
    }

    mstInfo(mstEdges);
  }

  // Left unimplemented, reference: https://www.cs.cmu.edu/~avrim/451f13/lectures/lect0912.pdf
  private void kruskals(List<Edge> edges) { }

  /***************   Helpers   ***************/

  private Map<Character, List<Edge>> buildAdjacencyMapping(List<Edge> edges) {
    Map<Character, List<Edge>> vertexToAdjacents = new HashMap<>();

    for (Edge e: edges) {
      List<Edge> adjacentEdges;

      if (!vertexToAdjacents.containsKey(e.start)) {
        adjacentEdges = new ArrayList<>();
      } else {
        adjacentEdges = vertexToAdjacents.get(e.start);
      }

      adjacentEdges.add(e);
      vertexToAdjacents.put(e.start, adjacentEdges);
    }

    return vertexToAdjacents;
  }

  private static void mstInfo(List<Edge> edges) {
    int totalCost = 0;

    for (Edge e: edges) {
      System.out.println(e.toString());
      totalCost += e.weight;
    }

    System.out.println("Total MST cost: " + totalCost);
  }

  /***************   Classes   ***************/

  private class Edge {
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return "Edge: {" + this.start + "," + this.end + "} w/ cost " + this.weight;
    }
  }

  private static class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge e1, Edge e2) {
      return Integer.compare(e1.weight, e2.weight);
    }
  }
}
