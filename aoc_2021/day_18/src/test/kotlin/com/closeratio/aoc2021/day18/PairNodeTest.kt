package com.closeratio.aoc2021.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class PairNodeTest {

    @Test
    fun parseSimple() {
        val result = PairNode.parse("[[[[[9,8],1],2],3],4]")

        assertThat(result.toString(), `is`("[[[[[9,8],1],2],3],4]"))
        assertThat(
            result,
            `is`(
                PairNode(
                    0,
                    PairNode(
                        1,
                        PairNode(
                            2,
                            PairNode(
                                3,
                                PairNode(
                                    4,
                                    LiteralNode(5, 9),
                                    LiteralNode(5, 8)
                                ),
                                LiteralNode(4, 1)
                            ),
                            LiteralNode(3, 2)
                        ),
                        LiteralNode(2, 3)
                    ),
                    LiteralNode(1, 4)
                )
            )
        )
    }

    @Test
    fun parseComplex() {
        val result = PairNode.parse("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]")
        assertThat(result.toString(), `is`("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]"))
    }

    @Test
    fun explodeCandidates() {
        val result = PairNode.parse("[[[[[9,8],1],2],3],4]").explodeCandidates()

        assertThat(result, hasSize(1))
        assertThat(result, contains(PairNode(4, LiteralNode(5, 9), LiteralNode(5, 8))))
    }

    @Test
    fun splitCandidates() {
        val result =  PairNode(
            0,
            PairNode(
                1,
                PairNode(
                    2,
                    PairNode(
                        3,
                        PairNode(
                            4,
                            LiteralNode(5, 10),
                            LiteralNode(5, 11)
                        ),
                        LiteralNode(4, 1)
                    ),
                    LiteralNode(3, 2)
                ),
                LiteralNode(2, 3)
            ),
            LiteralNode(1, 4)
        ).splitCandidates()


        assertThat(result, hasSize(2))
        assertThat(result, contains(LiteralNode(5, 10), LiteralNode(5, 11)))
    }

}