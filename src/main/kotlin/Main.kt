typealias GameBoard = MutableList<String>
typealias Players = List<String>

fun printBoard(cells: GameBoard) {
    val board = String.format(
        """
         %s | %s | %s 
        ---+---+---
         %s | %s | %s 
        ---+---+---
         %s | %s | %s 
        """.trimIndent(), *Array(9) { cells[it] }
    )
    println(board)
}


fun clearConsole() {
    Array(30) { print("\n") }
}


fun getPlayer(players: Players): String {
    val possiblePlayers = players.joinToString(" or ")
    println("Who will start playing $possiblePlayers?")

    while (true) {
        print("=> ")
        val input = readln().uppercase()

        if (input in players) {
            return input
        }

        println("Invalid player, try again")
    }
}


fun getPlayerMove(player: String, players: Players, boardCells: GameBoard): Int {
    println("Player $player, select a board cell")

    while (true) {
        print("=> ")
        val input = readln().toIntOrNull()

        if (input == null || input !in 0..8) {
            println("Invalid input, try again!")
            continue
        }

        if (boardCells[input] in players) {
            println("Cell already taken, player $player, try again!")
            continue
        }

        return input
    }
}


fun main() {
    val players = listOf("X", "O")
    val boardCells: GameBoard = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8")

    var currentPlayer = getPlayer(players)

    while (true) {
        clearConsole()
        printBoard(boardCells)

        val playerMove = getPlayerMove(currentPlayer, players, boardCells)
        boardCells[playerMove] = currentPlayer

        currentPlayer = if (currentPlayer == players[0]) players[1] else players[0]
    }

}