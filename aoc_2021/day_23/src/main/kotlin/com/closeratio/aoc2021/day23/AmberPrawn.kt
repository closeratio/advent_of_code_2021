package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

class AmberPrawn(position: Vec2i, spentEnergy: Long): Prawn(position, spentEnergy, ::AmberPrawn) {

    override fun moveUnitCost(): Long = 1

    override val primaryDestination: Vec2i = Vec2i(2, 2)
    override val secondaryDestination: Vec2i = Vec2i(2, 1)

}

