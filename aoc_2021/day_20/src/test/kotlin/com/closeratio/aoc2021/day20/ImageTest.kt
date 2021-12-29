package com.closeratio.aoc2021.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class ImageTest {

    @Test
    fun parse() {
        val image = Image.parse(javaClass.getResource("/test_input.txt")!!.readText()
            .trim()
            .split("\n\n")[1])

        assertThat(image.litPixels, hasSize(10))
    }

}