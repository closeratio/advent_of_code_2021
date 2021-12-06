package com.closeratio.aoc2021.day6

data class TimerValue(
    val value: Int
) {

    fun decrement(): TimerValue = TimerValue(value - 1)

}
