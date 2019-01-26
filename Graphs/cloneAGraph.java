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

  The video to explain this code is here: [a link will live here someday]
*/

public UndirectedGraphNode cloneGraph(UndirectedGraphNode start) {

  if(start == null) {
    return null;
  }

  Map<UndirectedGraphNode, UndirectedGraphNode> vertexMap = new HashMap<>();
  Queue<UndirectedGraphNode> queue = new LinkedList<>();

  queue.add(start);
  vertexMap.put(start, new UndirectedGraphNode(start.label));

  while(!queue.isEmpty()) {

    UndirectedGraphNode currVertex = queue.remove();

    for (UndirectedGraphNode neighbor : currVertex.neighbors) {

      if (!vertexMap.containsKey(neighbor)) {
        vertexMap.put(neighbor, new UndirectedGraphNode(neighbor.label));
        queue.add(neighbor);
      }

      vertexMap.get(currVertex).neighbors.add(vertexMap.get(neighbor));
    }
  }

  return vertexMap.get(start);
}
