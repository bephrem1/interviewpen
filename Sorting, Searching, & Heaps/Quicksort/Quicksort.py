'''
    Quicksort. Picking the pivot is 'pivotal' to the  algorithm's performance ;)

    This version picks the last item in the partition space as the pivot everytime,
    there are many other ways to choose a pivot item.

    The video to explain this code is here: https://www.youtube.com/watch?v=uXBnyYuwPe8
'''


class Solution:
    '''
        Top level function that calls 'partition' and splits the data
        based on where 'partition' leaves the choosen pivot value
    '''

    def quicksort( arr, left, right):
        if left < right:
            '''
                Partition the array from left to right and find where the selected pivot 
                belongs
            '''
            pivotFinalRestingPosition = Solution.partition( arr, left, right)
            # Call quicksort to the left and to the right of the pivot
            Solution.quicksort(arr, left, pivotFinalRestingPosition - 1)
            Solution.quicksort(arr, pivotFinalRestingPosition + 1, right)
        return arr

    def partition( arr, left, right):
        '''
            In this implementation of quicksort we pick the last item in the partition
            space as the pivot everytime. This can turn out very good or very badly
        '''
        pivot = arr[right]

        '''
            I will keep track of the 'tail' of the section of items less than the pivot
            so that at the end we can 'sandwich' the pivot between the section less than
            it and the section greater than it
        '''
        i = left - 1
        # J will scan for us
        for j in range(left, right):
            '''
                If this item is less than the pivot it needs to be moved to the section 
                of items less than the pivot
            '''
            if arr[j] <= pivot:
                # Move i forward so that we can swap tha value at j into the tail
                # of the items less than the pivot
                i += 1
                Solution.swap(arr, i, j)

        '''
            Swap the pivot value right after the section of items less than the pivot.
            i keeps thetail of this section
        '''
        Solution.swap(arr, i + 1, right)
        return i + 1  # Return the pivots's final resting position

    def swap( arr, first, second):
        arr[first], arr[second] = arr[second], arr[first]

# Todo: Write testcases for this solution