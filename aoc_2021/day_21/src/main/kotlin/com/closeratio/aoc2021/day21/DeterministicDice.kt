package com.closeratio.aoc2021.day21

class DeterministicDice: Dice() {
    private var currentRollValue: Int = 1

    override fun roll(): Int {
        val rollValue = currentRollValue
        currentRollValue++
        rollCount++
        if (currentRollValue > 100) {
            currentRollValue = 1
        }
        return rollValue
    }
}