import utils.Color
import utils.colorize

typealias GameBoard = MutableList<String> // Define a type alias for the game board
typealias Players = List<String> // Define a type alias for the players


/*
* This function receives a list of strings and prints them in a tic-tac-toe board format. It uses the colorize function
* to color the X and O players and the board lines.
* */
fun printBoard(cells: GameBoard) {
    val formattedCells = cells.map { cell -> // Apply color to the cell value based on the player
        when (cell) {
            "X" -> colorize(cell, Color.CYAN) // Colorize X player as cyan
            "O" -> colorize(cell, Color.RED) // Colorize O player as red
            else -> cell // Return the default cell value
        }
    }

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
* This function clears the console by printing 20 new lines. I used that because I couldn't find a way to clear the
* console natively in Kotlin.
* */
fun clearConsole() {
    Array(11) { println("\n") } // Print 22 new lines
}


/*
* This function receives a list of players and asks the user to select one of them. It returns the selected player.
* It will validate the input and ask the user to try again if the input is a not a valid player (X or O).
* */
fun getPlayer(players: Players): String {
    val possiblePlayers = players.joinToString(" or ") // Join the players list into a string with " or " between them
    println(colorize("Who will start playing $possiblePlayers?", Color.YELLOW)) // Ask the user to select a player

    while (true) { // Loop until the user selects a valid player
        print("=> ") // Print the input prompt
        val input = readln().uppercase() // Read the user input and convert it to uppercase

        if (input in players) { // Check if the input is a valid player
            return input // Return the selected player
        }

        println(colorize("Invalid player, try again!", Color.RED)) // Ask the user to try again
    }
}

/*
* This function receives a player and asks the user to select a cell on the board. It returns the selected cell.
* It will validate the input and ask the user to try again if the input is not a valid cell (0 to 8) or if the cell
* is already taken.
* */
fun getPlayerMove(player: String, players: Players, boardCells: GameBoard): Int {
    println(colorize("Player $player, select a board cell", Color.YELLOW)) // Ask the user to select a cell

    while (true) { // Loop until the user selects a valid cell
        print("=> ") // Print the input prompt
        val input = readln().toIntOrNull() // Read the user input and convert it to integer or null

        if (input == null || input !in 0..8) { // Check if the input is a valid cell, between 0 and 8 and not null
            println(colorize("Invalid cell, try again!", Color.RED)) // Ask the user to try again
            continue // Skip the rest of the loop and start again
        }

        if (boardCells[input] in players) { // Check if the cell is already taken
            println(colorize("Cell already taken, player $player, try again!", Color.RED)) // Ask the user to try again
            continue // Skip the rest of the loop and start again
        }

        return input // Return the selected cell
    }
}

/*
* This function receives a list of strings and checks if there is a winner. It returns true if there is a winner and
* false if there is no winner. It uses the validateWinner function to check if there is a winner.
* */
fun validateWinner(boardCells: GameBoard): Boolean {
    val winningCombinations = listOf( // Define the winning combinations
        listOf(0, 1, 2), // Horizontal
        listOf(3, 4, 5), // Horizontal
        listOf(6, 7, 8), // Horizontal
        listOf(0, 3, 6), // Vertical
        listOf(1, 4, 7), // Vertical
        listOf(2, 5, 8), // Vertical
        listOf(0, 4, 8), // Diagonal
        listOf(2, 4, 6), // Diagonal
    )

    for (combination in winningCombinations) { // Loop through the winning combinations
        val (a, b, c) = combination // Destructure the combination into a, b and c
        if (boardCells[a] == boardCells[b] && boardCells[b] == boardCells[c]) { // Check if the combination is a winner
            return true // Return true if the combination is a winner
        }
    }

    return false // Return false if no combination is a winner
}


fun main() {
    val players = listOf("X", "O") // Define the players, X and O
    val boardCells: GameBoard = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8") // Define the board cells

    var currentPlayer = getPlayer(players) // Get the player that will start the game

    while (true) { // Loop until the game is over
        clearConsole() // Clear the console each time the board is printed
        printBoard(boardCells) // Print the game board with the current cells values

        val playerMove = getPlayerMove(currentPlayer, players, boardCells) // Get the player move
        boardCells[playerMove] = currentPlayer // Update the board cells with the player move
        currentPlayer = if (currentPlayer == players[0]) players[1] else players[0] // Switch the current player

        val hasWinner = validateWinner(boardCells) // Check if there is a winner
        if (hasWinner) { // Check if there is a winner
            clearConsole() // Clear the console
            printBoard(boardCells) // Print the game board with the current cells values
            println(colorize("Player ${boardCells[playerMove]} won the game!", Color.GREEN)) // Print the winner
            break // Break the loop
        }
    }

}