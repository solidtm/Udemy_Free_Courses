package com.solid.ufc.util

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

fun Context.showDialog(
    title: String = "Error",
    message: String,
    positiveTitle: String = "ok",
    negativeTitle: String? = "",
    dismiable: Boolean = false,
    negativeCallback: (() -> Unit)? = null,
    positiveCallback: (() -> Unit)? = null
) {

    MaterialDialog(this).show {
        cornerRadius(5F)
        title(text = title)
        message(text = message)
        cancelable(dismiable)

        positiveButton(text = positiveTitle) { dialog ->
            positiveCallback?.invoke()
        }

        negativeButton(text = negativeTitle) {
            negativeCallback?.invoke()
            dismiss()
        }
    }
}