
import random
class Solution:
    def quickselect(arr, k):
        '''
            Keep track of the 'left' and 'right' space in which the
            k'th largest element can possibly be, we will use these bounds
            to know what section to actually partition around a choosen pivot
        '''
        n = len(arr)
        left = 0
        right = n - 1

        # While the bounds stay valid continue doing directed partitioning
        while left <= right:
            # Pick o random pivot bounds are left right
            choosenPivotIndex = random.randint(left, right)

            '''
                Execute the actual partitioning and get back the final position
                of the pivot we choosen after partitioning is over
            '''

            finalIndexOfChoosenPivot = Solution.partition(arr, left, right, choosenPivotIndex)

            # What does the 'finalIndexOfChoosenPivot' tell us?
            if finalIndexOfChoosenPivot == n-k:
                '''
                    Found. The pivot is index on index n-k. This is literally ist final position if
                    the array we were given had been sorted. See how we saved work?
                    We don't need to sort the whole array.
                '''
                return arr[finalIndexOfChoosenPivot]
            elif finalIndexOfChoosenPivot > n-k:
                '''
                    k'th largest must be in the left partition. We 'overshot' and need to go 
                    left (and we do this by narrowing the right bound)
                '''
                right = finalIndexOfChoosenPivot - 1

            else:
                '''
                    finalIndexOfChoosenPivot < n-k
                    k'th largest must be in the right partition. We undershot and need to go right
                    (and we do this by narrowing the left bound)
                '''
                left = finalIndexOfChoosenPivot + 1

    def partition(arr, left, right, pivotIndex):
        # Grab the value at the pivot index we passed in
        pivotValue = arr[pivotIndex]
        ''' 
            Remember how partitioning works? We need to keep track of where 
            we last placed an item in the section of items 'less than the pivot'

            We keep track of the tail index of this section. We will need this later
        '''

        lesserItemsTailIndex = left
        '''
            Move the item at the 'pivotIndex' OUT OF THE WAY. We are about to
            bulldoze through the partitioning space and we don't want it in the way
        '''
        Solution.swap(arr, pivotIndex, right)
        '''
            Iterate from the left bound to 1 index right before the right bound.
            (Where the choosen pivot value is not sitting safety)
        '''
        for i in range(left, right):
            '''
                If this item is less than the 'pivotValue' then we need to move
                this item to the section of items "less than the pivot"

                'lesserItemsTailIndex' keeps track of where we need to swap into
                next...after doing the swap we advance it...you see hw this is 
                coming togethet
            '''
            if arr[i] < pivotValue:
                Solution.swap(arr, i, lesserItemsTailIndex)
                lesserItemsTailIndex += 1

            '''
                Ok...partitioning is done. Swap the pivot item BACK into the space we just 
                partitioned at the 'lesserItemsTailIndex'...that's where the pivot item 
                belongs

                In the middle of the "sandwich"
            '''
        Solution.swap(arr, right, lesserItemsTailIndex)
        '''
            Return the index of where we just put the pivot so that the caller knows its
            final resting place (so that the caller can make the decision it needs)
        '''
        return lesserItemsTailIndex

    def swap(arr, first, second):
        arr[first], arr[second] = arr[second], arr[first]


arr = [3, 2, 1, 5, 6, 4]
k = 2
print(Solution.quickselect(arr, k))