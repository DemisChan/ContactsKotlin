data class Person(var name: String, var surname: String, var phoneNumber: String)

class Contacts(
    var name: String = "",
    var surname: String = "",
) {


    val a = """[^\+?][A-Za-z0-9]{2,}(\s|-)?(\(?[A-Za-z0-9]{2,}\)?)?((\s|-)?[A-Za-z0-9]{2,})*"""
    val b = """(^\+)?\(?[A-Za-z0-9]{1,}\)?((\s|-)?[A-Za-z0-9]{2,})*"""
    val c = """\([A-Za-z0-9]{2,}\)((\s|-)?[A-Za-z0-9]{2,})*"""



    private val concat = "$a|$b|$c"

    private val regex = Regex(concat)


    private val contacts: MutableList<Person> = mutableListOf()

    private var phoneNumber: String = ""
        get() = field
        set(value) {
            if (value.matches(regex)) {
                field = value
            } else {
                field = "[no number]"
                println("Wrong number format!")
            }
        }

    fun add() {
        print("Enter the name:")
        name = readln()
        print("Enter the surname:")
        surname = readln()
        print("Enter the number:")
        phoneNumber = readln()
        val person = Person(name = name, surname = surname, phoneNumber)
        contacts.add(person)
        println("The record added.")
    }
    fun remove() {
        if(contacts.size == 0) {
            println("No records to remove!")
        } else {
            contacts.forEachIndexed { index, person -> println("${index + 1}. ${person.name} ${person.surname}, ${person.phoneNumber}") }
            print("Select a record:")
            val index = readln().toInt() - 1
            contacts.removeAt(index)
            print("The record removed!")
        }
    }
    fun edit() {
        if (contacts.size == 0) {
            println("No records to edit!")
        } else {
            contacts.forEachIndexed { index, person -> println("${index + 1}. ${person.name} ${person.surname}, ${person.phoneNumber}") }
            print("Select a record:")
            val index = readln().toInt() - 1
            print("Select a field (name, surname, number): ")
            when (readln()) {
                "name" -> contacts[index].name = readln()
                "surname" -> contacts[index].surname = readln()
                "number" -> { print("Enter the number: ")
                    val number = readln()
                    if (!number.matches(regex)) {
                        contacts[index].phoneNumber = "[no number]"
                        println("Wrong number format!")
                    } else {
                        contacts[index].phoneNumber = number
                    }
                }
            }
            println("The record updated!")
        }
    }

    fun count() {
        println("The Phone Book has ${contacts.size} records.")
    }
    fun list() {
        contacts.forEachIndexed { index, person -> println("${index + 1}. ${person.name} ${person.surname}, ${person.phoneNumber}") }
    }
    fun exit(){
    }
    fun hasNumber() {
    }
    private fun isNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.matches(regex)
    }
}