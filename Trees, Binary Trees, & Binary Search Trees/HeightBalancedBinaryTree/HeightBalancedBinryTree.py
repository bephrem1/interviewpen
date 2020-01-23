"""
    This solution taken from a book "Elements of Programming Interviews".
    Some variable names has been changed and code adapted  the solution to pass Leetocde
    Balanced Binary Tree - LeetCode: https://leetcode.com/problems/balanced-binary-tree

    Common misunderstanding with this questions is that canditates understood that balance can be
    broken on root. But as said in task conditions:
    "a binary tree in which the left and right subtrees of every node differ in height by no more than 1."
    We must check balance for each node.
    Also we care about 2 things as our recursion goes upwards after bottoming out:
        - The node's height
        -  Whether its left and right subtrees are balanced

    The video to explain this code is here: https://www.youtube.com/watch?v=LU4fGD-fgJQ

"""


import collections


class Solution(object):
    def isBalanced(self, root):
        BalanedStatusWithHeight = collections.namedtuple('BalanedStatusWithHeight', ('balanced', 'height'))

        # First value of the return value indicate if tree is balanced and if
        # balanced teh second value of teh return value is the height of tree.
        def check_balanced(root):
            if not root:
                return BalanedStatusWithHeight(True, -1)  # Base case

            left_result = check_balanced(root.left)
            if not left_result.balanced:
                # Left subtree is not balanced
                return BalanedStatusWithHeight(False, 0)

            right_result = check_balanced(root.right)
            if not right_result.balanced:
                # Right subtree is not balanced
                return BalanedStatusWithHeight(False, 0)

            is_balanced = abs(left_result.height - right_result.height) <= 1
            height = max(left_result.height, right_result.height) + 1
            return BalanedStatusWithHeight(is_balanced, height)

        return check_balanced(root).balanced

"""
If we replace namedtuple with simple array list the code will work 172/36 = 4.7 times more speedy
"""


class Solution(object):
    def isBalanced(self, root):
        def checkBalanced(root):

            if not root:
                return [True, -1]

            # check left subtree
            left_result = checkBalanced(root.left)
            if not left_result[0]:
                return [False, 0]

            # check right subtree
            right_result = checkBalanced(root.right)
            if not right_result[0]:
                return [False, 0]

            is_balanced = abs(left_result[1] - right_result[1]) <= 1
            height = max(left_result[1], right_result[1]) + 1
            return [is_balanced, height]

        return checkBalanced(root)[0]