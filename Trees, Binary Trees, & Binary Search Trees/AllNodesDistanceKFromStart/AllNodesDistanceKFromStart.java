/*
  All Nodes Distance K in Binary Tree - LeetCode: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

  This code passes all Leetcode test cases as of Feb. 12th 2019
  Runtime: 3 ms, faster than 64.42% of Java online submissions for All Nodes Distance K in Binary Tree.
  (can't get much faster than that. Asymptotically this is identical to anything even remotely faster)

  The video to explain this code is here: https://www.youtube.com/watch?v=nPtARJ2cYrg
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public List<Integer> distanceK(TreeNode treeRoot, TreeNode startNode, int targetDistance) {

  /*
    Create the node to parent map and populate it
  */
  Map<TreeNode, TreeNode> nodeToParentMap = new HashMap();
  populateNodeToParentMap(nodeToParentMap, treeRoot, null);

  /*
    Create the queue that we will use for the breadth first search.
    Add the start node to the queue
  */
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

  /*
    When our search starts, we are standing at layer 0
  */
  int currentLayer = 0;

  while (!queue.isEmpty()) {

    /*
      Is this the layer we want? If so, extract and return it
    */
    if (currentLayer == targetDistance) {
      return extractLayerFromQueue(queue);
    }

    /*
      How large is this layer? Let's process all node in the layer.
      This is Breadth First Search.
    */
      ArrayList<TreeNode> al = new ArrayList<>();
			al.addAll(queue);
			queue.clear();
			for (int i = 0; i < al.size(); i++)
			{
				/*
				 * Pull a node from the search queue, we are going to basically use our current
				 * layer to populate the next layer of nodes that we need to search in the next
				 * while loop iteration
				 */
				TreeNode currentNode = al.get(i);

      /*
        Has left been touched before?
        No?
          1.) Add it to the seen hashtable
          2.) Add it to the search queue
      */
      if (currentNode.left != null && !seen.contains(currentNode.left)) {
          seen.add(currentNode.left);
          queue.offer(currentNode.left);
      }

      /*
        Has right been touched before?
        No?
          1.) Add it to the seen hashtable
          2.) Add it to the search queue
      */
      if (currentNode.right != null && !seen.contains(currentNode.right)) {
        seen.add(currentNode.right);
        queue.offer(currentNode.right);
      }

      /*
        Has this node's parent been touched before?
        No?
          1.) Add it to the seen hashtable
          2.) Add it to the search queue
      */
      TreeNode parentOfCurrentNode = nodeToParentMap.get(currentNode);
      if (parentOfCurrentNode != null && !seen.contains(parentOfCurrentNode)) {
        seen.add(parentOfCurrentNode);
        queue.offer(parentOfCurrentNode);
      }

    }

    /*
      Advance the layer for the next iteration
    */
    currentLayer++;

  }

  return new ArrayList<Integer>();
}

/*
  When this recursion is done we will know all nodes' parents. The tree then
  can be treated and searched like any other graph
*/
private void populateNodeToParentMap(Map<TreeNode, TreeNode> nodeToParentMap,
                                  TreeNode root, TreeNode parent) {

  /*
    We can't add a null node to the map, return
  */
  if (root == null) {
    return;
  }

  /*
    Map the node to its parent
  */
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
