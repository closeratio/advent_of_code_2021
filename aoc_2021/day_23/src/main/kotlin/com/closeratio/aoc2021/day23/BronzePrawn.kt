package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

class BronzePrawn(position: Vec2i, spentEnergy: Long): Prawn(position, spentEnergy, ::BronzePrawn) {

    override fun moveUnitCost(): Long = 10

    override val destinationColumnX: Int = 4

}
