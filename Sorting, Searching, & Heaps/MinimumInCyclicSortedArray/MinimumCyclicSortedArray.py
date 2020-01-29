'''
    Find Minimum in Rotated Sorted Array - Leetcode: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    This code passes all Leetcode test cases as of January 29, 2020
    Runtime Complexity: O(logN)
    Space Complexity: O(1)
'''


class Solution(object):
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        left = 0
        right = len(nums) -1

        while left < right:
            mid = left + (right - left)//2
            if nums[mid] > nums[right]:
                left = mid + 1
            else:
                right = mid
        return nums[left]
