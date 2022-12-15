/*
In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate N is on top.
Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3 contains a single crate, P.

part 1

After the rearrangement procedure completes, what crate ends up on top of each stack? HBTMTBSDC

part 2

pick up and move multiple crates at once.

After the rearrangement procedure completes, what crate ends up on top of each stack? PQTJRSHWS
 */

typealias Instruction = Triple<Int, Int, Int> // move (\d+) from (\d+) to (\d+)
typealias Stacks = MutableMap<Int, ArrayList<Char>>

private val rawInput = readResourceFile("5.txt")

fun main() {
    println("part 1: ${part1()}")
    println("part 2: ${part2()}")
}

private fun part1(): String {
    val (stacks, instructions) = parseInput()

    instructions.map { (movesToMake, from, to) ->
        repeat(movesToMake) {
            val poppedCrate = stacks[from]!!.removeFirst()
            stacks[to]!!.add(0, poppedCrate)
        }
    }

    return stacks.readTopStackValues()
}

private fun part2(): String {
    val (stacks, instructions) = parseInput()

    instructions.map { (amountToMove, from, to) ->
        val poppedCrates = buildList(amountToMove) {
            repeat(amountToMove) {
                add(0, stacks[from]!!.removeFirst())
            }
        }
        poppedCrates.map { stacks[to]!!.add(0, it) }
    }

    return stacks.readTopStackValues()
}

fun parseInput(): Pair<Stacks, List<Instruction>> {
    val(rawStacks, rawInstructions) = rawInput.split("\n\n").map { it.lines() }

    val instructionMatcher = Regex("move (\\d+) from (\\d+) to (\\d+)")
    val instructions = rawInstructions.map { rawInstruction ->
        val matches = instructionMatcher.find(rawInstruction)!!.groupValues
        Instruction(matches[1].toInt(), matches[2].toInt(), matches[3].toInt())
    }

    val numberOfStacks = rawStacks[rawStacks.size-1].last().toString().toInt() // read the last number of the stack numbers
    val stacks: Stacks = mutableMapOf()

    for (i in 1..numberOfStacks) stacks[i] = arrayListOf()

    // for each input line find if there is a char for the stack add it to the stack
    rawStacks.dropLast(1).forEach { stackString ->
        stacks.map { stack ->
            stackString.getOrNull((stack.key - 1) * 4 + 1)?.let {
                if (it.isLetter()) stacks[stack.key]!!.add(it)
            }
        }
    }

    return stacks to instructions
}

fun Stacks.readTopStackValues() = this.values.map { it.first() }.joinToString("")