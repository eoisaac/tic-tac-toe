package entities

import utils.colorize

/*
* This class contains the game logic. It contains the players, the board and the methods to start the game, reset the
* game and ask the user to play again.
* */
class Player(var name: String, var color: Color) {
    /*
    * This method asks the user to select a cell to play. It will validate the input and ask the user to try again if
    * the input is not a valid cell (0 to 8) or if the cell is already taken.
    * */
    fun move(cellsRange: IntRange, boardCells: MutableList<String>, gamePlayers: List<String>): Int {
        println(
            colorize(
                "Player ${colorize(name, color)}, ${colorize("select a board cell", Color.YELLOW)}",
                Color.YELLOW
            )
        ) // Ask the user to select a cell

        while (true) { // Loop until the user selects a valid cell
            print("=> ") // Print the input prompt
            val input = readln().toIntOrNull() // Read the user input and convert it to integer or null

            if (input == null || input !in cellsRange) { // Check if the input is a valid cell, between 0 and 8 and not null
                println(colorize("Invalid cell, try again!", Color.RED)) // Ask the user to try again
                continue // Skip the rest of the loop and start again
            }

            if (boardCells[input] in gamePlayers) { // Check if the cell is already taken
                println(
                    colorize(
                        "Cell already taken, player ${boardCells[input]}, try again!",
                        Color.RED
                    )
                ) // Ask the user to try again
                continue // Skip the rest of the loop and start again
            }
            return input // Return the selected cell
        }
    }
}