package com.closeratio.aoc2021.day24.alu

abstract class ArithmeticLogicUnit {

    abstract fun isModelNumberValid(modelNumber: Long): Pair<Boolean, Long>

}

