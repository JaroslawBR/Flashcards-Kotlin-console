package flashcards

import kotlin.system.exitProcess
import java.io.File
import kotlin.random.Random

fun choose(mapCard: MutableMap<String, String>) {
    println("Input the action (add, remove, import, export, ask, exit):")
    when(readln()) {
        "add" -> add(mapCard)
        "remove" -> remove(mapCard)
        "import" -> import(mapCard)
        "export" -> export(mapCard)
        "ask" -> askRandom(mapCard)
        "exit" -> { println("Bye bye!"); exitProcess(0) }

    }
}

fun add(mapCard: MutableMap<String, String>) {
    println("The card:")
    val question: String = readln()
         if (mapCard.contains(question))  {
            println("The card \"$question\" already exists")
             println()
             return
         }
    println("The definition of the card:")
    val definition: String = readln()
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
    val removeCard = readln()
    if (mapCard.contains(removeCard)) {
        mapCard.remove(removeCard)
        println("The card has been removed.")
    } else println("Can't remove \"$removeCard\": there is no such card.")
    println()
}

fun import(mapCard: MutableMap<String, String>){
    println("File name:")
    val fileName ="${readlnOrNull()}"
    val file = File(fileName)
    var count = 0
    if (file.exists()) {
        for (i in file.readLines() ) {
            val import = i.split(":")
            if (import.size == 2) {
                val question = import[0]
                val definition = import[1]
                mapCard[question] = definition
                count ++
            } else println("wrong file format")
        }
        println("$count cards have been loaded.")
    } else println("File not found.")
    println()
}

fun export(mapCard: MutableMap<String, String>){
    println("File name:")
    var count = 0
    val fileDestination ="${readlnOrNull()}"
    if (File(fileDestination).exists()) File(fileDestination).delete()
    for ((q,d) in mapCard) {
        val newLine = "$q:$d"
        File(fileDestination).appendText("$newLine\n")
        count ++
    }
    println("$count cards have been saved.")
    println()
}

fun askRandom(mapCard: MutableMap<String, String>) {
    println("How many times to ask?")
    val askNumber = readln().toInt()
    val random = Random
    val keyList = mapCard.keys.toList()
    repeat(askNumber) {
        val randomKey = keyList[random.nextInt(keyList.size)]
        println("Print the definition of \"$randomKey\":")
        val answer = readln()
        if (answer == mapCard[randomKey]) println("Correct!")
        else if (mapCard.containsValue(answer)) {
            for ((w, f) in mapCard) {
                if (answer == f) {
                    println("Wrong. The right answer is \"${mapCard[randomKey]}\", but your definition is correct for \"$w\".")
                    break
                }
            }
        } else println("Wrong. The right answer is \"${mapCard[randomKey]}\".")
    }
    println()
}



fun main() {
    val mapCard: MutableMap<String, String> = mutableMapOf()
    while (true)
    {
        choose(mapCard)

    }



}



