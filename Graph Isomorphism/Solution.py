import sys

def printMatrix(matrix):
    for i in range(len(matrix)):
        print(matrix[i])
    print()

def readInMatrix(sizeN):
    matrix = [[0]*sizeN for i in range(sizeN)]
    for i in range(sizeN):
        for j in range(sizeN):
            newInt = ""
            while not (newInt.isdigit()):
                newInt = sys.stdin.read(1)
            matrix[i][j] = newInt
    return matrix

def isCompat(G,H, index):
    for j in range(index+1):
        if (G[index][j] != H[pi[index]][pi[j]]):
            return False
    return True

def perm(unknown_index):
    if unknown_index == len(pi)-1: #Final Index of Permutation, simply verify compatibility
        if isCompat(A1, A2, unknown_index):
            return True
        return False
    else:
        #Many options exist, still not solved
        for j in range(unknown_index, len(pi)):
            if(isCompat(A1, A2, unknown_index)):
                if (perm(unknown_index+1)):
                    return True
                pi[unknown_index], pi[j] = pi[j], pi[unknown_index]
            else:
                pi[unknown_index], pi[j] = pi[j], pi[unknown_index]
        return False

def isomorphism():
    global A1, A2, sizeOfMatrix, pi
    sizeOfMatrix = int(input())
    A1 = readInMatrix(sizeOfMatrix)
    A2 = readInMatrix(sizeOfMatrix)
    pi = [ i for i in range(sizeOfMatrix)]
    if not perm(0):
        print("Solution: Not isomorphic")
    else:
        print("Solution: Is isomorphic via "+str(pi))

isomorphism()
