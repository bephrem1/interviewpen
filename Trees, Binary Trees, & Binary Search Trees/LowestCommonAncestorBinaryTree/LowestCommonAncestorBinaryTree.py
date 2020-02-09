'''
    Lowest Common Ancestor of a Binary Tree - LeetCode:
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree

    This code passes all Leetcode test cases as of Jan. 1st 2019

    The video to explain this code is here: https://www.youtube.com/watch?v=py3R23aAPCA

'''


class Solution(object):
    def lowestCommonAncestor(self, root, p, q):


        def lca_helper(root, p, q):
            '''
                Our base cases.
                How our recursion stops.
                When we have an answer.

                1.) If the node we are holding is null then we can't search...just return null
                2.) If we find either value return ourselves to the caller

                Remember, we are "grabbing"/"holding" 'root' in this call.
            '''

            if root is None:
                return None
            if root.val == p.val or root.val == q.val:
                return root
            '''
                Root doesn't satisfy and of our base cases.
                Search left and then search right.
            '''
            leftSearchResult = lca_helper(root.left, p, q)
            rightSearchResult = lca_helper(root.right, p, q)
            '''
                If nothing turned up on the left, return whatever we got
                back on the right
            '''
            if leftSearchResult is None:
                return rightSearchResult
            '''
                If nothing turned up on the right, return whatever we got
                back on the left
            '''
            if rightSearchResult is None:
                return leftSearchResult

            '''
                If we reach here that means we got something back on the left AND
                right...that means this node is the LCA...because our recursion returns
                from bottom to up...so we return what we hold...'root'
            '''
            return root

        return lca_helper(root, p, q)
