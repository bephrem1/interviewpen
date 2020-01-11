"""
        Leetcode : https://leetcode.com/problems/design-linked-list/
    Geekforgeeks : https://www.educative.io/edpresso/how-to-create-a-linked-list-in-python
    This code passed all test cases in Leetcode on 10.01.2020:
"""

class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None


class MyLinkedList(object):
    def __init__(self):
        self.head = None
        self.size = 0

    def get(self, index):
        if index < 0 or index >= self.size:
            return -1

        dummyHead = self.head
        for i in range(index):
            dummyHead = dummyHead.next
        return dummyHead.val

    def addAtHead(self, val):
        newNode = ListNode(val)
        newNode.next = self.head
        self.head = newNode
        self.size += 1

    def addAtTail(self, val):
        newNode = ListNode(val)

        curr = self.head
        while curr.next:
            curr = curr.next
        curr.next = newNode
        self.size += 1

    def addAtIndex(self, index, val):
        if index > self.size:
            return

        newNode = ListNode(val)
        curr = self.head
        if index <= 0:
            newNode.next = curr
            self.head = newNode
        else:
            for i in range(index - 1):
                curr = curr.next
            newNode.next = curr.next
            curr.next = newNode
        self.size += 1

    def deleteAtIndex(self, index):
        if index < 0 or index >= self.size:
            return

        curr = self.head
        if index == 0:
            self.head = self.head.next
        else:
            for i in range(index - 1):
                curr = curr.next
            curr.next = curr.next.next
        self.size -= 1