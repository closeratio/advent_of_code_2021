package com.closeratio.aoc2021.day25

class FloorStateSimulation(
    val initialState: FloorState
) {

    companion object {
        fun parse(input: String): FloorStateSimulation = FloorStateSimulation(FloorState.parse(input))
    }

    fun iterationsUntilStable(): Int {
        val states = arrayListOf(initialState, initialState.iterate())

        while (states.last() != states[states.size - 2]) {
            states.add(states.last().iterate())
        }

        return states.size - 1
    }

}