package com.closeratio.aoc2021.day2

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class RouteParserTest {

    private val routeParser = RouteParser()

    @Test
    fun routeSimpleSubmarine() {
        val result = routeParser
            .routeSubmarine(
                SimpleSubmarine(Vec2i.ZERO),
                javaClass.getResource("/test_input.txt")!!.readText()
            )

        assertThat(result, `is`(SimpleSubmarine(Vec2i(15, 10))))
    }

    @Test
    fun routeComplexSubmarine() {
        val result = routeParser
            .routeSubmarine(
                ComplexSubmarine(Vec2i.ZERO, 0),
                javaClass.getResource("/test_input.txt")!!.readText()
            )

        assertThat(result, `is`(ComplexSubmarine(Vec2i(15, 60), 10)))
    }

}
