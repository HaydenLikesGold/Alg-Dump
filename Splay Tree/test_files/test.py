from BST import BSTNode,BST
from SplayTree import SplayTree

def test():
    print("Test 1 - Basic Insertion and deletion")
    splayTree1 = SplayTree()
    splayTree1.insert(BSTNode(4, splayTree1.nil, splayTree1.nil, splayTree1.nil))
    if (splayTree1.root.key != 4):
        raise RuntimeError("Root not properly Set")
    splayTree1.insert(BSTNode(7, splayTree1.nil, splayTree1.nil, splayTree1.nil))
    if (splayTree1.root.key != 7):
        raise RuntimeError("Root not properly Set")
    splayTree1.insert(BSTNode(9, splayTree1.nil, splayTree1.nil, splayTree1.nil))
    if (splayTree1.root.key != 9):
        raise RuntimeError("Root not properly Set")
    splayTree1.insert(BSTNode(3, splayTree1.nil, splayTree1.nil, splayTree1.nil))
    if (splayTree1.root.key != 3):
        raise RuntimeError("Root not properly Set")
    splayTree1.insert(BSTNode(8, splayTree1.nil, splayTree1.nil, splayTree1.nil))
    if (splayTree1.root.key != 8):
        raise RuntimeError("Root not properly Set")
    splayTree1.insert(BSTNode(11, splayTree1.nil, splayTree1.nil, splayTree1.nil))
    if (splayTree1.root.key != 11):
        raise RuntimeError("Root not properly Set")
    splayTree1.search(7)
    if (splayTree1.root.key != 7):
        raise RuntimeError("Root not properly Set")
    x = splayTree1.search(9)
    splayTree1.delete(x)
    splayTree1.print()
    print()

    print("Test 2 - Splay with given tree")
    splayTree2 = SplayTree()
    splayTree2.insert_without_splay(BSTNode(2, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.insert_without_splay(BSTNode(1, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.insert_without_splay(BSTNode(6, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.insert_without_splay(BSTNode(3, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.insert_without_splay(BSTNode(7, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.insert_without_splay(BSTNode(5, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.insert_without_splay(BSTNode(4, splayTree2.nil, splayTree2.nil, splayTree2.nil))
    splayTree2.search(3)
    if (splayTree2.root.key != 3):
        raise RuntimeError("Root not properly Set")
    splayTree2.search(4)
    if (splayTree2.root.key != 4):
        raise RuntimeError("Root not properly Set")
    splayTree2.print()
    print()

if __name__ == "__main__":
    test()
