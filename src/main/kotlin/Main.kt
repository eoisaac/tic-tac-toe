
typealias TicTacToeBoard = List<List<String>>

fun printTicTacToeBoard(board: TicTacToeBoard) {
    println(" A | B | C ")
    println("---+---+---")
    println(" D | E | F ")
    println("---+---+---")
    println(" G | H | I ")

    board.map { row ->  {
        row.
    } }
}


fun main() {

    val board: TicTacToeBoard = listOf(
        listOf("A", "B", "C"),
        listOf("D", "E", "F"),
        listOf("G", "H", "I")
    )

    printTicTacToeBoard(board)


}