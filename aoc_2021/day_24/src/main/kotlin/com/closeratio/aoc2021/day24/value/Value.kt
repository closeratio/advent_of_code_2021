package com.closeratio.aoc2021.day24.value

import com.closeratio.aoc2021.day24.VariableState

abstract class Value {

    abstract fun value(variableState: VariableState): Long

}

