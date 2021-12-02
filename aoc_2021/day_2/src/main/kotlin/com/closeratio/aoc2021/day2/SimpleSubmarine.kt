package com.closeratio.aoc2021.day2

import com.closeratio.aoc2021.common.math.Vec2i

class SimpleSubmarine(
    position: Vec2i
): Submarine(position) {

    override fun forward(amount: Int): SimpleSubmarine = SimpleSubmarine(position + Vec2i(amount, 0))
    override fun down(amount: Int): SimpleSubmarine = SimpleSubmarine(position + Vec2i(0, amount))
    override fun up(amount: Int): SimpleSubmarine = SimpleSubmarine(position + Vec2i(0, -amount))




}
