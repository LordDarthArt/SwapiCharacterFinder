package tk.lorddarthart.swapicharacterfinder.util.exceptions

import dagger.Component
import java.lang.IllegalStateException
import kotlin.reflect.KClass

class MissingComponentException(
    component: KClass<*>,
    message: String? = null
) : IllegalStateException(message ?: "Missing required for DI component ${component.simpleName}")