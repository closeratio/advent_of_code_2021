package com.closeratio.aoc2021.day16

import com.closeratio.aoc2021.day16.packet.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PacketDecoderTest {

    private val decoder = PacketDecoder()

    companion object {
        @JvmStatic
        fun hexValues(): List<Arguments> = listOf(
            Arguments.of("0", "0000"),
            Arguments.of("1", "0001"),
            Arguments.of("2", "0010"),
            Arguments.of("3", "0011"),
            Arguments.of("4", "0100"),
            Arguments.of("5", "0101"),
            Arguments.of("6", "0110"),
            Arguments.of("7", "0111"),
            Arguments.of("8", "1000"),
            Arguments.of("9", "1001"),
            Arguments.of("A", "1010"),
            Arguments.of("B", "1011"),
            Arguments.of("C", "1100"),
            Arguments.of("D", "1101"),
            Arguments.of("E", "1110"),
            Arguments.of("F", "1111")
        )

        @JvmStatic
        fun parseTransmissionArguments(): List<Arguments> = listOf(
            Arguments.of(
                "D2FE28",
                LiteralValue(6, 2021),
                6
            ),
            Arguments.of(
                "38006F45291200",
                LessThan(
                    1,
                    listOf(
                        LiteralValue(6, 10),
                        LiteralValue(2, 20)
                    )
                ),
                9
            ),
            Arguments.of(
                "8A004A801A8002F478",
                Minimum(
                    4,
                    listOf(
                        Minimum(
                            1, listOf(
                                Minimum(
                                    5, listOf(
                                        LiteralValue(6, 15)
                                    )
                                )
                            )
                        )
                    )
                ),
                16
            ),
            Arguments.of(
                "620080001611562C8802118E34",
                Sum(
                    3,
                    listOf(
                        Sum(
                            0,
                            listOf(LiteralValue(0, 10), LiteralValue(5, 11))
                        ),
                        Sum(
                            1,
                            listOf(LiteralValue(0, 12), LiteralValue(3, 13))
                        )
                    )
                ),
                12
            ),
            Arguments.of(
                "C0015000016115A2E0802F182340",
                Sum(
                    6,
                    listOf(
                        Sum(
                            0,
                            listOf(LiteralValue(0, 10), LiteralValue(6, 11))
                        ),
                        Sum(
                            4,
                            listOf(LiteralValue(7, 12), LiteralValue(0, 13))
                        )
                    )
                ),
                23
            ),
            Arguments.of(
                "A0016C880162017C3686B18A3D4780",
                Sum(
                    5,
                    listOf(
                        Sum(
                            1,
                            listOf(
                                Sum(
                                    3,
                                    listOf(
                                        LiteralValue(7, 6),
                                        LiteralValue(6, 6),
                                        LiteralValue(5, 12),
                                        LiteralValue(2, 15),
                                        LiteralValue(2, 15)
                                    )
                                )
                            )
                        )
                    )
                ),
                31
            )
        )

        @JvmStatic
        fun computeValueArguments(): List<Arguments> = listOf(
            Arguments.of("C200B40A82", 3),
            Arguments.of("04005AC33890", 54),
            Arguments.of("880086C3E88112", 7),
            Arguments.of("CE00C43D881120", 9),
            Arguments.of("D8005AC2A8F0", 1),
            Arguments.of("F600BC2D8F", 0),
            Arguments.of("9C005AC2F8F0", 0),
            Arguments.of("9C0141080250320F1802104A08", 1)
        )
    }

    @ParameterizedTest
    @MethodSource("hexValues")
    fun parseHex(
        hex: String,
        expectedBinary: String
    ) {
        val binaryString = decoder.parseHex(hex.first())

        assertThat(binaryString, `is`(expectedBinary))
    }

    @ParameterizedTest
    @MethodSource("parseTransmissionArguments")
    fun parseTransmission(
        transmission: String,
        expectedPacket: Packet,
        expectedVersionSum: Long
    ) {
        val packet = decoder.parseTransmission(transmission)
        assertThat(packet, `is`(expectedPacket))

        val versionSum = packet.versionSum()
        assertThat(versionSum, `is`(expectedVersionSum))
    }

    @ParameterizedTest
    @MethodSource("computeValueArguments")
    fun computeValue(
        transmission: String,
        expectedValue: Long
    ) {
        val result = decoder.parseTransmission(transmission).computeValue()
        assertThat(result, `is`(expectedValue))
    }

}