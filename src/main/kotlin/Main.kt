fun main() {
    val personDetails = Contacts()
    while (true) {
        print("Enter action (add, remove, edit, count, list, exit):")
        when (readln()) {
            "add" -> personDetails.add()
            "remove" -> personDetails.remove()
            "edit" -> personDetails.edit()
            "count" -> personDetails.count()
            "list" -> personDetails.list()
            "exit" -> break
        }
    }

}