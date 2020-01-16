public Node cloneGraph(Node start) {
  // If the start node is null then we cannot do any cloning
  if (start == null) {
    return null;
  }

  /*
    vertexMap: Map the original node reference to its clone
    queue: Our queue for Breadth First Search
  */
  Map<Node, Node> vertexMap = new HashMap<>();
  Queue<Node> queue = new LinkedList<>();

  // Add the start node to the queue. Give the start node a clone in the vertexMap
  queue.add(start);
  vertexMap.put(start, createNode(start.val));

  /*
    The breadth first search continues until we have processed all vertices
    in the original graph. We know this is done when the queue is empty
  */
  while (!queue.isEmpty()) {
    // We grab a node. We will express all of the edges coming off of this node.
    Node currVertex = queue.remove();

    // Iterate over all neighbors.
    for (Node neighbor: currVertex.neighbors) {
      // Has this neighbor been given a clone?
      if (!vertexMap.containsKey(neighbor)) {
        /*
          No? Give it a mapping and add the original neighbor to the
          search queue so we can express ITS edges later
        */
        vertexMap.put(neighbor, createNode(neighbor.val));
        queue.add(neighbor);
      }

      /*
        Draw the edge from currVertex's clone to neighbor's clone.
        Do you see how our hashtable makes this quick access possible?
      */
      vertexMap.get(currVertex).neighbors.add(vertexMap.get(neighbor));
    }
  }

  // Return the clone of the start. This is the entry point for the cloned graph section.
  return vertexMap.get(start);
}

// Can't modify external lib's constructor so abstracting here
private Node createNode(int value) {
  Node newNode = new Node(value);
  newNode.neighbors = new ArrayList<>();

  return newNode;
}
