from __future__ import print_function
from BST import BSTNode,BST
import random

class SplayTree(BST):
    """A splay tree.
    """

    def left_rotate(self, x):
        y = x.right
        x.right = y.left
        if y.left != self.nil:
            y.left.p = x
        y.p = x.p
        if x.p == self.nil:
            self.root = y
        elif x == x.p.left:
            x.p.left = y
        else:
            x.p.right = y
        y.left = x
        x.p = y

    def right_rotate(self, x):
        y = x.left
        x.left = y.right
        if y.right != self.nil:
            y.right.p = x
        y.p = x.p
        if x.p == self.nil:
            self.root = y
        elif x == x.p.right:
            x.p.right = y
        else:
            x.p.left = y
        y.right = x
        x.p = y

    def splay(self, x):
        while(x.p != self.nil):
            if (x.p.p == self.nil):
                if(x.p.left == x):
                    self.right_rotate(x.p)
                else:
                    self.left_rotate(x.p)
            elif (x.p.left == x) and (x.p.p.left == x.p):
                self.right_rotate(x.p.p)
                self.right_rotate(x.p)
            elif (x.p.right == x) and (x.p.p.right == x.p):
                self.left_rotate(x.p.p)
                self.left_rotate(x.p)
            elif (x.p.left == x) and (x.p.p.right == x.p):
                self.right_rotate(x.p)
                self.left_rotate(x.p)
            else:
                self.left_rotate(x.p)
                self.right_rotate(x.p)

        """input("Enter to display new tree")
        self.print()
        input()
        """
    def search(self, k):
        x = BST.search(self, k)
        self.splay(x)
        return x

    def insert(self, z):
        BST.insert(self, z)
        self.splay(z)

    def insert_without_splay(self, z):
        BST.insert(self, z)

    def delete(self, z):
        if (z.p != self.nil):
            parent = z.p
            BST.delete(self, z)
            splay(parent)
        BST.delete(self, z)

def main():
    splayTree = SplayTree()
    print("First insert 20 random nodes into an empty tree and print the tree.")
    for i in range(20):
        n = random.randint(0, 1000)
        splayTree.insert(BSTNode(n, splayTree.nil, splayTree.nil, splayTree.nil))
    print()
    splayTree.print()
    print()

    print("\nThen insert 80 more random nodes into the tree.")
    for i in range(80):
        n = random.randint(0, 1000)
        splayTree.insert(BSTNode(n, splayTree.nil, splayTree.nil, splayTree.nil))
    print("These keys in inorder are: ")
    splayTree.inorder()
    print()

    print("\nNow do 200 searches. ")
    for i in range(200):
        n = random.randint(0, 1000)
        if splayTree.search(n) == splayTree.nil:
            print(n, " not found")
        else:
            print(n, " found")

    print("\nNow search for & delete some nodes.")
    for i in range(2000):
        n = random.randint(0, 1000)
        z = splayTree.search(n)
        if z != splayTree.nil:
            splayTree.delete(z)
    print("The keys in the trees in inorder are now: ")
    splayTree.inorder()
    print()

    print("\nThe final tree looks like this.")
    print()
    splayTree.print()
    print()

if __name__ == "__main__":
    main()
