package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

class DesertPrawn(position: Vec2i, spentEnergy: Long): Prawn(position, spentEnergy, ::DesertPrawn) {

    override fun moveUnitCost(): Long = 1000

    override val primaryDestination: Vec2i = Vec2i(8, 2)
    override val secondaryDestination: Vec2i = Vec2i(8, 1)

}