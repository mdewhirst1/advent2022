/*
Each rucksack has two large compartments.
All items of a given type are meant to go into exactly one of the two compartments.

The list of items for each rucksack is given as characters all on a single line.
A given rucksack always has the same number of items in each of its two compartments

To help prioritize item rearrangement, every item type can be converted to a priority:
Lowercase item types a through z have priorities 1 through 26.
Uppercase item types A through Z have priorities 27 through 52.

Find the item type that appears in both compartments of each rucksack.
What is the sum of the priorities of those item types?

Additionally, nobody wrote down which item type corresponds to each group's badges.
The only way to tell which item type is the right one is by finding
the one item type that is common between all three Elves in each group.

Find the item type that corresponds to the badges of each three-Elf group.
What is the sum of the priorities of those item types?

part 1: 7817
part 2: 2444
 */

private val input = readResourceFileAsLines("3.txt")

private val itemPriorities = mapOf(
    'a' to 1,
    'b' to 2,
    'c' to 3,
    'd' to 4,
    'e' to 5,
    'f' to 6,
    'g' to 7,
    'h' to 8,
    'i' to 9,
    'j' to 10,
    'k' to 11,
    'l' to 12,
    'm' to 13,
    'n' to 14,
    'o' to 15,
    'p' to 16,
    'q' to 17,
    'r' to 18,
    's' to 19,
    't' to 20,
    'u' to 21,
    'v' to 22,
    'w' to 23,
    'x' to 24,
    'y' to 25,
    'z' to 26
)

fun main() {
    println("part 1: ${part1()}")
    println("part 2: ${part2()}")
}

private fun part1(): Int =
    input.sumOf { rucksack -> rucksack.splitCompartments().findCommonItem().getPriority() }

private fun part2(): Int =
    input.chunked(3).sumOf { groupRucksacks -> groupRucksacks.findCommonItem().getPriority() }

fun String.splitCompartments(): List<String> = this.chunked(this.length/2)

fun List<String>.findCommonItem() =
    this.map{ it.toSet() }.reduce { acc, chars -> acc.intersect(chars) }.first()

fun Char.getPriority() =
    if (this.isUpperCase()) itemPriorities.getValue(this.lowercaseChar()) + 26 else itemPriorities.getValue(this)