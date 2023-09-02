typealias TicTacToeBoard = List<List<String>>

fun printBoard(board: TicTacToeBoard) {
    board.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, column ->
            print(if (columnIndex % 2 != 0)  "| $column |" else " $column ")
        }
        if (rowIndex < board.size - 1) println("\n---+---+---")
    }
}

fun welcome() {
    println("Welcome to tic tac toe!")

}



fun main() {
    val board: TicTacToeBoard = listOf(
        listOf("A", "B", "C"),
        listOf("D", "E", "F"),
        listOf("G", "H", "I"),
    )

    printBoard(board)
}