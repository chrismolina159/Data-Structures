# HW2 README

**Description:**<br>
The purpose of this lab was to design a program to implement a calculator, but the calculator performs an infix to postfix conversion first and then evaluates the resutling postfix expression.

The program is split into two parts.<br>
* A converter class that converts the inpuut string to a postfix expression.
<br>
* A Calculator class that evaluates the postfix expression.

**Input Format:**<br>
The input is changing the value on line **#4** in `Calculator.java`.<br>
Example:
* Can change "Converter x = new Converter("408+(6+2^(5-1))");"<br>
to "Converter x = new Converter("2+2");"<br>

Some valid input examples:
* 3+4\*5/6
* (300+23)\*(43-21)/(84+7)
* (4+8)\*(6-5)/((3-2)\*(2+2))

**Output Format:**<br>
The program outputs the result to the console