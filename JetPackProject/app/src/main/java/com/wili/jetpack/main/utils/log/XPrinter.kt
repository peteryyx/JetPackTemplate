package com.wili.jetpack.main.utils.log

import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import kotlin.math.min

/**
 * Created by wanglei on 2016/11/29.
 */
object XPrinter {
    private const val MAX_LENGTH_OF_SINGLE_MESSAGE = 3500

    @JvmStatic
    var lineSeparator = lineSeparator()

    fun println(logLevel: Int, tag: String, msg: String) {
        if (msg.length <= MAX_LENGTH_OF_SINGLE_MESSAGE) {
            printChunk(logLevel, tag, msg)
            return
        }
        val msgLength = msg.length
        var start = 0
        var end = start + MAX_LENGTH_OF_SINGLE_MESSAGE
        while (start < msgLength) {
            printChunk(logLevel, tag, msg.substring(start, end))
            start = end
            end = min(start + MAX_LENGTH_OF_SINGLE_MESSAGE, msgLength)
        }
    }

    private fun printChunk(logLevel: Int, tag: String, msg: String) {
        Log.println(logLevel, tag, msg)
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun lineSeparator(): String {
        try {
            Class.forName("android.os.Build")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return System.lineSeparator()
            }
        } catch (ignored: Exception) {
        }
        return "\n"
    }
}