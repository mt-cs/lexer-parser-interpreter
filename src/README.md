### Project 2: Lexer, Parser, Interpreter

##### 100 points

##### Due Wed 11/4

In this project, you'll be integrating several concepts that you've learned about in earlier labs to implement a an interpreter that can read and evaluate inputs. You will:

- see how to use Stacks, Trees, Lists and HashMaps in a larger program
- Learn how to tokenize, parse and evaluate sentences in a high-level language

Advice:

Build this incrementally. Start small, and be diligent about unit testing as you go. There are lots of interlocking pieces. The debugger is also very helpful for this project.
 I've provided some skeleton code to help guide you through this.
 
 
Refer to the lecture slides as well for details.

Part 1: Lexer.(30 points)

A lexer is a tool that takes a String as input and emits a list of Tokens. (I've provided the Token class for you.)

A Token has a type and a value. Our language has the following Tokens:

- Integers: At least one digit in a row.
- Floats: One or more digits, followed by ., followed by more digits.
- Identifiers. A letter, followed by letters or numbers.
- Operators: +, -, *, /
- Assignment: =
- Expression assignment: #
- We also have an EOF token, to denote the end of file.

Tokens can have whitespace between them, but it's not required.

- Implement the methods in Lexer so that getAllTokens repeatedly calls getNextToken, which  looks at the current position in the input buffer, determines what token is next, and calls the appropriate method. It should return a list of Tokens. If the input is invalid, throw an 
IllegalArgumentException and indicate where the lexer stopped.
 
 Part 2: Expression Tree. (30 points)
 
 Note: this is the most complex part of the project. Start by just parsing expressions, then work on the interpreter. That will help you see how it all fits together.
 
 The expression tree is a data structure that stores a computation. Leaves contain numbers or variables, and parent nodes contain operators. We can compute the result by doing an inorder traversal.
 
 Start by implementing the methods that parse operators, numbers and identifiers. They should all return Nodes. If they get unexpected input, they should throw an IllegalArgumentEception indicating a parse error, and the token being processed.
 
 Next, implement parseExpression. Use the shunting algorithm presented in the lecture slides to construct an expression tree, calling the methods you just implemented. Throw an IllegalArgumentEception indicating a parse error, and the token being processed if the input is invalid.
 
 Next, implement evaluate. This should do an postorder traversal of the tree - each operator node should evaluate its left branch, evaluate its right branch, apply the operator, and return the result. Leaves should either return their value (if they're a number) or look up their value in the Symbol Table (if they're an identifier.)
 
 (at this point, you might want to jump to part 3, get the basic shell running, and try it out.)
 
 Next, implement parseAssignment. Parse the identifier and assignment operator, parse the expression on the right-hand side, evaluate it, and store the result in the Symbol Table.
 
 Part 3: Interpreter: (30 points)
 
 The interpreter is the engine that runs everything. It should be able to either:
 
 - prompt the user for input, read in a string, run it through the lexer, then parse it and display the result.
 - Read a series of sentences from a file and sequentially parse them and display their results.
 
 It should have a verbose mode that displays input and output, along with the symbol table. This will be helpful for debugging.
 
 Part 4: Expression assignment. (10 points)
 
 A cool feature of expression trees is the fact tht they're both code and data. We'll use this to store them as a function and execute them later.
 
 Implement the parseExprAssignment method. Like parseAssignment, it should grab the identifer, parse the exprAssignment operator, and then parse the expression on the right-hand side. But, rather than evaluating it, you should store the expression tree in the Symbol Table.
 Then, modify evaluate() so that when that identifier is evaluated, the corresponding expression tree is fetched from the symbol table and evaluated.
   