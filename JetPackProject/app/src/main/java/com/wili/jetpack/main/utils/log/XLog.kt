package com.wili.jetpack.main.utils.log

import android.util.Log

object XLog {

    var LOG = true
    var TAG_ROOT = "JetPack"

    fun json(logLevel: Int = Log.DEBUG, tag: String? = null, json: String) {
        if (LOG) {
            val formatJson = LogFormat.formatBorder(arrayOf((LogFormat.formatJson(json))))
            XPrinter.println(logLevel, tag ?: TAG_ROOT, formatJson)
        }
    }

    fun xml(logLevel: Int = Log.DEBUG, tag: String? = null, xml: String) {
        if (LOG) {
            val formatJson = LogFormat.formatBorder(arrayOf((LogFormat.formatXml(xml))))
            XPrinter.println(logLevel, tag ?: TAG_ROOT, formatJson)
        }
    }

    fun error(tag: String? = null, throwable: Throwable?) {
        if (LOG) {
            val formatError = LogFormat.formatBorder(arrayOf(LogFormat.formatThrowable(throwable)))
            XPrinter.println(Log.ERROR, tag ?: TAG_ROOT, formatError)
        }
    }

    fun msg(logLevel: Int = Log.DEBUG, tag: String? = null, format: String, vararg args: Any) {
        if (LOG) {
            val formatMsg = LogFormat.formatBorder(arrayOf(LogFormat.formatArgs(format, *args)))
            XPrinter.println( logLevel, tag ?: TAG_ROOT, formatMsg)
        }
    }

//    fun d(msg: String, vararg args: Any){
//        msg(Log.DEBUG,null, msg, args.toString())
//    }


    fun d(msg: String, vararg args: Any) {
        msg(Log.DEBUG, null, msg, args)
    }

    fun d(tag: String, msg: String, vararg args: Any?) {
        msg(Log.DEBUG, tag, msg, args)
    }

    fun i(msg: String, vararg args: Any?) {
        msg(Log.INFO, null, msg, args)
    }

    fun i(tag: String, msg: String, vararg args: Any?) {
        msg(Log.INFO, tag, msg, args)
    }

    fun e(msg: String, vararg args: Any?) {
        msg(Log.ERROR, null, msg, args)
    }

    fun e(tag: String, msg: String, vararg args: Any?) {
        msg(Log.ERROR, tag, msg, args)
    }


}