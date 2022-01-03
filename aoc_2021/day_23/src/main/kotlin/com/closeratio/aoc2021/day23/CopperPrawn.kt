package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

class CopperPrawn(position: Vec2i, spentEnergy: Long): Prawn(position, spentEnergy, ::CopperPrawn) {

    override fun moveUnitCost(): Long = 100

    override val primaryDestination: Vec2i = Vec2i(6, 2)
    override val secondaryDestination: Vec2i = Vec2i(6, 1)

}