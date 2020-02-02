class Solution {
  public List<Integer> distanceK(TreeNode treeRoot, TreeNode startNode, int targetDistance) {
    Map<TreeNode, TreeNode> nodeToParentMap = new HashMap();
    populateNodeToParentMap(nodeToParentMap, treeRoot, null);

    Queue<TreeNode> queue = new LinkedList();
    queue.add(startNode);

    /*
      The is an undirected graph now that we can go to and from nodes.
      Before we could only go down the tree.

      Therefore, we need a hashtable to keep track of nodes we have
      visited so that we do not go back and revisit what has already
      been processed and cause an infinite cycle
    */
    Set<TreeNode> seen = new HashSet();
    seen.add(startNode);

    int currentLayer = 0;
    while (!queue.isEmpty()) {
      if (currentLayer == targetDistance) {
        return extractLayerFromQueue(queue);
      }

      /*
        How large is this layer? Let's process all node in the layer.
        This is Breadth First Search.
      */
      int layerSize = queue.size();
      for (int i = 0; i < layerSize; i++) {
        /*
          Pull a node from the search queue, we are going to basically
          use our current layer to populate the next layer of nodes
          that we need to search in the next while loop iteration
        */
        TreeNode currentNode = queue.poll();

        if (currentNode.left != null && !seen.contains(currentNode.left)) {
          seen.add(currentNode.left);
          queue.offer(currentNode.left);
        }
        
        if (currentNode.right != null && !seen.contains(currentNode.right)) {
          seen.add(currentNode.right);
          queue.offer(currentNode.right);
        }

        TreeNode parentOfCurrentNode = nodeToParentMap.get(currentNode);
        if (parentOfCurrentNode != null && !seen.contains(parentOfCurrentNode)) {
          seen.add(parentOfCurrentNode);
          queue.offer(parentOfCurrentNode);
        }
      }

      currentLayer++;
    }

    return new ArrayList<Integer>();
  }

  /*
    When this recursion is done we will know all nodes' parents. The tree then
    can be treated and searched like any other graph
  */
  private void populateNodeToParentMap(
    Map<TreeNode, TreeNode> nodeToParentMap,
    TreeNode root,
    TreeNode parent
  ) {
    if (root == null) {
      return;
    }

    // Map the node to its parent
    nodeToParentMap.put(root, parent);

    /*
      Go left and after that go right. The call that we make next
      will have what we call 'root' now as the 'parent' value so
      we can do the mapping in THAT call stack frame...and so on
      and so on...
    */
    populateNodeToParentMap(nodeToParentMap, root.left, root);
    populateNodeToParentMap(nodeToParentMap, root.right, root);
  }

  private List<Integer> extractLayerFromQueue(Queue<TreeNode> queue) {
    List<Integer> extractedList = new ArrayList();
    for (TreeNode node: queue) {
      extractedList.add(node.val);
    }
    return extractedList;
  }
}
