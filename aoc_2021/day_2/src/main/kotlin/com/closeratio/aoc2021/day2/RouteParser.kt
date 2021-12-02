package com.closeratio.aoc2021.day2

class RouteParser {

    fun routeSubmarine(
        submarine: Submarine,
        inputData: String
    ): Submarine = inputData
        .trim()
        .split("\n")
        .map(String::trim)
        .fold(
            submarine,
        ) { sub, instruction ->
            val (direction, amountString) = instruction.split(" ")
            val amount = amountString.toInt()

            when (direction) {
                "forward" -> sub.forward(amount)
                "up" -> sub.up(amount)
                "down" -> sub.down(amount)
                else -> throw IllegalArgumentException("Unhandled instruction: $instruction")
            }
        }

}
