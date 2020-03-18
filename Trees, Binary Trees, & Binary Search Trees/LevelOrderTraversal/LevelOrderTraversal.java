/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<List<Integer>> levelOrderTraversal(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> levelsList = new ArrayList<List<Integer>>();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    /*
      We will continue the breadth first search as long as
      there are nodes to process.
      
      We do not need a hashtable to track nodes already
      visited since a tree is an acyclic connected graph.

      Each iteration will process a layer of the tree.
    */
    while (!queue.isEmpty()) {
      List<Integer> currentLayer = new ArrayList<>();

      /*
        We will get the size of the layer in the queue
        that we need to process so that we know how
        many nodes our for loop needs to process
      */
      int layerSize = queue.size();

      for (int i = 0; i < layerSize; i++) {
        TreeNode currentNode = queue.poll();

        currentLayer.add(currentNode.val);

        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }

        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }

      /*
        Notice how currentLayer is a new list on each iteration
        of the while loop. We do not need to worry about deep
        copying anything here.
      */
      levelsList.add(currentLayer);
    }

    return levelsList;
  }
}
