package i_introduction._7_Nullable_Types

fun test() {
    val s: String = "this variable cannot store null references"
    val q: String? = null

    if (q != null) q.length      // you have to check to dereference
    val i: Int? = q?.length      // null
    val j: Int = q?.length ?: 0  // 0
}

fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    if (client == null || message == null) return

    val email = client.personalInfo?.email

    email?.let { mailer.sendMessage(it, message) }
}

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}
