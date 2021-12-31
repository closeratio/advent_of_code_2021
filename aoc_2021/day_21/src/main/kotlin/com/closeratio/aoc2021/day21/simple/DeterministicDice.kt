package com.closeratio.aoc2021.day21.simple

class DeterministicDice {

    var rollCount: Int = 0
        private set

    private var currentRollValue: Int = 1

    fun roll(): Int {
        val rollValue = currentRollValue
        currentRollValue++
        rollCount++
        if (currentRollValue > 100) {
            currentRollValue = 1
        }
        return rollValue
    }
}

