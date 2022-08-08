package com.solid.ufc.util

interface ProgressLoader {
    fun show(message: String? = null, cancellable: Boolean = false)
    fun hide()
}