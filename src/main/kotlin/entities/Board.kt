package entities

import utils.colorize


/*
* This class represents the tic-tac-toe board. It contains the cells range, the cells values and the methods to print
* the board, reset the board and update a cell value.
* */
class Board {
    var cellsRange: IntRange = 0..8 // Define the cells range
        private set // Prevent the cells range from being updated outside the class

    private val defaultCellsValues: MutableList<String> =
        cellsRange.map { it.toString() }.toMutableList() // Define the default cells values

    var cells: MutableList<String> =
        defaultCellsValues.toMutableList() // Define the cells making a copy of the default cells values
        private set // Prevent the cells from being updated outside the class

    /*
    * This function receives a cell value and returns the cell value formatted with color.
    * It will colorize the X player as cyan and the O player as red.
    * It will return the default cell value if the cell value is not X or O.
    * */
    private fun formatCell(cell: String): String { // Apply color to the cell value based on the player
        return when (cell) {
            "X" -> colorize(cell, Color.CYAN) // Colorize X player as cyan
            "O" -> colorize(cell, Color.RED) // Colorize O player as red
            else -> cell // Return the default cell value
        }
    }

    /*
    * This function receives a list of strings and prints them in a tic-tac-toe board format. It uses the colorize
    * function to color the X and O players and the board lines.
    * */
    fun print() {
        val formattedCells = cells.map { formatCell(it) }
        val board = String.format( // Format the cells into a tic-tac-toe board
            """
         %s ${colorize("|", Color.YELLOW)} %s ${colorize("|", Color.YELLOW)} %s 
        ${colorize("---+---+---", Color.YELLOW)}
         %s ${colorize("|", Color.YELLOW)} %s ${colorize("|", Color.YELLOW)} %s 
        ${colorize("---+---+---", Color.YELLOW)}
         %s ${colorize("|", Color.YELLOW)} %s ${colorize("|", Color.YELLOW)} %s 
        """, *formattedCells.toTypedArray() // Spread the formatted cells into the format function
        ).trimIndent() // Trim the indentation of the board
        println(board) // Print the board

    }

    /*
    * This function resets the board to the default values.
    * */
    fun reset() {
        cells = defaultCellsValues.toMutableList() // Reset the cells to the default values
    }

    /*
    * This function updates a cell value on the board.
    * */
    fun updateCell(cell: Int, value: String) {
        cells[cell] = value // Update the cell value
    }

    fun validateWinner(): Boolean {
        val winningCombinations = listOf(
            // Define the winning combinations
            listOf(0, 1, 2), // Horizontal
            listOf(3, 4, 5), // Horizontal
            listOf(6, 7, 8), // Horizontal
            listOf(0, 3, 6), // Vertical
            listOf(1, 4, 7), // Vertical
            listOf(2, 5, 8), // Vertical
            listOf(0, 4, 8), // Diagonal
            listOf(2, 4, 6), // Diagonal
        )

        return winningCombinations.any { // Check if any of the winning combinations is true
                (a, b, c) ->
            cells[a] == cells[b] && cells[b] == cells[c]
        }
    }
}