/*
  Clone Graph - LeetCode: https://leetcode.com/problems/clone-graph/

  Authorship: ALL credit for the code in this file goes to the authors of the
  book "Elements of Programming Interviews" by Adnan Aziz, Amit Prakash, and
  Tsung-Hsien Lee.
  
  I have just added explanatory comments, reformatted the code, & changed
  variable names for understanding.

  This code passes all Leetcode test cases as of Jan. 26 2019
  Runtime: 3 ms, faster than 76.03% of Java online submissions for Clone Graph.
  (we can't get much faster than this...)

  The video to explain this code is here: https://www.youtube.com/watch?v=vma9tCQUXk8
*/

/**
 * Definition for an undirected graph node.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

public UndirectedGraphNode cloneGraph(UndirectedGraphNode start) {

  /*
    If the start node is null then we cannot do any cloning
  */
  if (start == null) {
    return null;
  }

  /*
    vertexMap: Map the original node reference to its clone
    queue: Our queue for Breadth First Search
  */
  Map<UndirectedGraphNode, UndirectedGraphNode> vertexMap = new HashMap<>();
  Queue<UndirectedGraphNode> queue = new LinkedList<>();

  /*
    Add the start node to the queue.
    Give the start node a clone in the vertexMap
  */
  queue.add(start);
  vertexMap.put(start, new UndirectedGraphNode(start.label));

  /*
    The breadth first search continues until we have processed all vertices
    in the original graph. We know this is done when the queue is empty
  */
  while (!queue.isEmpty()) {

    /*
      We grab a node. We will express all of the edges coming off of this
      node.
    */
    UndirectedGraphNode currVertex = queue.remove();

    /*
      Iterate over all neighbors.
    */
    for (UndirectedGraphNode neighbor : currVertex.neighbors) {

      /*
        Has this neighbor been given a clone?
      */
      if (!vertexMap.containsKey(neighbor)) {
        /*
          No. Give it a mapping and add the original neighbor to the
          search queue so we can express ITS edges later
        */
        vertexMap.put(neighbor, new UndirectedGraphNode(neighbor.label));
        queue.add(neighbor);
      }

      /*
        Draw the edge from currVertex's clone to neighbor's clone.
        Do you see how our hashtable makes this quick access possible?
      */
      vertexMap.get(currVertex).neighbors.add(vertexMap.get(neighbor));
    }
  }

  /*
    Return the clone of the start. This is the entry point for the
    cloned graph section.
  */
  return vertexMap.get(start);
}
