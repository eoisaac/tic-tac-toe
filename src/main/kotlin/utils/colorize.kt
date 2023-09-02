package utils

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

/*
* This function receives a string value and a color value from the Color enum class and returns a string with the ANSI
* color code. It resets the color to the default color after the text.
* If no color is provided, the default color is white.
* */

fun colorize(text: String, color: Color = Color.WHITE): String {
    return "${color.code}$text${Color.RESET.code}"
}
