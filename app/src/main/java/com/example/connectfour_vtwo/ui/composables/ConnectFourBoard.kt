package com.example.connectfour_vtwo.ui.composables


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConnectFourBoard(
    boardState: Array<IntArray>,
    onCellClick: (Int, Int) -> Unit,
    onRestart: () -> Unit,
    winner: Int?
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .padding(top = 240.dp),
    ) {
        items(42) { index ->
            val row = index / 7
            val col = index % 7
            BoardCell(
                color = when (boardState[row][col]) {
                    1 -> Color(0xFFFF5C42)
                    2 -> Color(0xFFFFCD5C)
                    else -> Color.White
                },
                onClick = { onCellClick(row, col) }
            )
        }
    }

    if (winner != null) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Game Over") },
            text = { Text("Player ${if (winner == 1) "Red" else "Yellow"} wins!") },
            confirmButton = {
                Button(onClick = { onRestart() }) {
                    Text("Restart")
                }
            }
        )
    }
}
