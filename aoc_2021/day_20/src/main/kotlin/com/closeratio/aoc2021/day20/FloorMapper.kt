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
            .fold(initialImage) { acc, _ ->
                val minX = acc.litPixels.minOf(Vec2i::x)
                val maxX = acc.litPixels.maxOf(Vec2i::x)
                val minY = acc.litPixels.minOf(Vec2i::y)
                val maxY = acc.litPixels.maxOf(Vec2i::y)

                IntRange(minX - 1, maxX + 1)
                    .flatMap { x -> IntRange(minY - 1, maxY + 1).map { y -> Vec2i(x, y) } }
                    .mapNotNull { pixel ->
                        val lit = getPixelList(pixel)
                            .map { it in acc.litPixels }
                            .let(imageEnhancementAlgorithm::runAlgorithm)

                        if (lit) {
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