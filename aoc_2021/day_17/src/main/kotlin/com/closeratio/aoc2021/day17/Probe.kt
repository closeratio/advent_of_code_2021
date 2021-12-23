package com.closeratio.aoc2021.day17

import com.closeratio.aoc2021.common.math.Vec2i

data class Probe(
    val position: Vec2i,
    val velocity: Vec2i
) {

    fun step(): Probe = Probe(
        position + velocity,
        Vec2i(
            when {
                velocity.x < 0 -> velocity.x + 1
                velocity.x > 0 -> velocity.x - 1
                else -> 0
            },
            velocity.y - 1
        )
    )

}
