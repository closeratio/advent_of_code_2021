package com.closeratio.aoc2021.day22

import com.closeratio.aoc2021.day22.Instruction.Type.TURN_OFF
import com.closeratio.aoc2021.day22.Instruction.Type.TURN_ON

data class Instruction(
    val cuboid: Cuboid,
    val type: Type
 ) {

    companion object {
        fun parse(line: String): Instruction = line
            .trim()
            .split(" ")
            .let { (typeString, cubeString) ->
                val type: Type = when (typeString) {
                    "on" -> TURN_ON
                    "off" -> TURN_OFF
                    else -> throw IllegalArgumentException("Bad instruction type: $typeString")
                }

                val cuboid: Cuboid = Cuboid.parse(cubeString)

                Instruction(cuboid, type)
            }
    }

    enum class Type {
        TURN_ON,
        TURN_OFF
    }

}