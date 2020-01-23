"""
    Symmetric Tree - LeetCode: https://leetcode.com/problems/symmetric-tree/
    Runtime complexity : O(N)
    Space complexity : O(h)
"""

class Solution(object):
    def isSymmetric(self, root):

        def checker(subtree_0, subtree_1):
            if not subtree_0 and not subtree_1:
                return True
            elif subtree_0 and subtree_1:
                return (subtree_0.val == subtree_1.val and checker(subtree_0.left, subtree_1.right)
                        and checker(subtree_0.right, subtree_1.left))
            return False
        return not root or checker(root.left, root.right)