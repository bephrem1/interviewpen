"""
    This repository is no longer actively maintained. To find all solutions
    to this problem (and practice coding more problems) at:

    ~~~~~~~~~~~~~~~~~~~~~~~~~
    https://backtobackswe.com
    ~~~~~~~~~~~~~~~~~~~~~~~~~
"""


class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None


class LinkedList:
    def __init__(self):
        self.head = None

    def printListIterative(head):
        """
           Common pattern. Create a reference variable to point to the
           head node so that we can operate on the LinkedList
        """
        workingPointer = head

        while workingPointer:
            """
                    Our work - this can be anything we want to do to 'node'. 'workingPointer'
                    literally points to an object on the heap that is a 'Listnode'
            """
            print(workingPointer.val)
            workingPointer = workingPointer.next  # go to the next node in the list

    def printListRecursive(node):
        if not node:
            return
        # Our work - this can be anything we want to do to 'node'
        print(node.val)

        # Continue the recursion
        LinkedList.printListRecursive(node.next)

    def printListInReverseRecursive(node):
        if not node:
            return

        """
			Recursion first - drill down to the end of the list. Base
			case will catch us.
		"""
        LinkedList.printListInReverseRecursive(node.next)

        """
			Our work. This will be fired by the last node first, then the 2nd to last node,
			and so on...because we recursed first, got a ton of nodes on the stack in recursive calls,
			and now they will run this as the recursion goes "back up"
		"""
        print(node.val)

    def printListInReverseIterative(head):
        """
                Replace call stack with our own stack
        """
        stack = []
        workingPointer = head
        while workingPointer:
            stack.append(workingPointer.val)
            workingPointer = workingPointer.next

        while stack:
            node = ListNode(stack.pop())
            print(node.val)

    def deepCopyListIterative(head):
        """
                A 'dummy head' node allows us to not have to handle the
                initial empty case of a new list not being initialized with a first node.

                We can immediately begin appending to this new list and 'chop off' this node
                at the end and it will be garbage collected (probably, if no one is using the reference later)
        """
        newListHead = ListNode(-1)

        """
			Point a working pointer to the old list, point a working pointer
			to the dummy head.
			
			We will build off the 'next' field of the dummy head immediately.
		"""
        oldListWorkingPointer = head
        newListWorkingPointer = newListHead

        while oldListWorkingPointer:
            newNode = ListNode(oldListWorkingPointer.val)

            # Append the deep copied node to the new list we are building
            newListWorkingPointer.next = newNode

            # Advance to the next node in the old list to process
            oldListWorkingPointer = oldListWorkingPointer.next

            # Move to the new node we just appended
            newListWorkingPointer = newListWorkingPointer.next

            """
				We can also do the following, same reference to the same
				node (the newly appended node):
			"""
            # "Cut off" the dummy head just leaving the pure list created
            return newListHead.next

    def reverseLinkedListIterative(head):
        """
                Common pattern. We keep 2 pointers, 1 to the previous element and 1
                to the current element
        """
        prev = None
        curr = head

        while curr:
            """
                    Another common pattern - preserve the pointer to the next node in
                    the list since we will overwrite this value later.

                    Literally preserving the pointer to the next obkect in memory that
                    we want to visit...in a reference variable.
            """
            preservedNextNode = curr.next
            curr.next = prev

            prev = curr
            curr = preservedNextNode

            """
				Useless pointer to rename things to make it clear what is happening,
				prev is pointing to the last element when the above iteration ends.
				
				That is the head of the new reversed linked list.			
			"""
            reversedLinkedListHead = prev
            return reversedLinkedListHead

    def reverseLinkedListRecursive(node):
        if not node or node.next is None:
            return node

        """
			We need to preserve teh reference to the last node in the orginal
			linked list since it will be the head of the new reversed linked list.
			
			We "bubble" that value up in our calls to the roor caller by recursing
			deep first to get reference to the last node (and in the process creating n stack
			frames on the call stack, each with reference to a node)
		"""
        headOfReversedList = LinkedList.reverseLinkedListRecursive(node.next)

        """
			When the base case is hit & returns, we will return to here.
			  1 node before the final node in the list:
		
			  O -> O -> O -> O -> O -> O -> O -> x
									   ^
									   me
			  
			  In that frame we will have reference to a node 1 before
			  the end of the linked list. We point the node 'nodeToMyRight'
			  to 'me'.
		
			  Then we cut off 'me's next because 'me' may be the last node in
			  the reversed linked list
		"""
        nodeToMyRight = node.next
        nodeToMyRight.next = node
        node.next = None

        # We continue "bubbling up" reference to the tail node up and up and up
        return headOfReversedList

    def getMiddleNodeReference(head):
        slow = head
        fast = head

        """
		Even
		O -> O -> O -> O -> O -> O -> x
		^
		^

		O -> O -> O -> O -> O -> O -> x
			 ^
				  ^

		O -> O -> O -> O -> O -> O -> x
					   ^
									  ^

		Odd
		O -> O -> O -> O -> O -> x
		^
		^

		O -> O -> O -> O -> O -> x
			 ^
				  ^

		O -> O -> O -> O -> O -> x
				  ^
							^

		Keep going until 'fast' is null or 'fast.next' is null.
		Then return the reference;
		
		"""
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        # Useless pointer to rename for teaching purposes
        middleNode = slow
        return middleNode

    # k==1 is the last node
    def getKthToLastNodeReference(head, k):

        leftOfWindow = head
        rightOfWindow = head

        """
			Move the right of the window so that the gap between the 2 references is length 'k'
			Imagine k = 3, we want:
			O -> O -> O -> O -> O -> O -> x
						   ^
			We do:
			O -> O -> O -> O -> O -> O -> x
			l
			r
			
			O -> O -> O -> O -> O -> O -> x
			l
						   r
		"""

        for i in range(k):
            rightOfWindow = rightOfWindow.next

        """
		Now we do:
		O -> O -> O -> O -> O -> O -> x
		l
						r

		O -> O -> O -> O -> O -> O -> x
			 l
							r


		O -> O -> O -> O -> O -> O -> x
				  l
								 r

		O -> O -> O -> O -> O -> O -> x
					   l
									  r
		And we have found the kth to last node
		
		"""
        while rightOfWindow:
            leftOfWindow = leftOfWindow.next
            rightOfWindow = rightOfWindow.next

        # Useless pointer to rename for teaching purposes
        kthToLastNode = leftOfWindow
        return kthToLastNode


