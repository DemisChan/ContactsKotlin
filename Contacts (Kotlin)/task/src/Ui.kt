package contacts

enum class MenuAction(val action: String) {
    ADD("add"),
    LIST("list"),
    SEARCH("search"),
    COUNT("count"),
    REMOVE("remove"),
    EDIT("edit"),
    RECORD("record"),
    MENU("menu"),
    EXIT("exit");

    companion object {
        fun fromString(action: String): MenuAction {
            return values().first { it.action == action }
        }
    }
}

class UiImproved {
    fun displayMainMenu(): String {
        print("[menu] Enter action (add, list, search, count, exit): ")
        return readln().lowercase()
    }

    fun displayAddMenu(): String {
        print("Enter the type (person, organization): ")
        return readln().lowercase()
    }

    fun displayListMenu(): String {
        print("[list] Enter action ([number], back): ")
        return readln().lowercase()
    }

    fun displayEditMenu(fields: List<String>): String {
        println("Select a field (${fields.joinToString(", ")}): ")
        return readln().lowercase()
    }

    fun displaySearchMenu(): String {
        print("[search] Enter action ([number], back, again): ")
        return readln().lowercase()
    }

    fun displayRecordMenu(): String {
        print("[record] Enter action (edit, delete, menu): ")
        return readln().lowercase()
    }


    fun selectFieldToEdit(fields: List<String>): String {
        println("Select a field (${fields.joinToString(", ")}): ")
        return readln().lowercase()
    }

    fun getNewFieldValue(field: String): String {
        println("Enter new value for $field: ")
        return readln().lowercase()
    }

}

