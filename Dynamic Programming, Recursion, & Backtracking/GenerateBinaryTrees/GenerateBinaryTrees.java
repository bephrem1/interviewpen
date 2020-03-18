/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      List<TreeNode> trees = new ArrayList<>();

      return trees;
    }

    return generateTrees(1, n);
  }

  // Generates all binary search trees from nodes numbered 'start' to 'end'
  private List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> trees = new ArrayList<>();

    // 'start' has passed 'end', we cannot generate anymore nodes
    if (start > end) {
      trees.add(null);
      return trees;
    }

    /*
      Imagine the iteration for n = 5, we need to generate trees 5 nodes in size. Here is
      how the calls would compose themselves.

      Top level stack frame:
        start = 1
        end = 5

      i = 1
                1
            /       \
      gen(1, 0)  gen(2, 5)

      i = 2
                2
            /       \
      gen(1, 1)  gen(3, 5)

      i = 3
                3
            /       \
      gen(1, 2)  gen(4, 5)

      i = 4
                4
            /       \
      gen(1, 3)  gen(5, 5)

      i = 5
                5
            /       \
      gen(1, 4)  gen(6, 5)
    */
    for (int i = start; i <= end; i++) {
      /*
        The left subtree for tree rooted at 'i' is all the subtrees generated
        from 'start' to 'i - 1'
      */
      List<TreeNode> leftSubtrees = generateTrees(start, i - 1);

      /*
        The right subtree for tree rooted at 'i' is
        all the subtrees generated from 'i + 1' to 'end'
      */
      List<TreeNode> rightSubtrees = generateTrees(i + 1, end);

      // Take Cartesian product of the left & right subtrees that come back from the recursion
      for (TreeNode left: leftSubtrees) {
        for (TreeNode right: rightSubtrees) {
          // 'i' gets placed as the root of all subtrees generated from this stack frame
          TreeNode newNode = new TreeNode(i);
          newNode.left = left;
          newNode.right = right;

          trees.add(newNode);
        }
      }
    }

    return trees;
  }
}
