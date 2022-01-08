package com.closeratio.aoc2021.day25

import com.closeratio.aoc2021.common.math.Vec2i

class VerticalCucumber(position: Vec2i): Cucumber(position, ::VerticalCucumber) {
    override val moveAmount: Vec2i = Vec2i(0, 1)
}