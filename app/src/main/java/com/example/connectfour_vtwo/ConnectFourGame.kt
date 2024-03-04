package com.example.connectfour_vtwo

class ConnectFourGame {
    companion object {
        fun checkWin(board: Array<IntArray>): Boolean {
            // Horizontal Check
            for (row in 0 until 6) {
                for (col in 0 until 4) {
                    if (board[row][col] != 0 &&
                        board[row][col] == board[row][col + 1] &&
                        board[row][col] == board[row][col + 2] &&
                        board[row][col] == board[row][col + 3]
                    ) {
                        return true
                    }
                }
            }


            // Check vertical
            for (col in 0 until 7) {
                for (row in 0 until 3) {
                    if (board[row][col] != 0 &&
                        board[row][col] == board[row + 1][col] &&
                        board[row][col] == board[row + 2][col] &&
                        board[row][col] == board[row + 3][col]
                    ) {
                        return true
                    }
                }
            }

            // Check diagonal (top-left to bottom-right)
            for (row in 0 until 3) {
                for (col in 0 until 4) {
                    if (board[row][col] != 0 &&
                        board[row][col] == board[row + 1][col + 1] &&
                        board[row][col] == board[row + 2][col + 2] &&
                        board[row][col] == board[row + 3][col + 3]
                    ) {
                        return true
                    }
                }
            }

            // Check diagonal (bottom-left to top-right)
            for (row in 3 until 6) {
                for (col in 0 until 4) {
                    if (board[row][col] != 0 &&
                        board[row][col] == board[row - 1][col + 1] &&
                        board[row][col] == board[row - 2][col + 2] &&
                        board[row][col] == board[row - 3][col + 3]
                    ) {
                        return true
                    }
                }
            }
            return false
        }
    }
}
