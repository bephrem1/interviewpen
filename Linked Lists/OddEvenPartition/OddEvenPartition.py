'''
    This repository is no longer actively maintained. To find all solutions
    to this problem (and practice coding more problems) at:

    ~~~~~~~~~~~~~~~~~~~~~~~~~
    https://backtobackswe.com
    ~~~~~~~~~~~~~~~~~~~~~~~~~
'''

class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None


class LinkedList:
    def __init__(self):
        self.head = None


class Solution:
    def oddEvenList(self, head):
        if head is None or head.next is None:
            return head
        oddDummy = ListNode(-1)
        evenDummy = ListNode(-1)

        oddTail = oddDummy
        evenTail = evenDummy

        index = 0
        curr = head
        while curr:
            if index % 2 == 0:
                evenTail.next = curr
                evenTail = curr
            else:
                oddTail.next = curr
                oddTail = curr

            curr = curr.next
            index += 1
        evenTail.next = oddDummy.next
        oddTail.next = None

        return evenDummy.next


class WithLessPointers:
    def oddEvenList(self, head):
        if not head or not head.next:
            return head

        even = head
        odd = head.next

        oddHead = odd

        while odd and odd.next:
            even.next = odd.next
            even = odd.next
            odd.next = even.next
            odd = even.next
        even.next = oddHead
        return head
