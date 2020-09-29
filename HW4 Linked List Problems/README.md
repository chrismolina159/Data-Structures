# HW4 README

**Description:**<br>
This homework assignment consists of solving three different problems using linked lists. The answer to question one and two are in the LLHomeworkFunctions.java file, and question three is the FastMaxStack.java file. Here are the questions and their descriptions.
1. The Intersection of Linked Lists
	* `getIntersection(Node<E> list1, Node<E> list2)` returns a Node<E> list of nodes with elements that appear in both list1 and list.
	* If there aren't any elements, the method should return `null`.
2. Checking if a list is recursive
	* `terminates(Node<E> list1)` returns true/false if the LinkedList terminates.
3. Implementing a Max-Stack
	* `FastMaxStack.java` is an optimized version of another "MaxStack" class.
	* `push(E element)`, `pop()`, and `getMaxSoFar()` all have a runtime of O(1).

**Input Format:**<br>
The input requires the user to write in the code. If a user wants to test out problems #1 and #2, then the user has to create linked list(s) in the `main` method of LLHomeworkFunctions. From there, you can print out to console the results of the methods. Similarly, you can instantiate an instance of FastMaxStack and SlowMaxStack push elements into both to check if they return the same values.

**Output Format:**<br>
The user can write 'print' statements to output their results to the console.
