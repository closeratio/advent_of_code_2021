package com.closeratio.aoc2021.day25

import com.closeratio.aoc2021.common.math.Vec2i

class HorizontalCucumber(position: Vec2i): Cucumber(position, ::HorizontalCucumber) {
    override val moveAmount: Vec2i = Vec2i(1, 0)
}