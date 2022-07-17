package tk.lorddarthart.swapicharacterfinder.util.exceptions

class UninitializedBindingException(
    override val message: String = "",
    override val cause: Throwable? = null
) : IllegalStateException(message, cause)