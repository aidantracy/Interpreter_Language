# Interpreter Assignment

* Author: Aidan Tracy
* Class: CS354 Section 002
* Semester: Fall 2024

## Overview

This application is a basic programming language that utilizes a lexer to tokenize a string of data, and a parser to
split up the data into chunks that act as a part of a programming language. 

## Reflection

### Part 1: Lexical Analysis

This was an interesting and fun mini project to understand more about how scanning works with programming languages. 
A lot of logic is involved with determining what token is what and what is accepted vs what is not accepted. I didn't 
really run into issues when solving this program. I believe my program handles 99% of the edge cases that will be 
thrown at it. As I think of more edge cases I will update the tests and the code to make them pass.

This was a good project for Test driven development as you would make tests to get them to fail then update the code to
make them pass. I found that a lot of what I was coding already passed some cases then when I went to fix certain tests
that weren't passing, would make other tests not pass and vice versa. I also simplified the next() function to show
what the program was doing by simplifying each part of the next function into smaller functions that handles 1 task. 



### Part 2: Parser

This was an easier assignment than I had anticipated. The logic was easy to piece together and the recursive nodes make sense
for the parse tree. I didn't have any issues passing these tests but I also incorporated test driven development to help
these tests pass. Make a test fail then find a way to pass it without compromising the integrity of the previously passed tests.

The hardest part of this assignment was the parseFact method because you are accounting for terminals and non terminal 
grammar within the code. However, it was not difficult as additional logic checks were required.  



### Part 3: Evaluation

This was a little harder for me to conceptualize due to adding the eval method. I think my biggest mistake was just diving into modifying the code instead of refreshing myself what was happening in part 2 to really get the big picture. I had an issue with trying to determine where to put the environment hashmap and didn't realize I was muddying up the parser by trying to eval while I was parsing. It was like fitting a square peg into a round hole. After some time, I made it work then realized a completely different environment map was being used in the tests so it wouldn't have had access to the stored variables and values in the previous map that was instantiated in the parser. 

After that clicked, everything became easier to debug and understand what was happening. I learned my lesson to review the previous code first then to try to understand the new code fragments before I start doing some major modifications. This was still a fun portion and is now satisfying that we can see the interpreter start to work with very basic functionality! 


### Part 4: Control Flow

This was a lot easier to implement than part 3, but that was just because this was just more of the implementation step done from part 3. This part also introduces a scanner to allow user input into the program! At first I was a little confused on what the scanner was doing in the tests and for some reason my IDE was not recognizing the BytesArray as a class that is already a part of another library. So I thought I had to create and instantiate that class. Then the next day my IDE decided to recognize it and import it.  

It is really neat to see the project come together, and now it can accept full simple programs! The dangling if else problem is interesting. I believe I implemented it correctly, but I do have another idea if it isn't implemented correctly. If it isn't correct then I believe implementing another function with the parser to look for a future "else" token and then returning a boolean value to determine if the current else in question is a part of the inner or outer "if" token. Right now it is defaulted to the inner if stmt. 

### Part 5: Scope

This was a very easy part of the program to implement because the first solution I thought of when making this program
was able to make this work! When starting to work on the parseStmt() function in the parser, I realized I had been
working at the previous parts at an odd "angle" because I would return a stmt type for parseAssn and parseDecl when they should have
been returning Assn and Decl, respectively. I fixed all of that and implemented the scope map in the environment. I also
created a StackFrame class that would handle multiple types of maps for different types of data types that I plan on
working on when I can.

## Results

 I created more tests that would test for scope and multiple scopes and they all pass! 


## Sources used

- ASCII values from: https://www.ascii-code.com/

----------

## Notes

* This README.md template is using Markdown. Here is some help on using Markdown:
  [markdown cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)


* Markdown can be edited and viewed natively in most IDEs such as Eclipse and VS Code. Just toggle
  between the Markdown source and preview tabs.

* To preview your README.md output online, you can copy your file contents to a Markdown editor/previewer
  such as [https://stackedit.io/editor](https://stackedit.io/editor).