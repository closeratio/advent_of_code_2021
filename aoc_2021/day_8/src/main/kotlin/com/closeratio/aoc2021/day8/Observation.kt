package com.closeratio.aoc2021.day8

data class Observation(
    val patterns: List<SignalPattern>,
    val outputValues: List<SignalPattern>
) {

    companion object {
        fun parse(input: String): List<Observation> = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map { line ->
                val (left, right) = line.split("|")
                left.trim() to right.trim()
            }
            .map { (left, right) ->
                Observation(
                    left.split(" ").map { SignalPattern.parse(it) },
                    right.split(" ").map { SignalPattern.parse(it) }
                )
            }
    }

    fun countOutputEasyNumbers(): Int = outputValues
        .filter { it.determineDigit() != null }
        .size

}
