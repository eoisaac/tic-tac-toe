package utils

import entities.Color


/*
* This function receives a string value and a color value from the Color enum class and returns a string with the ANSI
* color code. It resets the color to the default color after the text.
* If no color is provided, the default color is white.
* */
fun colorize(text: String, color: Color = Color.WHITE): String {
    return "${color.code}$text${Color.RESET.code}"
}
