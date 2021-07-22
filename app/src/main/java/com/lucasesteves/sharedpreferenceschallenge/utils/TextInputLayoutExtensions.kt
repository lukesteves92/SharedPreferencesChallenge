package com.github.cesar1287.turma1dh.utils

import android.text.Editable
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout?.getText(): String {
    return this?.editText?.text.toString()
}

fun String?.toEditable(): Editable {
    return Editable.Factory().newEditable(this)
}