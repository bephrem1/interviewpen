'''

    These are the 3 fundamental tree traversals all done recursively.
    Moreover you can do lever order traversal, zigzag traversal etc.
    These can be done iteratively as well as in O(1) space, but then they get trickier
    to implement but don't worry.If you can grasp these, you can grasp those.

    All codes here are
        O(N) - runtime complexity
        O(h) space complexit(h-height tree).
'''

class BinaryTreeNode:
    def __init__(self, data=None, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right


    def printPreOrder(self, tree):
        # In each recursion code will print root data
        # then goes to left node and in the next itreation print its data
        # when the deepest left node goes back and print right nodes data

        if not tree:
            return
        print(tree.data)
        self.printPreOrder(tree.left)
        self.printPreOrder(tree.right)

    def printInOrder(self, tree):
        # Recursion goes to deepest left leaf then
        # when next left node doesn't exists it print node data
        # Then from deepest left node goes his parent then again goes to right

        if not tree:
            return
        self.printInOrder(tree.left)
        print(tree.data)
        self.printInOrder(tree.right)

    def printPostOrder(self, tree):
        # In the beginning recursion goes to depest left node
        # when reach end print its data then return back right node
        # and print its data at the end of each recursion stack print root data
        if not tree:
            return
        self.printPostOrder(tree.left)
        self.printPostOrder(tree.right)
        print(tree.data)


root = BinaryTreeNode(1)

root.left = BinaryTreeNode(2)
root.right = BinaryTreeNode(3)

root.left.left = BinaryTreeNode(4)
root.left.right = BinaryTreeNode(5)

root.right.left = BinaryTreeNode(6)
root.right.right = BinaryTreeNode(7)

'''
This picture belongs to above tree.

         1
      /     \   
     2       3
    / \     /   \
   4   5   6     7

'''

print('Preorder traversal of Binary Tree')
root.printPreOrder(root)

print('Inorder traversal of Binary Tree')
root.printInOrder(root)

print('Postorder traversal of Binary Tree')
root.printPostOrder(root)