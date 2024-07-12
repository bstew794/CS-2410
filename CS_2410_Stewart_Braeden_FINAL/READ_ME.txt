Hello User, and thank you for purchasing our Sudoku java software!

Before we begin playing, make sure that you have unsolved Sudoku board .txt files
moved to just outside of the package folder; please make sure these are boards are
indeed solvable or else the program will run an error. The format is displayed below:

0 0 0 4 7 5 8 0 9
0 0 0 8 0 1 0 2 0
0 0 0 0 3 0 7 0 0
0 9 0 0 0 0 1 0 6
0 7 6 3 5 9 2 4 0
5 0 8 0 0 0 0 3 0
0 0 2 0 1 0 0 0 0
0 6 0 5 0 7 0 0 0
7 0 9 2 4 3 0 0 0

***PLAYING THE GAME***
Once you are ready to play, run Sudoku.java; you will see a window pop up with the label
"Please enter the filename" with an empty input field along and a "Submit" button next to it.
Enter the name of the .txt file that you saved the board in (e.x. sudoku2.txt) and press the
"Submit" button. If you do not enter a valid filename, or the file is not found, then a red
label will appear saying, "ERROR: File not found" and the stack trace will be printed to the console.

While your Sudoku game is laoding, a Loading screen will be present; if the laoding screen does not
disappear after 5 seconds, please contact our support representatives.

After the loading screen, two windows will pop up; one of which is the game board and the other as a
chat window. The game board will display the given numbers in bold, and are not editable. Any blank
space can be written in with numbers 1 to 9. If you are stuck or are lazy the press the "Hint" button.
Pressing this button will fill two blank spaces with the correct answer in blue (these are non-editable)
as well as highlighting any incorrect numbers in red.

***WINNING THE GAME***
To the left of the "Hint" button is the "Check" button which will tell you if the current board
configuration is the solution or not. If it is not the solution, a window will display
"The current configuration is not a win state" and won't let you continue play until you've
closed the window. If the current configuration does match the solution then the board window
will close and a new window will be siplay "You won!" along with a button asking if you want to
play another sudoku board.

Pressing the button will cause the application to loop back to the original window where you can
input another sudoku board .txt file.

***CREDITS***

Software done by:
Jacob Tucker
Braeden Stewart