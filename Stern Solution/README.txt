Hayden Goldstien
Homework 6
----------------

I worked on this problem by myself. After looking at the example tree, I quickly realized it was a BST. This helped me greatly when working towards the solution. I then decided that rather than computing then entire tree to the level needed, then completing a depth first search, I would skip the first part and try to simulate the depth first search by computing the values in the path and appending to a string that would hold the "L" and "R" information. My approach went extremley well. 

There are a few spots that could be refactored such as the finding kink operation. But, I felt that the code now is fairly clear and the functions simply mirror each other. If this function was being called reapeatdely on large sets of data, and needed quick responses, I would consider saving the results in an actual tree, then when building a path, check if it exists already, and if it does just return it. However, the time spent checking for nodes on a path might be better spent simply recomputing the result if the operations needed are not very time intensive.