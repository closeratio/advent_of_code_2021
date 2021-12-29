package com.closeratio.aoc2021.day20

import com.closeratio.aoc2021.common.math.Vec2i

class FloorMapper(
    val initialImage: Image,
    val imageEnhancementAlgorithm: ImageEnhancementAlgorithm
) {

    data class Results(
        val litPixels: Set<Vec2i>
    )

    companion object {
        fun parse(input: String): FloorMapper = input
            .trim()
            .split("\n\n")
            .map(String::trim)
            .let { (algorithmString, imageString) ->
                FloorMapper(
                    Image.parse(imageString),
                    ImageEnhancementAlgorithm(algorithmString)
                )
            }
    }

    fun applyAlgorithm(count: Int): Results {
        val endImage = IntRange(1, count)
            .fold(initialImage) { acc, index ->
                acc.litPixels
                    .flatMap(::getPixelList)
                    .toSet()
                    .mapNotNull { pixel ->
                        val boolList = getPixelList(pixel).map { it in acc.litPixels }
                        if (imageEnhancementAlgorithm.runAlgorithm(boolList)) {
                            pixel
                        } else {
                            null
                        }
                    }
                    .toSet()
                    .let(::Image)
            }

        println(endImage.print())

        return Results(endImage.litPixels)
    }

    private fun getPixelList(pixel: Vec2i): Set<Vec2i> = setOf(
        pixel.left().up(), pixel.up(), pixel.right().up(),
        pixel.left(), pixel, pixel.right(),
        pixel.left().down(), pixel.down(), pixel.right().down()
    )

}