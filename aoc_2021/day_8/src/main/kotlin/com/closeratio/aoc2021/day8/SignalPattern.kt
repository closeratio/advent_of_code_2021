package com.closeratio.aoc2021.day8

data class SignalPattern(
    val signals: Set<Signal>
) {

    companion object {
        fun parse(input: String): SignalPattern = SignalPattern(
            input.map(::Signal).toSet()
        )
    }

    fun determineSimpleDigit(): Digit? = when (signals.size) {
        2 -> Digit(1)
        4 -> Digit(4)
        3 -> Digit(7)
        7 -> Digit(8)
        else -> null
    }

}

