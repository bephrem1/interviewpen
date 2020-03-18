/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public static void main(String args[]) {
    /*
      Here you are given the adjacency list, but even if you were
      given the raw edges and vertices (remember: G = (V, E)), you'd
      still be able to build the adjacency list in O(|E|) time.

      Just loop over the edges and build what you see below.
    */
    Map<Integer, List<Integer>> originalNodeToAdjacents = new HashMap<>();
    originalNodeToAdjacents.put(0, Arrays.asList(1));
    originalNodeToAdjacents.put(1, Arrays.asList(2));
    originalNodeToAdjacents.put(2, Arrays.asList(3, 4));
    originalNodeToAdjacents.put(3, Arrays.asList(0));
    originalNodeToAdjacents.put(4, Arrays.asList(2));

    /*
      The graph above:

        0 ---> 1 ---> 2 <---> 4
        ^            /
         \         /
           \     /
             \  ⌄
               3
    */

    /*
      If we reverse each edge:

        0 <--- 1 <--- 2 <---> 4
         \            ^
          \         /
            \     /
             ⌄   /
               3
    */

    System.out.println(isStronglyConnected(originalNodeToAdjacents));
  }

  private static boolean isStronglyConnected(Map<Integer, List<Integer>> originalNodeToAdjacents) {
    boolean allNodesReached = allNodesReachedViaBFS(originalNodeToAdjacents);

    if (!allNodesReached) {
      return false;
    }

    Map<Integer, List<Integer>> reversedNodeToAdjacents = reverseGraph(originalNodeToAdjacents);
    boolean allNodesReachedWithReverseGraph = allNodesReachedViaBFS(reversedNodeToAdjacents);

    return allNodesReachedWithReverseGraph;
  }

  private static boolean allNodesReachedViaBFS(Map<Integer, List<Integer>> originalNodeToAdjacents) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();

    queue.add(0);
    visited.add(0);

    while (!queue.isEmpty()) {
      int node = queue.poll();
      List<Integer> adjacents = originalNodeToAdjacents.get(node);

      if (adjacents != null) {
        for (int adjacent: adjacents) {
          if (!visited.contains(adjacent)) {
            visited.add(adjacent);
            queue.add(adjacent);
          }
        }
      }
    }

    return visited.size() == originalNodeToAdjacents.size();
  }

  private static Map<Integer, List<Integer>> reverseGraph(Map<Integer, List<Integer>> originalNodeToAdjacents) {
    Map<Integer, List<Integer>> reversedNodeToAdjacents = new HashMap<>();

    for (Map.Entry<Integer, List<Integer>> entry: originalNodeToAdjacents.entrySet()) {
      int nodeValue = entry.getKey();
      List<Integer> adjacents = entry.getValue();

      // Reverse each edge
      for (int adjacent: adjacents) {
        List<Integer> reversedAdjacents;

        if (!reversedNodeToAdjacents.containsKey(adjacent)) {
          reversedAdjacents = new ArrayList<>();
        } else {
          reversedAdjacents = reversedNodeToAdjacents.get(adjacent);
        }

        // Reverse edge, before node went to adjacent, now adjacent maps to the node
        reversedAdjacents.add(nodeValue);
        reversedNodeToAdjacents.put(adjacent, reversedAdjacents);
      }
    }

    return reversedNodeToAdjacents;
  }
}
