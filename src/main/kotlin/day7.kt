/*
The filesystem consists of a tree of files (plain data) and directories (which can contain other directories or files).
The outermost directory is called /.
You can navigate around the filesystem, moving into or out of directories and listing the contents of the directory you're currently in.

Within the terminal output, lines that begin with $ are commands you executed, very much like some modern computers:

cd means change directory. This changes which directory is the current directory, but the specific result depends on the argument:
cd x moves in one level: it looks in the current directory for the directory named x and makes it the current directory.
cd .. moves out one level: it finds the directory that contains the current directory, then makes that directory the current directory.
cd / switches the current directory to the outermost directory, /.
ls means list. It prints out all of the files and directories immediately contained by the current directory:
123 abc means that the current directory contains a file named abc with size 123.
dir xyz means that the current directory contains a directory named xyz.

find all of the directories with a total size of at most 100000, then calculate the sum of their total sizes.
In the example, these directories are a and e; the sum of their total sizes is 95437 (94853 + 584).
(As in this example, this process can count files more than once!)

Find all of the directories with a total size of at most 100000. What is the sum of the total sizes of those directories?

2031851

--- Part Two ---

The total disk space available to the filesystem is 70000000.
To run the update, you need unused space of at least 30000000.
You need to find a directory you can delete that will free up enough space to run the update.

To achieve this, you have the following options:

Delete directory e, which would increase unused space by 584.
Delete directory a, which would increase unused space by 94853.
Delete directory d, which would increase unused space by 24933642.
Delete directory /, which would increase unused space by 48381165.
Directories e and a are both too small; deleting them would not free up enough space.
However, directories d and / are both big enough! Between these, choose the smallest: d, increasing unused space by 24933642.

Find the smallest directory that, if deleted, would free up enough space on the filesystem to run the update.
What is the total size of that directory?

2568781
 */

private val rootDirectory = parseCommands(readResourceFileAsLines("7.txt"))

typealias File = Pair<String, Int>

private data class Directory(
    val name: String,
    val parent: Directory? = null
) {
    private val files: MutableList<File> = mutableListOf()
    private val subDirectories: MutableList<Directory> = mutableListOf()

    fun addFile(file: File) {
        files.add(file)
    }

    fun addSubDirectory(directory: Directory) {
        subDirectories.add(directory)
    }

    fun getSubDirectory(name: String): Directory? =
        subDirectories.firstOrNull { it.name == name }

    fun getSize(): Int =
        files.sumOf { it.second } + subDirectories.sumOf { it.getSize() }

    fun getAllSubDirectories(): List<Directory> =
        subDirectories + subDirectories.flatMap { it.getAllSubDirectories() }
}

fun main() {
    println("part 1: ${part1()}")
    println("part 2: ${part2()}")
}

private fun part1() =
    rootDirectory.getAllSubDirectories().filter { it.getSize() <= 100000 }.sumOf { it.getSize() }

private fun part2() =
    rootDirectory.getAllSubDirectories()
        .filter { it.getSize() > 30000000 - (70000000 - rootDirectory.getSize()) }
        .minOf { it.getSize() }

private fun parseCommands(input: List<String>): Directory {
    val homeDirectory = Directory("/")
    var currentDirectory = homeDirectory

    for (line in input) {
        when {
            line.startsWith("$") -> {
                val command = line.substring(2)
                when {
                    command.startsWith("cd") -> {
                        val subCommand = command.substring(3)
                        currentDirectory = when {
                            subCommand.startsWith("..") -> currentDirectory.parent ?: currentDirectory
                            else -> currentDirectory.getSubDirectory(subCommand) ?: currentDirectory
                        }
                    }
                    command.startsWith("ls") -> Unit // can just ignore this
                    else -> println("error")
                }
            }
            line.startsWith("dir") -> {
                val newDir = Directory(line.split(" ").last(), currentDirectory)
                currentDirectory.addSubDirectory(newDir)
            }
            else -> {
                val (fileSize, fileName) = line.split(" ")
                currentDirectory.addFile(fileName to fileSize.toInt())
            }
        }
    }

    return homeDirectory
}