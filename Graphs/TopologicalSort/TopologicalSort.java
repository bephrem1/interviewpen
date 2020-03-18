/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public static void main(String args[]) {
    smallGraphExample();
    biggerGraphExample();
  }

  private static void biggerGraphExample() {
    Set<Integer> vertices = new HashSet<>();
    vertices.add(1);
    vertices.add(2);
    vertices.add(3);
    vertices.add(4);
    vertices.add(5);
    vertices.add(6);
    vertices.add(7);

    Set<Edge> edges = new HashSet<>();
    edges.add(new Edge(1, 4));
    edges.add(new Edge(1, 5));
    edges.add(new Edge(1, 7));
    edges.add(new Edge(2, 3));
    edges.add(new Edge(2, 5));
    edges.add(new Edge(2, 6));
    edges.add(new Edge(3, 4));
    edges.add(new Edge(3, 5));
    edges.add(new Edge(4, 5));
    edges.add(new Edge(5, 6));
    edges.add(new Edge(5, 7));
    edges.add(new Edge(6, 7));

    /*
      Graph (hard to draw the edges, view them here: https://imgur.com/a/uXjbvOO)

          2       3
     
     6        5        4

        7           1

      via Algorithm Design by Jon Kleinberg & Éva Tardos
    */

    // A Topological Ordering: [ 1, 2, 3, 4, 5, 6, 7 ]
    List<Integer> topologicalOrdering = getTopologicalOrdering(vertices, edges);
    System.out.println(Arrays.toString(topologicalOrdering.toArray()));
  }

  private static void smallGraphExample() {
    Set<Integer> vertices = new HashSet<>();
    vertices.add(1);
    vertices.add(2);
    vertices.add(3);
    vertices.add(4);

    Set<Edge> edges = new HashSet<>();
    edges.add(new Edge(1, 2));
    edges.add(new Edge(2, 4));
    edges.add(new Edge(3, 1));
    edges.add(new Edge(3, 4));

    /*
      Graph:

      1  -►  2
      ▲      |
      |      ▼
      3  -►  4
    */

    // The Topological Ordering: [ 3, 1, 2, 4 ]
    List<Integer> topologicalOrdering = getTopologicalOrdering(vertices, edges);
    System.out.println(Arrays.toString(topologicalOrdering.toArray()));
  }

  private static List<Integer> getTopologicalOrdering(Set<Integer> vertices, Set<Edge> edges) {
    List<Integer> topologicalOrdering = new ArrayList<>();

    /*
      Notate the starting indegree of every vertex, we know that an edge
      is "coming into" a node if it is at the 'head' of the edge (meaning
      that is is the destination of the edge, not the start)
    */
    Map<Integer, Integer> vertexToIndegree = new HashMap<>();
    Map<Integer, List<Integer>> vertexToAdjacents = new HashMap<>();

    // Initialize tables
    for (Integer vertex: vertices) {
      vertexToIndegree.put(vertex, 0);
      vertexToAdjacents.put(vertex, new ArrayList<>());
    }

    // Populate tables
    for (Edge edge: edges) {
      // Update the indegree mapping
      vertexToIndegree.put(edge.end, vertexToIndegree.get(edge.end) + 1);

      // Update the adjacency mapping
      List<Integer> adjacents = vertexToAdjacents.get(edge.start);
      adjacents.add(edge.end);
      vertexToAdjacents.put(edge.start, adjacents);
    }

    // Take note of indegree 0 vertices, we will start with one
    PriorityQueue<Integer> indegreeZeroVertices = new PriorityQueue<>();
    for (Integer vertex: vertices) {
      if (vertexToIndegree.get(vertex) == 0) {
        indegreeZeroVertices.add(vertex);
      }
    }

    // Iteratively build the ordering, subtracting edges along the way
    while (!indegreeZeroVertices.isEmpty()) {
      int vertex = indegreeZeroVertices.poll();
      topologicalOrdering.add(vertex);

      List<Integer> adjacents = vertexToAdjacents.get(vertex);
      for (int adjacent: adjacents) {
        // here we could remove edge {vertex, adjacent} from 'edges'

        // 'Destroy' the edge going to 'adjacent'
        vertexToIndegree.put(adjacent, vertexToIndegree.get(adjacent) - 1);

        if (vertexToIndegree.get(adjacent) == 0) {
          indegreeZeroVertices.add(adjacent);
        }
      }
    }

    return topologicalOrdering;
  }

  private static class Edge {
    int start; // The 'tail' of the edge
    int end; // The 'head' of the edge

    public Edge(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
