package ru.vitalysizov.moviedb.utils

import android.content.SharedPreferences
import androidx.databinding.ViewDataBinding
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}

fun SharedPreferences.string(
    defaultValue: String = "",
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String> = object : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String =
        getString(key(property), defaultValue) ?: ""

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) =
        edit().putString(key(property), value).apply()
}

fun SharedPreferences.stringNullable(
    defaultValue: String? = null,
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String?> = object : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? =
        getString(key(property), defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) =
        edit().putString(key(property), value).apply()

}

inline fun <reified T> SharedPreferences.json(
    defaultValue: T,
    key: String? = null,
    cacheValue: Boolean = false
) = object : ReadWriteProperty<Any, T> {
        private val gson = Gson()

        private var cache: T? = null

        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            cache?.let { return it }
            val jsonString = getString(key ?: property.name, "")

            return when {
                jsonString.isNullOrEmpty() -> {
                    defaultValue
                }
                else -> {
                    val newValue = gson.fromJson(jsonString, T::class.java)
                    if (cacheValue) {
                        cache = newValue
                    }
                    newValue
                }
            }
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            edit().putString(key ?: property.name, gson.toJson(value)).apply()
            if (cacheValue) {
                cache = value
            }
        }
    }