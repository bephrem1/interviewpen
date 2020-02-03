'''
    This problem named as:
        EPI: Sort an almost sorted array
        Pramp: K-Messed Array Sort
        Following code passes all tests in pramp site on 03 February 2020

    The video to explain this code: https://www.youtube.com/watch?v=yQ84lk-EXTQ

'''

import heapq


def sort_k_messed_array(arr, k):
    minHeap = []
    n = len(arr)

    '''
        Add the first k elements to the min heap. Guard against the case that
        there are less than k items in the whole array
    '''
    for i in range(k):
        heapq.heappush(minHeap, arr[i])

    '''
        Add and place...add and place...add and place from the heap. Initially, we
        added items from index 0 to index k - 1. We continue reading from index k
        and begin our minimum element placements at index 0.

        Continue while the read index stays within the index bounds of the array.
        When it runs over we will have no more items to add to the heap for consideration.
    '''
    readIndex = k
    placementIndex = 0
    while readIndex < n:
        '''
           Add the next element to be considered in the heap. The heap will
            hold at max k + 1 items. 
        '''
        heapq.heappush(minHeap, arr[readIndex])
        readIndex += 1
        '''
            Remove the smallest item to place into the array. This item will be
            placed where it belongs by the definition of what k represents.
        '''
        arr[placementIndex] = heapq.heappop(minHeap)
        placementIndex += 1

    # Empty out the rest of the heap & do placements.
    while minHeap:
        arr[placementIndex] = heapq.heappop(minHeap)
        placementIndex += 1
    return arr
