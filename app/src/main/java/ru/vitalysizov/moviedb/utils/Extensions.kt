package ru.vitalysizov.moviedb.utils

import android.animation.ValueAnimator
import android.content.SharedPreferences
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.animation.doOnEnd
import androidx.core.view.drawToBitmap
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
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

/**
 * Potentially animate showing a [BottomNavigationView].
 *
 * Abruptly changing the visibility leads to a re-layout of main content, animating
 * `translationY` leaves a gap where the view was that content does not fill.
 *
 * Instead, take a snapshot of the view, and animate this in, only changing the visibility (and
 * thus layout) when the animation completes.
 */
fun BottomNavigationView.show() {
    if (visibility == VISIBLE) return

    val parent = parent as ViewGroup
    // View needs to be laid out to create a snapshot & know position to animate. If view isn't
    // laid out yet, need to do this manually.
    if (!isLaidOut) {
        measure(
            View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
        )
        layout(parent.left, parent.height - measuredHeight, parent.right, parent.height)
    }

    val drawable = BitmapDrawable(context.resources, drawToBitmap())
    drawable.setBounds(left, parent.height, right, parent.height + height)
    parent.overlay.add(drawable)
    ValueAnimator.ofInt(parent.height, top).apply {
        startDelay = 100L
        duration = 300L
        interpolator = AnimationUtils.loadInterpolator(
            context,
            android.R.interpolator.linear_out_slow_in
        )
        addUpdateListener {
            val newTop = it.animatedValue as Int
            drawable.setBounds(left, newTop, right, newTop + height)
        }
        doOnEnd {
            parent.overlay.remove(drawable)
            visibility = VISIBLE
        }
        start()
    }
}

/**
 * Potentially animate hiding a [BottomNavigationView].
 *
 * Abruptly changing the visibility leads to a re-layout of main content, animating
 * `translationY` leaves a gap where the view was that content does not fill.
 *
 * Instead, take a snapshot, instantly hide the view (so content lays out to fill), then animate
 * out the snapshot.
 */
fun BottomNavigationView.hide() {
    if (visibility == GONE) return

    val drawable = BitmapDrawable(context.resources, drawToBitmap())
    val parent = parent as ViewGroup
    drawable.setBounds(left, top, right, bottom)
    parent.overlay.add(drawable)

    visibility = GONE

    ValueAnimator.ofInt(top, parent.height).apply {
        startDelay = 100L
        duration = 300L
        interpolator = AnimationUtils.loadInterpolator(
            context,
            android.R.interpolator.fast_out_linear_in
        )
        addUpdateListener {
            val newTop = it.animatedValue as Int
            drawable.setBounds(left, newTop, right, newTop + height)
        }
        doOnEnd {
            parent.overlay.remove(drawable)
        }
        start()
    }
}