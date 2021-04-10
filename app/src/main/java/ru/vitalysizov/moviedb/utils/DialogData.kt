package ru.vitalysizov.moviedb.utils

import android.content.Context
import androidx.annotation.ArrayRes

class ConfirmationDialogData(
    val context: Context,
    val title: String,
    @ArrayRes val singleItems: Int,
    val checkedItem: Int,
    val positiveButtonCallback: (position: Int) -> Unit
)