package flashcards

import kotlin.system.exitProcess
import java.io.File
import kotlin.random.Random
import kotlin.io.readln as originalReadLine
import kotlin.io.println as originalPrintln

val logList = mutableListOf<String>()
fun println(message: Any? = ""){
    originalPrintln(message)
    val logMessage = message?.toString() ?: ""
    logList.add(logMessage)
}
fun readLine(): String {
    val input = originalReadLine()
    logList.add(input)
    return input
}

fun choose(mapCard: MutableMap<String, String>, hardestCard: MutableMap<String, Int>) {
    println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
    when(readLine()) {
        "add" -> add(mapCard)
        "remove" -> remove(mapCard)
        "import" -> import(mapCard, hardestCard)
        "export" -> export(mapCard, hardestCard)
        "ask" -> askRandom(mapCard, hardestCard)
        "exit" -> { println("Bye bye!"); exitProcess(0) }
        "log" -> log(logList)
        "hardest card" -> hardest(hardestCard)
        "reset stats" -> resetStats(hardestCard)
    }
}

fun resetStats(hardestCard: MutableMap<String, Int>){
    hardestCard.clear()
    println("Card statistics have been reset.")
    println()
}

fun hardest(hardestCard: MutableMap<String, Int>) {
    if (hardestCard.isEmpty()) {
        println("There are no cards with errors.")
        println()
    } else {
        val maxError = hardestCard.maxByOrNull { it.value }
        val valueError = maxError?.value!!
        val theSameValueKey = hardestCard.filter { it.value == valueError }.keys
        when (theSameValueKey.size) {
            1 -> println("The hardest card is ${theSameValueKey.joinToString { "\"$it\"" }}. You have $valueError errors answering it.")
            else -> println("The hardest cards are ${theSameValueKey.joinToString { "\"$it\"" }}. You have $valueError errors answering them.")
        }
        println()
    }
}

fun log(logList: MutableList<String>){
    println("File name:")
    val fileName = readLine()
    val logFile = File(fileName)
    if (logFile.exists()) logFile.delete()
    for (i in logList) {
        logFile.appendText("$i\n")
    }
    println("The log has been saved.")
    println()
}

fun add(mapCard: MutableMap<String, String>) {
    println("The card:")
    val question: String = readLine()
    if (mapCard.contains(question))  {
        println("The card \"$question\" already exists")
        println()
        return
    }
    println("The definition of the card:")
    val definition: String = readLine()
    if (mapCard.containsValue(definition))  {
        println("The definition \"$definition\" already exists.")
        println()
        return
    }
    mapCard[question] = definition
    println("The pair (\"$question\":\"$definition\") has been added.")
    println()
}

fun remove(mapCard: MutableMap<String, String>){
    println("Which card?")
    val removeCard = readLine()
    if (mapCard.contains(removeCard)) {
        mapCard.remove(removeCard)
        println("The card has been removed.")
    } else println("Can't remove \"$removeCard\": there is no such card.")
    println()
}

fun import(mapCard: MutableMap<String, String>, hardestCard: MutableMap<String, Int>){
    println("File name:")
    val fileName = readLine()
    val file = File(fileName)
    var count = 0
    if (file.exists()) {
        for (i in file.readLines() ) {
            val import = i.split(":")
            if (import.size == 3) {
                val question = import[0]
                val definition = import[1]
                val error = import[2]
                mapCard[question] = definition
                hardestCard[question] = error.toInt()
                count++
            }
            if (import.size == 2) {
                val question = import[0]
                val definition = import[1]
                mapCard[question] = definition
                count++
            }
        }
        println("$count cards have been loaded.")
    } else println("File not found.")
    println()
}

fun export(mapCard: MutableMap<String, String>, hardestCard: MutableMap<String, Int>){
    println("File name:")
    var count = 0
    val fileDestination = readLine()
    if (File(fileDestination).exists()) File(fileDestination).delete()
    for ((q, d) in mapCard) {
        val newLine = if (!hardestCard.containsKey(q)) {
            "$q:$d"
        } else {
            val error = hardestCard[q]!!
            "$q:$d:$error"
        }
        File(fileDestination).appendText("$newLine\n")
        count++
    }
    println("$count cards have been saved.")
    println()
}

fun askRandom(mapCard: MutableMap<String, String>, hardestCard: MutableMap<String, Int>) {
    println("How many times to ask?")
    val askNumber = readLine().toInt()
    val random = Random
    val keyList = mapCard.keys.toList()
    repeat(askNumber) {
        val randomKey = keyList[random.nextInt(keyList.size)]
        println("Print the definition of \"$randomKey\":")
        val answer = readLine()
        if (answer == mapCard[randomKey]) println("Correct!")
        else if (mapCard.containsValue(answer)) {
            if (hardestCard.containsKey(randomKey)) {
                hardestCard[randomKey] = hardestCard[randomKey]!! + 1
            } else hardestCard[randomKey] = 1
            for ((w, f) in mapCard) {
                if (answer == f) {
                    println("Wrong. The right answer is \"${mapCard[randomKey]}\", but your definition is correct for \"$w\".")
                    break
                }
            }
        } else {
            println("Wrong. The right answer is \"${mapCard[randomKey]}\".")
            if (hardestCard.containsKey(randomKey)) {
                hardestCard[randomKey] = hardestCard[randomKey]!! + 1
            } else hardestCard[randomKey] = 1
        }
    }
    println()
}

fun main() {
    val mapCard: MutableMap<String, String> = mutableMapOf()
    val hardestCard: MutableMap<String, Int> = mutableMapOf()
    while (true) {
        choose(mapCard, hardestCard)
    }
}




