"""
    Reverse Linked List II - Leetcode: https://leetcode.com/problems/reverse-linked-list-ii/
    This code passes all Leetcode test cases on 17.01.2020
    Runtime Complexity : O(N)
    Space Complexit: O(1)
"""

class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def reverseBetween(self, head, m, n):
        if m == n:
            return head
        dummyHead = ListNode(-1)
        dummyHead.next = head
        '''
            Create a working pointer to advance to right before the sublist head:

            pos = 1
            dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                ^
            
            pos = 2
            dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                         ^
            break, pos == start
        '''
        nodeBeforeReversedSublist = dummyHead
        pos = 1
        while pos < m:
            nodeBeforeReversedSublist = nodeBeforeReversedSublist.next
            pos += 1

        '''
            Reverse the actual sublist:

        start:
          dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                      nbrs swp
        
        1st iteration (start = 2):
          1.) Cut out of sublist
            dummyHead -> 1 -> 2 -> 3 -> 4 -> 5 -> x
                        nbrs swp nctsf
            
            dummyHead -> 1 -> 2 -> 4 -> 5 -> x
                        nbrs swp   ^
                                3--|
                              nctsf
    
          2.) Wire into sublist head
            dummyHead -> 1 -> 2 -> 4 -> 5 -> x
                        nbrs swp
                              ^ 
                           3--|
                         nctsf
    
            dummyHead -> 1 -> 3 -> 2 -> 4 -> 5 -> x
                        nbrs      swp
          
        2nd iteration (start = 3):
          1.) Cut out of sublist
            dummyHead -> 1 -> 3 -> 2 -> 4 -> 5 -> x
                       nbrs       swp nctsf
            
            dummyHead -> 1 -> 3 -> 2 -> 5 -> x
                       nbrs       swp   ^
                                     4--|
                                   nctsf
          
          2.) Wire into sublist head
            dummyHead -> 1 -> 3 -> 2 -> 5 -> x
                       nbrs   ^   swp
                           4--|
                         nctsf
    
            dummyHead -> 1 -> 4 -> 3 -> 2 -> 5 -> x
                       nbrs            swp
        
        3rd Iteration (start = 4), break while loop
        
        
        '''
        sublistWorkingPtr = nodeBeforeReversedSublist.next
        while m < n:
            # 1.) Cut out of sublist
            nodeComingToSublistFront = sublistWorkingPtr.next
            sublistWorkingPtr.next =nodeComingToSublistFront.next

            # 2.) Wire into sublist head
            nodeComingToSublistFront.next = nodeBeforeReversedSublist.next
            nodeBeforeReversedSublist.next = nodeComingToSublistFront
            m += 1
        return dummyHead.next



