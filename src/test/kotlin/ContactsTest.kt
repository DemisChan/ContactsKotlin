import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ContactsTest {
    private var contacts: Contacts? = null
    private val outputStream = ByteArrayOutputStream()
    private val printStream = PrintStream(outputStream)

    @BeforeEach
    fun setUp() {
        contacts = Contacts()
        System.setOut(printStream)

    }

    @Test
    fun `if no records exist count should return`() {
        val result = contacts!!.count()
        assertEquals(print("The Phone Book has 5 records."), result)
    }

    @Test
    fun `if no records exist edit should return`() {
        val result = contacts!!.edit()
        assertEquals("No records to edit!", result)
    }

    @Test
    fun `if no records exist to remove should return`() {
        val result = contacts!!.remove()
        assertEquals("No records to remove!", result)
    }

    @Test
    fun `add valid record` () {
        val input = "John\nDoe\n+0 (123) 456-789-ABcd\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        contacts!!.add()

        assertEquals("John", contacts!!.name)
        assertEquals("Doe", contacts!!.surname)
    }

}