/*
Some of the pairs have noticed that one of their assignments fully contains the other.
For example, 2-8 fully contains 3-7, and 6-6 is fully contained by 4-6.

part 1 In how many assignment pairs does one range fully contain the other? 562

--- Part Two ---
the Elves would like to know the number of pairs that overlap at all.
In the above example, the first two pairs (2-4,6-8 and 2-3,4-5) don't overlap,
while the remaining four pairs (5-7,7-9, 2-8,3-7, 6-6,4-6, and 2-6,4-8) do overlap

In how many assignment pairs do the ranges overlap? 924
 */

private val input = readResourceFileAsLines("4.txt").map {
    val (left, right) =  it.split(',')
    left.toRange() to right.toRange()
}

fun String.toRange(): IntRange {
    val (low, high) = this.split('-')
    return low.toInt() .. high.toInt()
}

fun IntRange.containsOther(otherRange: IntRange): Boolean {
    return this.first >= otherRange.first && this.last <= otherRange.last
}

fun IntRange.overlapsOther(otherRange: IntRange): Boolean {
    return (this.first <= otherRange.first && this.last >= otherRange.first)
            || (this.first >= otherRange.first && this.first <= otherRange.last)
}

fun main() {
    println("part 1: ${part1()}")

    println("part 2: ${part2()}")
}

private fun part1(): Int {
    return input.count { it.first.containsOther(it.second) || it.second.containsOther(it.first)  }
}

private fun part2(): Int {
    return input.count { it.first.overlapsOther(it.second) }
}