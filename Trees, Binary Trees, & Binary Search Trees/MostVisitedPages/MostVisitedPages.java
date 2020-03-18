/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  private static class LogProcessor {
    Map<String, TreeNode> pageIdToNode;
    TreeNode root;

    public LogProcessor() {
      pageIdToNode = new HashMap<>();
    }

    public List<String> topKVisitedPages(int k) {
      List<String> topKItems = new ArrayList<>();
      Counter nodesLeftToCollect = new Counter(k);

      topKHelper(this.root, nodesLeftToCollect, topKItems);

      return topKItems;
    }

    private void topKHelper(TreeNode subtreeRoot, Counter nodesLeftToCollect, List<String> topKItems) {
      if (subtreeRoot == null || nodesLeftToCollect.count() == 0) {
        return;
      }

      topKHelper(subtreeRoot.right, nodesLeftToCollect, topKItems);

      if (nodesLeftToCollect.count() != 0) {
        topKItems.add(subtreeRoot.pageId);
        nodesLeftToCollect.decrement();
      }

      topKHelper(subtreeRoot.left, nodesLeftToCollect, topKItems);
    }

    /*
      There is no balancing provision so the tree printed
      out may look weird
    */
    public void insert(String visitedPageId) {
      TreeNode pageNode = null;

      if (pageIdToNode.containsKey(visitedPageId)) {
        // 1.) Exists: Get the reference, remove from tree, update the count
        pageNode = pageIdToNode.get(visitedPageId);
        remove(pageNode);
        pageNode.visitCount++;
      } else {
        // 2.) Does not exist: Create the new node with 'visitCount' of 1
        pageNode = new TreeNode(visitedPageId);
        pageIdToNode.put(visitedPageId, pageNode);
      }

      // Insert into 'balanced' BST
      add(pageNode);
    }

    private void add(TreeNode nodeToInsert) {
      if (this.root == null) {
        this.root = nodeToInsert;
        return;
      }

      addHelper(this.root, nodeToInsert);
    }

    private TreeNode addHelper(TreeNode subtreeRoot, TreeNode nodeToInsert) {
      if (subtreeRoot == null) {
        return nodeToInsert;
      }

      if (nodeToInsert.compareTo(subtreeRoot) < 0) {
        subtreeRoot.left = addHelper(subtreeRoot.left, nodeToInsert);
      } else if (nodeToInsert.compareTo(subtreeRoot) > 0) {
        subtreeRoot.right = addHelper(subtreeRoot.right, nodeToInsert);
      }

      return subtreeRoot;
    }

    private void remove(TreeNode node) {
      this.root = removeHelper(this.root, node);
    }

    private TreeNode removeHelper(TreeNode subtreeRoot, TreeNode nodeToRemove) {
      if (subtreeRoot == null) {
        return null;
      }

      if (nodeToRemove.compareTo(subtreeRoot) < 0) {
        subtreeRoot.left = removeHelper(subtreeRoot.left, nodeToRemove);
      } else if (nodeToRemove.compareTo(subtreeRoot) > 0) {
        subtreeRoot.right = removeHelper(subtreeRoot.right, nodeToRemove);
      } else {
        if (subtreeRoot.left == null) {
          TreeNode right = subtreeRoot.right;
          subtreeRoot.clearPointers();

          return right;
        } else if (subtreeRoot.right == null) {
          TreeNode left = subtreeRoot.left;
          subtreeRoot.clearPointers();

          return left;
        }

        // Cache reference to this subtree root, we will replace its reference
        TreeNode oldSubtreeRoot = subtreeRoot;
        TreeNode newSubtreeRoot = minimum(oldSubtreeRoot.right);

        /*
          Rewire the 'subtreeRoot' as the 'newSubtreeRoot' reference (the min item in the
          'oldSubtreeRoot's right subtree).
        */
        subtreeRoot = newSubtreeRoot;

        // Wire up the right of the 'newSubtreeRoot'
        newSubtreeRoot.right = removeHelper(oldSubtreeRoot.right, newSubtreeRoot);
        newSubtreeRoot.left = oldSubtreeRoot.left;

        oldSubtreeRoot.clearPointers();
      }

      return subtreeRoot;
    }

    private TreeNode minimum(TreeNode subtreeRoot) {
      if (subtreeRoot == null) {
        return null;
      }

      TreeNode curr = subtreeRoot;
      while (curr.left != null) {
        curr = curr.left;
      }

      return curr;
    }

    // Some printing code I got from Stack Overflow, just ignore, will help you if you run this
    private void printTree() {
      StringBuilder tree = printTreeHelper(this.root, new StringBuilder(), true, new StringBuilder());

      if (tree != null) {
        System.out.println(tree.toString());
      }
    }

    public StringBuilder printTreeHelper(TreeNode node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
      if (node == null) {
        return null;
      }

      if (node.right != null) {
        printTreeHelper(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
      }

      sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");

      if (node.left != null) {
        printTreeHelper(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
      }

      return sb;
    }
  }

  private class TreeNode implements Comparable<TreeNode> {
    String pageId;
    int visitCount;

    TreeNode left;
    TreeNode right;

    public TreeNode(String pageId) {
      this.pageId = pageId;
      this.visitCount = 1;
    }

    public void clearPointers() {
      this.left = null;
      this.right = null;
    }

    public String toString() { 
      return "(" + this.pageId + "," + this.visitCount + ")";
    }

    @Override
    public int compareTo(TreeNode that) {
      if (this.visitCount < that.visitCount) {
        return -1;
      } else if (this.visitCount > that.visitCount) {
        return 1;
      } else {
        return this.pageId.compareTo(that.pageId);
      }
    }
  }

  private class Counter {
    int counter;

    public Counter(int counter) {
      this.counter = counter;
    }

    public void decrement() {
      counter--;
    }

    public int count() {
      return counter;
    }
  }
}
