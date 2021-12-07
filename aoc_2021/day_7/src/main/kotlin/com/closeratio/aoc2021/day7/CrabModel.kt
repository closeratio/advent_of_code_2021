package com.closeratio.aoc2021.day7

import kotlin.math.absoluteValue

class CrabModel(
    input: String
) {

    val crabs: List<Crab> = input
        .trim()
        .split(",")
        .map(String::toLong)
        .map(::Crab)

    fun calculateFuelForAlignmentSimple(): Long = LongRange(
        crabs.minOf { it.position },
        crabs.maxOf { it.position }
    ).minOf { candidatePosition ->
        crabs.sumOf { (it.position - candidatePosition).absoluteValue }
    }

    fun calculateFuelForAlignmentComplex(): Long = LongRange(
        crabs.minOf { it.position },
        crabs.maxOf { it.position }
    ).minOf { candidatePosition ->
        crabs.sumOf {
            val movementAmount = (it.position - candidatePosition).absoluteValue
            complexMovementCost(movementAmount)
        }
    }

    private fun complexMovementCost(amount: Long): Long = (amount * (amount + 1)) / 2

}
