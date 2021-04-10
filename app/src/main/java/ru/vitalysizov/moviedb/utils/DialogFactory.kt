package ru.vitalysizov.moviedb.utils

import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vitalysizov.moviedb.R

object DialogFactory {

    fun showConfirmationDialog(dialogData: ConfirmationDialogData) {
        val context = dialogData.context
        val resources = context.resources
        val title = dialogData.title
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setNeutralButton(resources.getString(R.string.button_cancel)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.button_ok)) { dialog, _ ->
                val checkedItemPosition: Int = (dialog as AlertDialog).listView.checkedItemPosition
                dialogData.positiveButtonCallback.invoke(checkedItemPosition)
            }
            .setSingleChoiceItems(dialogData.singleItems, dialogData.checkedItem) { _, _ -> }
            .show()
    }

}