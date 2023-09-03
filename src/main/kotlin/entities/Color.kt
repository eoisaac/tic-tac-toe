package entities

/*
* This enumerator class contains a mapping of ANSI color codes to the color names.
* */
enum class Color(val code: String) {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    RESET("\u001B[0m"),
}