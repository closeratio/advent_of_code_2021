package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.junit.jupiter.api.Test

class BurrowTest {

    private val burrow = Burrow.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        val amberPrawnPositions = burrow.prawns.filterIsInstance<AmberPrawn>().map(AmberPrawn::position)
        assertThat(amberPrawnPositions, hasSize(2))
        assertThat(amberPrawnPositions, containsInAnyOrder(Vec2i(2, 2), Vec2i(8, 2)))

        val bronzePrawnPositions = burrow.prawns.filterIsInstance<BronzePrawn>().map(BronzePrawn::position)
        assertThat(bronzePrawnPositions, hasSize(2))
        assertThat(bronzePrawnPositions, containsInAnyOrder(Vec2i(2, 1), Vec2i(6, 1)))

        val copperPrawnPositions = burrow.prawns.filterIsInstance<CopperPrawn>().map(CopperPrawn::position)
        assertThat(copperPrawnPositions, hasSize(2))
        assertThat(copperPrawnPositions, containsInAnyOrder(Vec2i(4, 1), Vec2i(6, 2)))

        val desertPrawnPositions = burrow.prawns.filterIsInstance<DesertPrawn>().map(DesertPrawn::position)
        assertThat(desertPrawnPositions, hasSize(2))
        assertThat(desertPrawnPositions, containsInAnyOrder(Vec2i(4, 2), Vec2i(8, 1)))
    }

    @Test
    fun computeOrganizeEnergy() {
        val result = burrow.computeOrganizeEnergy()
        assertThat(result, `is`(12521))
    }

}