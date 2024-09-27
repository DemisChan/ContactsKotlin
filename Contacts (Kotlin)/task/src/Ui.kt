package contacts

class Ui {
    fun displayMainMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter action (add, list, search, count, exit): ")
    }

    fun displayAddMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter the type (person, organization): ")
    }

    fun displayListMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter action ([number], back): ")
    }

    fun displayRecordMenu(action: Action) {
        print("[${action.name.lowercase()}] Enter action (edit, delete, menu): ")
    }
}

class UiImproved {
    fun displayMainMenu() {
        print("[menu] Enter action (add, list, search, count, exit): ")
    }

    fun displayAddMenu() {
        print("Enter the type (person, organization): ")
    }

    fun displayListMenu() {
        print("[list] Enter action ([number], back): ")
    }

    fun displaySearchMenu() {
        print("[search] Enter action ([number], back, again): ")
    }

    fun displayRecordMenu() {
        print("[record] Enter action (edit, delete, menu): ")
    }

    fun displayEditMenu(fields: List<String>) {
        println("Select a field (${fields.joinToString(", ")}): ")
    }
}

