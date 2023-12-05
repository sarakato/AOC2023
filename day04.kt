import java.io.File

fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

    val fileName = "/Users/Sara.Kato/IdeaProjects/AOC/src/main/kotlin/04-input-01.txt" // Replace with the actual file name or path
    var sum = 0
    try {
        val file = File(fileName)

        // Check if the file exists
        if (file.exists()) {
            // Read each line from the file and print it

            val cardCounts = ArrayDeque<Int>()
            file.forEachLine {
                val cardInfo = it.split(":")
                val numbers = cardInfo[1].split("|")
                //println("numbers=$numbers")
                val winningNumbers = numbers[0]
                val myNumbers = numbers[1].trimEnd().trimStart().split(" ")

                //println("winning='$winningNumbers'")
                //println("mine='$myNumbers'")

                var currCardMatchCounts = 0
                var numberOfCurrCard = 0

                // make it an array?
                for (number in myNumbers) {
                    if (winningNumbers.contains(" $number ") && number != "") {
                        //println("    '$number' is contained in $cardInfo")
                        /*if (currRound == 0) {
                            currRound = 1
                        } else {
                            currRound *= 2
                        }*/
                        currCardMatchCounts ++
                    }
                }
                // number of current card
                val amountOfCurrentCard = if (cardCounts.isEmpty()) 1 else cardCounts.removeFirst() + 1
                sum += amountOfCurrentCard

                println("$cardInfo has $currCardMatchCounts matches, $amountOfCurrentCard cards")


                // add the number of cards for the next round.
                if (cardCounts.isEmpty()) {
                    // add one
                    var i = 0
                    while (i < currCardMatchCounts) {
                        cardCounts.addFirst(1)
                        i ++
                    }
                } else if (currCardMatchCounts > 0) {
                    // for each element, +1, then add it onto stack again
                    val tempStack = ArrayDeque<Int>()
                    var i = 0
                    println("Adding $amountOfCurrentCard for next $currCardMatchCounts")
                    println("    before=$cardCounts")
                    while (i < currCardMatchCounts) {
                        if (cardCounts.isNotEmpty()) {
                            tempStack.addFirst(cardCounts.removeFirst())
                        } else {
                            tempStack.addFirst(0)
                        }
                        i ++
                    }

                    // re-add all the elements, incrementing by 1 each time.
                    while(tempStack.isNotEmpty()) {
                        cardCounts.addFirst(tempStack.removeFirst() + amountOfCurrentCard)
                    }
                }
                println("    cardCounts=$cardCounts")
                //println("$cardInfo has $currRound points")
            }
            println("sum=$sum")
        } else {
            println("File not found: $fileName")
        }
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }
}
