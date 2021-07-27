# project-definitely-not-excel 
## Purpose Of Project
Creating a SpreadSheet Software with built in Functionalities that are much like those of Excel. The idea and main Aim is to Build Said software using only Java Libs.

Current Version is : Pre-Alpha 

Authors: Edoardo Salvioni and Anton Tanev

Requirements: OpenJDK-11

## Pre-Alpha Instructions for User Testing:
#### BluejUsers: 
Clone the repo to a local directory, open the package.bluej file either through the terminal or through the Bluej GUI. Browse to under the gui package and Create an instance of The GUIBASE class. There You go up and running.

#### For Terminal Users Clone the repo and navigate to the Path in the terminal.
Make sure your Java install meets the requirements.
call the following commands with the command line:


` ~javac DefinitelyNotExcel.java`


` ~java DefinitelyNotExcel` 


## In the software defined Function, Operators, Shortcuts, And CellIdentifiers
#### KeyboardShortcuts in Graphic User Interface
CTRL + Z -> undo.

CTRL + R -> redo.

CTRL + N -> new SpreadSheet.

CTRL + C -> Copy the currently selected cells.

CTRL + V -> Paste Cells.

CTRL + X -> Cut the currently selected cells, the in this way cut cells will be stored in the clipboard.

DEL -> delete all the currently selected cells.

When Writing within a Cell, prepend the "=" Symbol for the Calculation in the cell to occur.

For referring to a cell use XY where X is the Column of the cell and Y its row. Example A0

For referring to a range of cells use WX:YZ where W and X are the column and row of the top left Cell and Y, Z are the column and row of the bottom right cell. Example A0:B0


Functions Available for Use with Ranges:

SUM(WX:YZ) returns the sum of the range given.

AVG(WX:YZ) returns the Average of the range given.

MIN(WX:YZ) returns the Minimum number amongst the given cells.

MAX(WX:YZ) returns the Maximum number amongst the given cells.


Functions Available for Use with Single Cells and numbers:

SQRT(X) -> the square root of the Number or Single Cell within the parenthesis.

SIN(X) -> the sine of the Number or Single Cell within the parenthesis.

COS(X) -> the cosine of the Number or Single Cell within the parenthesis.

TAN(X) -> the Tangent of the Number or Single Cell within the parenthesis.


Binary Operators for arithmetic operations which work between two numbers or  Single CellIdentifiers:

\+ -> Sum

\- -> Subtraction

/ -> Division

_/ -> FloorDivision

^ -> Power

\* -> Multiplication

% -> Modulo