a = ListNode(0)
b = ListNode(1)
c = ListNode(2)
d = ListNode(3)

a.next = b
b.next = c
c.next = d

head = LinkedList()
head = a
head1 = LinkedList()
head1 = a

print("printlistiterative")
LinkedList.printListIterative(head)
print('\n')

print("printListRecursive")
LinkedList.printListRecursive(head)
print('\n')

print('printListInReverseRecursive')
LinkedList.printListInReverseRecursive(head)
print('\n')

print('printListInReverseIterative')
LinkedList.printListInReverseIterative(head)
print('\n')

print('deepCopyListIterative')
newListHead = LinkedList.deepCopyListIterative(head)
LinkedList.printListIterative(newListHead)
print("New list head value: " + str(newListHead.val))
print("Are the 2 list head's equivalent in memory?" + str(a == newListHead))

print("reverseLinkedListIterative")
reversedListHead1 = LinkedList.reverseLinkedListIterative(head)
LinkedList.printListIterative(reversedListHead1)
print('\n')

"""
	Restore the orginal list by reversing again...itis sitting reversed
	in memory...we have to restore it
"""
LinkedList.reverseLinkedListIterative(reversedListHead1)

print("reverseLinkedListRecursive")
reversedListHead2 = LinkedList.reverseLinkedListRecursive(head)
LinkedList.reverseLinkedListIterative(reversedListHead2)
print('\n')

# Restore
LinkedList.reverseLinkedListIterative(reversedListHead2)

print("getMiddleNodeReference")
middle = LinkedList.getMiddleNodeReference(head)
print("Middle node value: " + str(middle.val))
print('\n')

print("getKthToLastNodeReference")
kthToLast = LinkedList.getKthToLastNodeReference(head, 3)
print("3rd To Last Node Value: " + str(kthToLast.val))
print('\n')
