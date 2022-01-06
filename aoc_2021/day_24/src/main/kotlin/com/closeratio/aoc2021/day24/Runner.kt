package com.closeratio.aoc2021.day24

import com.closeratio.aoc2021.day24.alu.InstructionBasedArithmeticLogicUnit

object Runner {

    private val alu = InstructionBasedArithmeticLogicUnit.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = LongRange(11111111111111, 99999999999999)
            .reversed()
            .first { alu.isModelNumberValid(it).first }

        println(result)
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}