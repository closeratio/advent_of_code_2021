package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

data class Path(
    val path: List<Vec2i>,
) {
    val start = path.first()
    val finish = path.last()
}
