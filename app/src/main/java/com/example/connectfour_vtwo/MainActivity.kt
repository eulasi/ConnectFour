package com.example.connectfour_vtwo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.connectfour_vtwo.ui.composables.ConnectFourBoard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val board = remember { mutableStateOf(Array(6) { IntArray(7) }) }
            val currentPlayer = remember { mutableStateOf(1) }
            val winner = remember { mutableStateOf<Int?>(null) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1B66DC)) // Set the background color to blue
            ) {

                ConnectFourBoard(
                    boardState = board.value,
                    onCellClick = { row, col ->
                        if (winner.value == null) {
                            // Find the lowest empty row in the clicked column
                            for (r in 5 downTo 0) {
                                if (board.value[r][col] == 0) {
                                    // Update the board
                                    val newBoard = board.value.map { it.clone() }.toTypedArray()
                                    newBoard[r][col] = currentPlayer.value
                                    board.value = newBoard

                                    // Check for a win
                                    if (ConnectFourGame.checkWin(newBoard)) {
                                        winner.value = currentPlayer.value
                                    }
                                    currentPlayer.value = if (currentPlayer.value == 1) 2 else 1
                                    break
                                }
                            }
                        }
                    },
                    onRestart = {
                        board.value = Array(6) { IntArray(7) }
                        currentPlayer.value = 1
                        winner.value = null
                    },
                    winner = winner.value
                )
            }
        }
    }
}
