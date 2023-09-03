package entities

import utils.colorize

/*
* This class contains the game logic. It contains the players, the board and the methods to start the game, reset the
* game and ask the user to play again.
* */
class Game {
    private val players: List<Player> = listOf(
        Player("X", Color.CYAN),
        Player("O", Color.RED)
    ) // Define the players
    private val playersNames: List<String> = players.map { player -> player.name } // Define the players names
    private lateinit var currentPlayer: Player // Define the current player, it will be set later

    private val board: Board = Board() // Inject the board

    private var totalMoves: Int = 0 // Define the total game moves

    /*
    * This method contains the game loop. It will print the board, ask the user to select a cell, update the cell value
    * and check if there is a winner or a tie.
    * */
    private fun run() {
        while (true) {
            clearConsole() // Clear the console
            board.print() // Print the board

            val playerMove = currentPlayer.move(board.cellsRange, board.cells, playersNames) // Ask the user to select a cell
            totalMoves += 1 // Increment the total moves

            board.updateCell(playerMove, currentPlayer.name) // Update the cell value
            currentPlayer = if (currentPlayer.name == playersNames[0]) players[1] else players[0] // Change the current player

            if (totalMoves >= 5) { // Check if there is a winner or a tie, is only possible after 5 moves
                val hasWinner = board.validateWinner() // Validate if there is a winner
                if (hasWinner) { // Check if there is a winner
                    clearConsole() // Clear the console
                    board.print() // Print the board
                    println(colorize("Player ${board.cells[playerMove]} won the game!", Color.GREEN)) // Print the winner
                    break // Break the game loop
                }
            }

            if (totalMoves == 9) { // Check if there is a tie, is only possible after 9 moves
                println(colorize("It's a tie!", Color.GREEN)) // Print the tie
                break // Break the game loop
            }
        }

        val playAgain = playAgain() // Ask the user to play again
        if (playAgain) { // Check if the user wants to play again
            reset() // Reset the game
        } else { // The user doesn't want to play again
            println(colorize("Bye!", Color.YELLOW))
        }
    }

    /*
    * This method starts the game. It will print the welcome message, the players names and ask the user to select a
    * player.
    * */
    fun start() {
        println(colorize("Welcome to Tic-Tac-Toe!", Color.GREEN)) // Print the welcome message
        println(
            colorize(
                "The players are ${playersNames.joinToString(" and ")}",
                Color.YELLOW
            )
        ) // Print the players names
        println(colorize("====================================", Color.YELLOW))

        currentPlayer = getPlayer() // Ask the user to select a player
        run() // Start the game
    }

    /*
    * This method resets the game. It will reset the board, the total moves and ask the user to select a player.
    * */
    private fun reset() {
        board.reset() // Reset the board
        totalMoves = 0 // Reset the total moves
        currentPlayer = getPlayer() // Ask the user to select a player
        run() // Start the game
    }

    /*
    * This method asks the user to select a player. It will validate the input and ask the user to try again if the
    * input is not a valid player. It will return the selected player.
    * */
    private fun getPlayer(): Player {
        val possiblePlayers =
            players.joinToString(" or ") { it.name } // Join the players list into a string with " or " between them
        println(colorize("Who will start playing $possiblePlayers?", Color.YELLOW)) // Ask the user to select a player

        while (true) { // Loop until the user selects a valid player
            print("=> ") // Print the input prompt
            val input = readln().uppercase() // Read the user input and convert it to uppercase

            val selectedPlayer = players.find { it.name == input } // Find the player with the selected name
            if (selectedPlayer != null) { // Check if the input is a valid player
                return selectedPlayer // Return the selected player
            }

            println(colorize("Invalid player, try again!", Color.RED)) // Ask the user to try again
        }
    }

    /*
    * This method asks the user to play again. It will validate the input and ask the user to try again if the input is
    * not a valid option. It will return true if the user wants to play again or false if the user doesn't want to play
    * again.
    * */
    private fun playAgain(): Boolean {
        println(colorize("====================================", Color.YELLOW))
        println(colorize("Do you want to play again? (Y/N)", Color.YELLOW))

        while (true) { // Loop until the user selects a valid option
            print("=> ")
            val input = readln().uppercase() // Read the user input and convert it to uppercase
            when (input) { // Check the user input
                "Y" -> return true // The user wants to play again
                "N" -> return false // The user doesn't want to play again
                else -> println(colorize("Invalid option, try again!", Color.RED)) // Ask the user to try again
            }
        }
    }

    /*
    * This method clears the console. I use that because I didn't find a way to clear the console in Kotlin.
    * */
    private fun clearConsole() {
        Array(11) { println("\n") } // Print 22 new lines
    }
}