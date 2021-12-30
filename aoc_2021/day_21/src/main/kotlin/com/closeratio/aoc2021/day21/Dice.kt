package com.closeratio.aoc2021.day21

abstract class Dice {

    var rollCount: Int = 0
        protected set

    abstract fun roll(): Int
}

