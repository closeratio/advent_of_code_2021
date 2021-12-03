package com.closeratio.aoc2021.day3

class DiagnosticReport(
    inputDataString: String
) {

    val values: List<String> = inputDataString
        .trim()
        .split("\n")
        .map(String::trim)

    fun computePowerConsumption(): Long = computeGammaRate() * computeEpsilonRate()

    private fun computeRate(
        characterSelector: (Collection<CharacterCount>) -> CharacterCount
    ): Long = IntRange(0, values.first().length - 1)
        .map { index -> values.map { it[index] } }
        .map { chars ->
            chars.groupBy({ it }) { CharacterCount(it, 1) }
                .mapValues { (_, v) -> v.combine() }
                .values
                .let { characterSelector(it) }
                .character
        }
        .joinToString("")
        .toLong(2)

    fun computeGammaRate(): Long = computeRate { set ->
        set.maxByOrNull(CharacterCount::count)!!
    }

    fun computeEpsilonRate(): Long = computeRate { set ->
        set.minByOrNull(CharacterCount::count)!!
    }

    private fun computeBitCriteria(
        chars: List<Char>,
        discriminant: Char,
        groupSelector: (Collection<GroupedCharacterCounts>) -> GroupedCharacterCounts
    ): Char {

        val candidates: GroupedCharacterCounts = chars
            .groupBy({ it }) { CharacterCount(it, 1) }
            .values
            .map { it.combine() }
            .groupBy(CharacterCount::count) { it }
            .mapValues { (k, v) -> GroupedCharacterCounts(k, v) }
            .values
            .let {
                groupSelector(it)
            }

        return if (candidates.characterCounts.size == 1) {
            candidates.characterCounts.first().character
        } else {
            discriminant
        }
    }

    private fun computeRating(
        index: Int,
        sublist: List<String>,
        discriminant: Char,
        groupSelector: (Collection<GroupedCharacterCounts>) -> GroupedCharacterCounts
    ): Long {
        val criteria = computeBitCriteria(
            sublist.map { it[index] },
            discriminant,
            groupSelector
        )

        val filtered = sublist.filter { it[index] == criteria }

        return if (filtered.size == 1) {
            filtered.first().toLong(2)
        } else {
            computeRating(index + 1, filtered, discriminant, groupSelector)
        }
    }

    fun computeOxygenGeneratorRating(): Long = computeRating(
        0,
        values,
        '1'
    ) { set -> set.maxByOrNull(GroupedCharacterCounts::count)!! }

    fun computeCo2ScrubberRating(): Long = computeRating(
        0,
        values,
        '0'
    ) { set -> set.minByOrNull(GroupedCharacterCounts::count)!! }

    fun computeLifeSupportRating(): Long = computeOxygenGeneratorRating() * computeCo2ScrubberRating()

    private fun Iterable<CharacterCount>.combine(): CharacterCount {
        val uniqueCharacters = map { it.character }.toSet()

        if (uniqueCharacters.size != 1) {
            throw IllegalStateException("Can only combine single set of characters, provided set is $uniqueCharacters")
        }

        return CharacterCount(
            uniqueCharacters.first(),
            sumOf(CharacterCount::count)
        )
    }

}
