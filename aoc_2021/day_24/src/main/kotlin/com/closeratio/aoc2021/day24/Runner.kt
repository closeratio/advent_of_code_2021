package com.closeratio.aoc2021.day24

object Runner {

    private val solver = AluSolver.parse(javaClass.getResource("/input.txt")!!.readText())

    @JvmStatic
    fun main(args: Array<String>) {
        println(solver.solve())
    }

}