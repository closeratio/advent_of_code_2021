package com.closeratio.aoc2021.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test

class SignalPatternTest {

    @Test
    fun oneSignal() {
        val result = SignalPattern.parse("cg").determineSimpleDigit()
        assertThat(result, `is`(Digit(1)))
    }

    @Test
    fun fourSignal() {
        val result = SignalPattern.parse("gecf").determineSimpleDigit()
        assertThat(result, `is`(Digit(4)))
    }

    @Test
    fun sevenSignal() {
        val result = SignalPattern.parse("cbg").determineSimpleDigit()
        assertThat(result, `is`(Digit(7)))
    }

    @Test
    fun eightSignal() {
        val result = SignalPattern.parse("egdcabf").determineSimpleDigit()
        assertThat(result, `is`(Digit(8)))
    }

    @Test
    fun unknownSignal() {
        val result = SignalPattern.parse("cbdgef").determineSimpleDigit()
        assertThat(result, `is`(nullValue()))
    }

}
