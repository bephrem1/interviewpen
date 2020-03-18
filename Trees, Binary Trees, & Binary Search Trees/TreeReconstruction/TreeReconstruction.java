/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  int preorderIndex = 0;

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    /*
     * Save duplicate work for searches against the inorder traversal data & map
     * node value to the index that node sits at
     */
    Map<Integer, Integer> inorderNodeToIndex = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderNodeToIndex.put(inorder[i], i);
    }

    return build(preorder, inorder, 0, inorder.length - 1, inorderNodeToIndex);
  }

  private TreeNode build(int[] preorder, int[] inorder, int inorderStart, int inorderEnd,
      Map<Integer, Integer> inorderNodeToIndex) {
    if (inorderStart > inorderEnd) {
      return null;
    }

    int nodeValue = preorder[preorderIndex];
    TreeNode node = new TreeNode(nodeValue);
    preorderIndex += 1;

    if (inorderStart == inorderEnd) {
      return node;
    }

    int inorderIndex = inorderNodeToIndex.get(nodeValue);

    node.left = build(preorder, inorder, inorderStart, inorderIndex - 1, inorderNodeToIndex);
    node.right = build(preorder, inorder, inorderIndex + 1, inorderEnd, inorderNodeToIndex);

    return node;
  }
}
