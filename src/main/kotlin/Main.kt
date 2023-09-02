typealias GameBoard = MutableList<String>

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
    print(board)
}





fun main() {
    val boardCells: GameBoard = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8")
    printBoard(boardCells)
}