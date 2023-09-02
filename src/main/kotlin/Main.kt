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

fun main() {
    val players = listOf("X", "O")
    val boardCells: GameBoard = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8")

    var currentPlayer = getPlayer(players)

}