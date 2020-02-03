"""
    Similar question on Leetcode: Third Maximum Number
    Link: https://leetcode.com/problems/third-maximum-number/

  Thanks to GeeksForGeeks for this code sample. I just cleaned it up
  and added explanatory comments: https://www.geeksforgeeks.org/find-second-largest-element-array/

  The video to explain* this code is here: https://www.youtube.com/watch?v=NheWPxGpoxQ

"""
import unittest

class Solution:
    def secondLargest(arr):
        # We are searching for largest number thats why we initialize variables as negative infiniti
        firstLargest = float("-inf")
        secondLargest = float("-inf")
        if len(arr) < 2:
            raise Exception('Array to small to have 2nd largest item')
        '''
            We will check every element and compete it for a position 
            amongst firstLargest and secondLargest 
        '''
        for i in range(len(arr)):
            '''
                If this item beats the largest seen so far, 
                make the firstLargest the new secondLargest
                
                arr[i] the becomes the new firstLargest
            '''
            if arr[i] > firstLargest:
                secondLargest = firstLargest
                firstLargest = arr[i]
            elif arr[i] > secondLargest and arr[i] != firstLargest:
                '''
                    This element becomes the secondLargest if it does not beat
                    the firstLargest BUT it beats the secondLargest AND is NOT
                    the same as the firstLargest item (so we don't unnecessarily
                    eject the secondLargest value if this item IS the same as the 
                    firstLargest value)
                '''
                secondLargest = arr[i]
        if secondLargest == float("-inf"):
            return float("-inf")
        else:
            return secondLargest


class TestEqual(unittest.TestCase):

    def test_equal(self):
        self.assertEqual(Solution.secondLargest([4, 7, 8, 3, 12, 27, 9]), 12, "Should be 7")

    def test_equal_1(self):
        self.assertEqual(Solution.secondLargest([10, 10, 8, -9, 23, -10]), 10, "Should be 7")

    def test_equal_2(self):
        self.assertEqual(Solution.secondLargest([3, 3, 3, 3]), float("-inf"), "Should be 7")

    def test_equal_3(self):
        self.assertEqual(Solution.secondLargest([-1, 3, 2, 7, 9, 4, 6]), 7, "Should be 7")

if __name__ == '__main__':
    unittest.main()
