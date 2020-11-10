# HW6 README

**Description:**<br>
For part 1 of the Huffman homework assignment, this program creates a Huffman Tree based on the given frequencies of characters. For a brief description of a Huffman Tree, its leaf nodes are characters with their frequencies, and its internal nodes are combinations of its children's characters with the sum of their frequencies. Additionally, each left child is labeled a 0 and each right a 1(to create the 0,1 encoding for the string). Each path from the rootnode to each leafnode creates a string made of 0's and 1's.

**Input Format:**<br>
The input is a string of letters and numbers separated by a space.
Ex: `A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2`
The input is hard coded in the main method of `HuffmanTree.java`.

**Output Format:**<br>
There is no output.