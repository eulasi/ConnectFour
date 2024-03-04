# Connect Four Code Logic

```
+---------------------------------+
|         MainActivity            |
+---------------------------------+
| - board: Array<IntArray>        |
| - currentPlayer: Int            |
| - winner: Int?                  |
+---------------------------------+
| + onCreate(savedInstanceState:  |
|   Bundle?): Unit                |
+---------------------------------+
          ▲
          | Inheritance
          |
+---------------------------------+
|       ComponentActivity         |
+---------------------------------+
```
## MainActivity.kt
* MainActivity of the app where the UI is set.
* setContent is where you define your app's UI. It's a DSL (domain-specific language). several remember calls store the state. remember is used to remember the state of variables across recompositions.

### Variables:
* board holds the game state.
* currentPlayer toggles between player 1 and 2.
* winner holds the information about whether there's a winner.

### In the ConnectFourBoard call:
#### boardState
This parameter represents the current state of the Connect Four game board. It is a 2D array of integers, where each integer represents a cell on the board. The array has 6 rows and 7 columns, corresponding to the 6x7 grid of a standard Connect Four game. The boardState is used to keep track of where each player has placed their discs, and it's updated after each move.
* 0 indicates an empty cell where no player has placed a disc yet.
* 1 might represent a cell occupied by the first player's disc (commonly represented by the color red).
* 2 might represent a cell occupied by the second player's disc (commonly represented by the color yellow).

#### onCellClick
This is a higher-order function that defines what should happen when a cell in the grid is clicked. It takes two integers as parameters: the row and column of the clicked cell. The function is used to handle the game's logic, such as placing a new disc in the clicked column or determining if the move is valid (for example, you can only place a disc in the lowest available row in a given column).

#### onRestart
This higher-order function is executed when the game needs to be restarted. It resets the boardState to its initial state with all cells empty and sets the winner to null, indicating no player has won yet.

#### winner
The winner parameter holds the state of the game's outcome. It's an integer that signifies which player has won the game, if any. If the winner is null, the game is still ongoing. If it has a value of 1 or 2, it means that player 1 (red) or player 2 (yellow) has won, respectively. When winner is not null, the ConnectFourBoard composable displays an AlertDialog to announce the game's result. The dialog tells which player has won and provides a "Restart" button to reset the game.


```
+-----------------------------------+
|         ConnectFourGame           |
+-----------------------------------+
|                                   |
+-----------------------------------+
| + checkWin(board:                 |
| Array<IntArray>): Boolean         |
+-----------------------------------+
```
## ConnectFourGame.kt
* This class includes a companion object, which holds a static function checkWin that can be called without an instance of the class.
* The function checkWin checks if there's a winning condition on the board.

_Inside the inner loops, it checks four consecutive cells (board[row][col] to board[row][col + 3])._

#### Horizontal Check
1) The outer loop goes through each row from 0 to 5 (since there are 6 rows in total).
2) The inner loop goes through the columns from 0 to 3. It stops at 3 because from any column starting from 4 to 6, you can't have a horizontal sequence of four (as it would go out of the board's bounds).
3) If they are all the same and not equal to zero (meaning they are filled with a player's disc), it returns true, indicating a win.

#### Vertical Check
1) The outer loop iterates through each column from 0 to 6.
2) The inner loop goes through the rows from 0 to 2. It stops at 2 because from any row starting from 3 to 5, you can't have a vertical sequence of four (it would go out of the bottom of the board).
3) It checks four vertically consecutive cells. If all four cells have the same non-zero value, it returns true.

#### Diagonal Check (Top-Left to Bottom-Right):
1) The outer loop goes through the rows from 0 to 2. A diagonal win in this direction starting from row 3 or below would go out of the board.
2) The inner loop goes through the columns from 0 to 3. A diagonal win starting from column 4 or beyond would go out of the board's right edge.
3) It checks a diagonal line of four cells moving from top-left to bottom-right. If they are all the same non-zero value, it indicates a win.

#### Diagonal Check (Bottom-Left to Top-Right):
1) The outer loop goes through the rows from 3 to 5. This time we start from 3 because we're checking a diagonal moving upwards; starting any lower doesn't allow for a sequence of four.
2) The inner loop goes through the columns from 0 to 3. Similar to other checks, starting from column 4 or beyond would not allow for a sequence of four within the board limits.
3) It checks a diagonal line of four cells moving from bottom-left to top-right. If they match and are non-zero, it's a win.
```
+-----------------------------------+
|            BoardCell              |
+-----------------------------------+
| Start                             |
|    |                              |
|    V                              |
|  Set color parameter              |
|    |                              |
|    V                              |
|  Set onClick action               |
|    |                              |
|    V                              |
|  Create Box composable            |
|    |                              |
|    V                              |
|  Apply padding of 2.dp            |
|    |                              |
|    V                              |
|  Set background with color        |
|    |   and CircleShape            |
|    V                              |
|  Make Box clickable               |
|    |                              |
|    V                              |
|  Set size of Box to 50.dp         |
|    |                              |
|    V                              |
| End                               |
+-----------------------------------+
```

## BoardCell.kt
*  BoardCell is a Composable function that creates a UI element for a single cell on the Connect Four board.
*  It takes two parameters: color, specifying the color of the cell and onClick, specifying what should happen when the cell is clicked.
*  The Box Composable lays out its children on top of each other.
*  Modifier adds padding, sets the background color and shape, makes it clickable, and sets a size for the cell.

```
+-----------------------------------+
|          ConnectFourBoard         |
+-----------------------------------+
| Start                             |
|    |                              |
|    V                              |
|  Initialize LazyVerticalGrid      |
|    |                              |
|    V                              |
|  For each cell in the grid        |
|    |                              |
|    V                              |
|  Create BoardCell with color      |
|    |   and onClick action         |
|    V                              |
|  Check if winner is not null      |
|    |                              |
|    +---- Yes ----> Show AlertDialog |
|    |                              |
|    +---- No -----> End            |
+-----------------------------------+
```
## ConnectFourBoard.kt
* The ConnectFourBoard function creates the UI for the Connect Four board.
* The function takes in the following parameters:
* boardState represents the current state of the game, with a 2D array where 0 is an empty cell, 1 is a cell filled by player 1, and 2 is filled by player 2.
* onCellClick is a function that is called when a cell is clicked, passing the row and column as parameters.
* onRestart is a function that restarts the game.
* winner is used to display a dialog when the game is over.
* The LazyVerticalGrid creates a grid layout. items(42) creates 42 items (7 columns x 6 rows for Connect Four). Inside, it calculates the row and column from the index and uses BoardCell to display each cell.
* The AlertDialog shows up when there is a winner, announcing the winner and providing a "Restart" button.