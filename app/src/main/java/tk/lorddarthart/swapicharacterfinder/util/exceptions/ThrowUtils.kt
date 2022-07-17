package tk.lorddarthart.swapicharacterfinder.util.exceptions

import kotlin.reflect.KClass

object ThrowUtils {
    fun throwUninitializedBinding(message: String = ""): Nothing {
        throw UninitializedBindingException(message.ifBlank { "Binding was not initialized" })
    }

    fun throwMissingComponentException(component: KClass<*>, message: String? = null): Nothing {
        throw MissingComponentException(component, message)
    }
}