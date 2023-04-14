package com.github.ygngy.godottapsell

var debugMode = false

fun log(msg: String, e: Exception? = null) {
    if (debugMode) {
        if (e == null)
            println("\n> TAPSELL: $msg")
        else {
            println("\n> TAPSELL: $msg - Exception: ${e.message}")
            e.printStackTrace()
        }
    }
}