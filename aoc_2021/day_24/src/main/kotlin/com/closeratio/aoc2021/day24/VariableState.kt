package com.closeratio.aoc2021.day24

data class VariableState(
    private val stateMap: Map<Char, Long>
) {

    fun setVariable(name: Char, value: Long): VariableState = VariableState(
        stateMap + (name to value)
    )

    fun getVariable(name: Char): Long = stateMap.getOrDefault(name, 0L)

}
