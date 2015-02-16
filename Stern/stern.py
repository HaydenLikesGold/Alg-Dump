class Node:
	def __init__(self, numerator, denominator):
		self.value = (numerator, denominator)
		self.left_parent = None
		self.right_parent = None

	def __str__(self): #used primarly in debugging
		return "Value: "+str(self.value)

class Path:
	def __init__(self):
		self.root = Node(1,1)
		self.root.left_parent = Node(0,1)
		self.root.right_parent = Node(1,0)
		self.current_node = self.root
		self.path = ""

	def buildPath(self, n, d):
		while(True):
			if (n/d < self.current_node.value[0]/self.current_node.value[1]):
				self.path += "L"
				new_value = self.findKinkRight(self.current_node)
				next_node = Node(new_value[0], new_value[1])
				next_node.right_parent = self.current_node
				self.current_node = next_node
			elif (n/d > self.current_node.value[0]/self.current_node.value[1]):
				self.path +="R"
				new_value = self.findKinkLeft(self.current_node)
				next_node = Node(new_value[0], new_value[1])
				next_node.left_parent = self.current_node
				self.current_node = next_node
			else:
				return self.path

	def findKinkRight(self, node):
		current_node = node
		kinkValue = None
		baseValue = current_node.value
		while(current_node.right_parent is not None):
			if (current_node.value == (1,1)):
				kinkValue = current_node.left_parent.value
				break;
			else:
				current_node = current_node.right_parent
		if (kinkValue is None):
			kinkValue = current_node.left_parent.value
		return (baseValue[0]+kinkValue[0], baseValue[1]+kinkValue[1])

	def findKinkLeft(self, node):
		current_node = node
		kinkValue = None
		baseValue = current_node.value
		while(current_node.left_parent is not None):
			if (current_node.value == (1,1)):
				kinkValue = current_node.right_parent.value
				break;
			else:
				current_node = current_node.left_parent
		if (kinkValue is None):
			kinkValue = current_node.right_parent.value
		return (baseValue[0]+kinkValue[0], baseValue[1]+kinkValue[1])


import sys

for line in sys.stdin:
    values = line.split(" ")
    path = Path()
    n, d= values[0], values[1]
    if not (n is "1" and d is "1"):
    	print(path.buildPath(int(n), int(d)))
