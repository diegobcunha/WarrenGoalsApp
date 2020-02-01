package com.diegocunha.warrengoalsapp.view.util

import java.util.regex.Pattern

val EMAIL_ADDRESS = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun String?.isValidEmail(): Boolean {
    return !this.isNullOrEmpty() && EMAIL_ADDRESS.matcher(this).matches()
}