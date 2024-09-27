package contacts

abstract class BaseContact(
    open var name: String = "",
    open var phoneNumber: String = "",
    open var createdTime: String = "",
    open var modifiedTime: String = ""
) {
    abstract fun setProperty(propertyName: String, value: Any)

    abstract fun getPossibleProperties(): List<String>

    abstract fun getProperty(propertyName: String): Any?

    abstract fun edit()

    abstract fun info(index: Int)

    abstract fun detailedInfo()

}