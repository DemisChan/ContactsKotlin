package contacts

import java.io.*
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/** Basically, you need to implement the functionality in one place inside a concrete class.
All of the derived classes should implement this method, and in the base class, there should be an abstract method.
In the code, you actually call this abstract method and get different code executions in the derived classes.

To solve your problem, you should create several methods:

A method that returns all of the possible properties this class is able to change.
A method that takes a string that represents a property that the class is able to change and its new value.
A method that takes a string representation of the property and returns the value of this property.
 **/

enum class Action {
    ADD, REMOVE, EDIT, COUNT, LIST, SEARCH, RECORD, MENU, EXIT
}

class ContactBook(private val filename: String?): Serializable {


    init {
        if (filename != null) {
            loadFromFile()
        }
    }

    private val a = """[^\+?][A-Za-z0-9]{2,}(\s|-)?(\(?[A-Za-z0-9]{2,}\)?)?((\s|-)?[A-Za-z0-9]{2,})*"""
    private val b = """(^\+)?\(?[A-Za-z0-9]{1,}\)?((\s|-)?[A-Za-z0-9]{2,})*"""
    private val c = """\([A-Za-z0-9]{2,}\)((\s|-)?[A-Za-z0-9]{2,})*"""
    private val d = """\+?\d{1,}(\s|-)?\(?\d{3}\)?(\s|-)?\d{3}(\s|-)?\d{3}(\s|-)?\d{4}"""

    private val concat = "$a|$b|$c|$d"

    private val regex = Regex(concat)

    private var contacts: MutableList<BaseContact> = mutableListOf()

    private var birthday = ""
        set(value) {
            if (value.isNotBlank()) {
                field = value
            }
            field = "[no data]"
            println("Bad birth date!")

        }

    private var gender = ""
        set(value) {
            if (value == "M" || value == "F") {
                field = value
            }
            field = "[no data]"
            println("Bad gender!")
        }

    private var phoneNumber: String = ""
        set(value) {
            if (value != "") {
                field = value
            }
            field = "[no number]"
            println("Wrong number format!")

        }

    private fun addPerson() {
        val name = print("Enter the name:").run { readln() }
        val surname = print("Enter the surname:").run { readln() }
        birthday = print("Enter the birth date:").run { readln() }
        gender = print("Enter the gender (M, F):").run { readln() }
        phoneNumber = print("Enter the number:").run { readln() }
        val timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        val person = Person(
            name = name,
            surname = surname,
            birthday = birthday,
            gender = gender,
            phoneNumber,
            createdTime = timeNow,
            modifiedTime = timeNow
        )
        contacts.add(person)
        println("The record added.")

    }

    private fun addOrganization() {

        val name = print("Enter the name:").run { readln() }
        val address = print("Enter the address:").run { readln() }
        phoneNumber = print("Enter the number:").run { readln() }
        val timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
        val organization =
            Organization(name = name, address = address, phoneNumber, createdTime = timeNow, modifiedTime = timeNow)
        contacts.add(organization)
        println("The record added.")
    }

    fun add() {
        when (readln()) {
            "person" -> {
                addPerson()
            }

            "organization" -> {
                addOrganization()
            }
        }
        println()
    }

    fun remove() {
        if (contacts.size == 0) {
            println("No records to remove!")
        } else {
            contacts.forEachIndexed { index, _ ->
                when (val entity = contacts[index]) {
                    is Person -> println("${index + 1}. ${entity.name} ${entity.surname}, ${entity.phoneNumber}")
                    is Organization -> println("${index + 1}. ${entity.name} ${entity.address}, ${entity.phoneNumber}")
                }
                print("Select a record:")
                val n = readln().toInt() - 1
                contacts.removeAt(n)
                print("The record removed!")
            }
        }
        println()
    }

    fun edit() {
        if (contacts.size == 0) {
            println("No records to edit!")
        } else {
            contacts.forEachIndexed { index, type ->
                when (type) {
                    is Person -> println("${index + 1}. ${type.name}")
                    is Organization -> println("${index + 1}. ${type.name}")
                }
            }
            print("Select a record:")
            val n = readln().toInt() - 1
            contacts[n].edit()
        }
        println("The record updated!")
        println()
    }


    fun count() {
        println("The Phone Book has ${contacts.size} records.")
        println()
    }

    fun info() {
        contacts.forEachIndexed { index, baseContact ->
            baseContact.info(index)
        }
    }

    fun listOption() {
        val n = readln().toInt() - 1
        val entity = contacts[n]
        entity.detailedInfo()
        println()
    }

    private fun isNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.matches(regex)
    }
}

fun main() {
    val contacts = ContactBook()
    val phoneBookUI = Ui()
    while (true) {
        phoneBookUI.displayMainMenu(action = Action.MENU)
        val actionInput = readln()
        val action = try {
            Action.valueOf(actionInput.uppercase())
        } catch (e: IllegalArgumentException) {
            println("Invalid action.")
            continue
        }
        when (action) {
            Action.ADD -> {
                phoneBookUI.displayAddMenu(Action.ADD)
                contacts.add()
            }
            Action.REMOVE -> contacts.remove()
            Action.EDIT -> contacts.edit()
            Action.COUNT -> contacts.count()
            Action.LIST -> {
                contacts.info()
                phoneBookUI.displayListMenu(Action.LIST)
                contacts.listOption()
                readln()
                continue

            }
            Action.RECORD -> {
                phoneBookUI.displayRecordMenu(Action.RECORD)
            }
            Action.MENU -> continue
            Action.EXIT -> break
        }
    }
}