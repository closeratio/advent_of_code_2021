package com.closeratio.aoc2021.day24.alu

class HardcodedArithmeticLogicUnit : ArithmeticLogicUnit() {

    override fun isModelNumberValid(modelNumber: Long): Pair<Boolean, Long> {
        var w = 0L
        var x = 0L
        var y = 0L
        var z = 0L

        modelNumber.toString()
            .map { it.toString().toLong() }
            .forEachIndexed { index, inputValue ->
                // inp w
                w = inputValue

                // mul x 0
                // add x z
                x = z

                // mod x 26
                x %= 26

                // div z 1
                z /= when(index) {
                    5, 7, 8, 10, 11, 12, 13 -> 26
                    else -> 1
                }

                // add x 15
                x += when (index) {
                    0 -> 15
                    1, 3, 6 -> 10
                    2 -> 12
                    4 -> 14
                    5 -> -11
                    7 -> -16
                    8, 13 -> -9
                    9 -> 11
                    10, 11 -> -8
                    12 -> -10
                    else -> throw IllegalArgumentException("Bad index: $index")
                }

                // eql x w
                x = if (x == w) 1 else 0

                // eql x 0
                x = if (x == 0L) 1 else 0

                // mul y 0
                // add y 25
                // mul y x
                // add y 1
                y = (25 * x) + 1

                // mul z y
                z *= y

                // mul y 0
                // add y w
                y = w

                // add y 13
                y += when (index) {
                    0 -> 13
                    1 -> 16
                    2, 7, 8 -> 2
                    3 -> 8
                    4 -> 11
                    5 -> 6
                    6 -> 12
                    9 -> 15
                    10 -> 1
                    11, 13 -> 10
                    12 -> 14
                    else -> throw IllegalArgumentException("Bad index: $index")
                }

                // mul y x
                y *= x

                // add z y
                z += y
            }

        return (z == 0L) to z
    }
}