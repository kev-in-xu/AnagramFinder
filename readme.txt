Kevin Xu (kkx2102)
Dec 17 2023
AnagramFinder Final Project

1. How long does your program take to produce the answer when using the bst, avl, and hash flags? 
Copy the results into this readme file.

Data
Structure	| Average 	| Trial 1 	| Trial 2 	| Trial 3 	| Trial 4 	| Trial 5
------------------------------------------------------------------------------------
	bst		| 1367.2ms	| 1355ms	| 1356ms	| 1386ms	| 1385ms	| 1354ms
------------------------------------------------------------------------------------
	avl		| 500.2ms	| 498ms		| 497ms		| 501ms		| 507ms		| 498ms
------------------------------------------------------------------------------------
	hash	| 219.8ms	| 218ms		| 220ms		| 219ms		| 222ms		| 220ms

(terminal output found below)


2. What data structure do you expect to have the best (fastest) performance? 
Which one do you expect to be the slowest? Do the results of timing your program’s execution 
match your expectations? If so, briefly explain the correlation. If not, what run times deviated 
and briefly explain why you think this is the case.

(n = number of words in dictionary or reference file)

For BSTs, the runtime for insertion (put()) and search (get()) are both O(n). If the tree was perfectly balanced,
the runtime would be O(lg n). This is because the height of a balanced binary tree is lg(n), so it would take at
most lg(n) comparisons to find the node for retrieval or insertion. However, there is no way to rebalance in BST. 
Hence, if a list of integers in increasing order was inserted into the BST, the result would be a degenerate and
have a runtime of O(n) for subsequent insertions and searches.

AVL trees are balanced binary trees. As a result, their runtime is theta(lg n) for insertion and O(lg n) for 
search (since search can end early if the matching key is found, whereas insertion needs to find an empty node.)
Rebalancing trees does require additional computation that is lacking in BST, but having a balanced tree still
makes AVL a more efficienct data structure than BST. This is reflected in the results, since the running 
AnagramFinder using AVL trees was more than twice as fast as usign BST.

Hash tables are even faster, as it functions by looking up of a value in a large table using a hashcode. In
open hashing, which is our case, the hash table has an insertion runtime of theta(1), since the input is added
to the beginning of the list at each key. The average search runtime is also theta(1). This runtime depends on 
the hash function's ability to map different inputs to different keys in the table. If all the inputs mapped onto 
the same key, then we would have to traverse the whole list, leading to a search runtime of O(n). However, if 
different inputs are hashed evenly throughout the table, then then search runtime becomes theta(1). This improvement
is also seen in the actual runtimes, where using hash tables is more than twice as fast as AVL and more than 
5 times as fast as BST.


-----------------------------------Sources-----------------------------------------------

https://reintech.io/blog/java-file-handling-reading-writing-text-binary-files
* for usage of FileReader and BufferedReader

https://www.freecodecamp.org/news/string-to-array-in-java-how-to-convert-a-string-to-an-array-in-java
* for converting from String to char array

--------------------------------terminal output------------------------------------------

TRIAL 1
java AnagramFinder least words.txt bst  1.46s user 0.05s system 111% cpu 1.355 total
java AnagramFinder least words.txt avl  0.70s user 0.03s system 145% cpu 0.498 total
java AnagramFinder least words.txt hash  0.36s user 0.02s system 175% cpu 0.218 total

TRIAL 2
java AnagramFinder least words.txt bst  1.44s user 0.05s system 109% cpu 1.356 total
java AnagramFinder least words.txt avl  0.70s user 0.03s system 146% cpu 0.497 total
java AnagramFinder least words.txt hash  0.37s user 0.02s system 176% cpu 0.220 total

TRIAL 3
java AnagramFinder least words.txt bst  1.49s user 0.06s system 111% cpu 1.386 total
java AnagramFinder least words.txt avl  0.70s user 0.03s system 145% cpu 0.501 total
java AnagramFinder least words.txt hash  0.36s user 0.02s system 177% cpu 0.219 total

TRIAL 4
java AnagramFinder least words.txt bst  1.52s user 0.05s system 113% cpu 1.385 total
java AnagramFinder least words.txt avl  0.69s user 0.03s system 143% cpu 0.507 total
java AnagramFinder least words.txt hash  0.36s user 0.02s system 173% cpu 0.222 total

TRIAL 5
java AnagramFinder least words.txt bst  1.44s user 0.05s system 110% cpu 1.354 total
java AnagramFinder least words.txt avl  0.69s user 0.03s system 145% cpu 0.498 total
java AnagramFinder least words.txt hash  0.36s user 0.02s system 176% cpu 0.220 total



