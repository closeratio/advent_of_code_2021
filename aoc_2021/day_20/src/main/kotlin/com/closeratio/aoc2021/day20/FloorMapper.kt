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
        val (endImage, _) = IntRange(1, count)
            .fold(initialImage to false) { (image, default), iteration ->
                val minX = image.pixels.keys.minOf(Vec2i::x)
                val maxX = image.pixels.keys.maxOf(Vec2i::x)
                val minY = image.pixels.keys.minOf(Vec2i::y)
                val maxY = image.pixels.keys.maxOf(Vec2i::y)

                val nextImage = IntRange(minX - 1, maxX + 1)
                    .flatMap { x -> IntRange(minY - 1, maxY + 1).map { y -> Vec2i(x, y) } }
                    .associateWith { pixel ->
                        getPixelList(pixel)
                            .map {
                                image.pixels.getOrElse(it) {
                                    when ((iteration % 2) == 0) {
                                        true -> imageEnhancementAlgorithm.firstIndexLit()
                                        false -> imageEnhancementAlgorithm.lastIndexLit()
                                    }
                                }
                            }
                            .let(imageEnhancementAlgorithm::runAlgorithm)
                    }
                    .let(::Image)

                val nextDefault = when {
                    default -> imageEnhancementAlgorithm.firstIndexLit()
                    else -> imageEnhancementAlgorithm.lastIndexLit()
                }

                nextImage to nextDefault
            }

        return Results(endImage.litPixels())
    }

    private fun getPixelList(pixel: Vec2i): Set<Vec2i> = setOf(
        pixel.left().up(), pixel.up(), pixel.right().up(),
        pixel.left(), pixel, pixel.right(),
        pixel.left().down(), pixel.down(), pixel.right().down()
    )

}