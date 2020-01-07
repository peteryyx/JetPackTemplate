package com.wili.jetpack.main.utils.Language

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.Log
import com.wili.jetpack.main.utils.Constant
import com.wili.jetpack.main.utils.shared_preferences.SharedPreferencesUtil
import java.util.*

class LanguageUtil {


    private val TAG = "LanguageUtils"

    /**
     * 中文
     */
    val LOCALE_CHINESE = Locale.CHINESE
    /**
     * 英文
     */
    val LOCALE_ENGLISH = Locale.ENGLISH

    private var systemCurrentLocal = Locale.CHINESE


    private fun LanguageUtils() {}

    companion object {
        @Volatile
        var mInstance: LanguageUtil? = null
            private set
            get() {
                if (field == null) {
                    field = LanguageUtil()
                }
                return field
            }
    }

    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    fun getSystemLocale(context: Context?): Locale {
        return systemCurrentLocal
    }

    private fun setSystemCurrentLocal(local: Locale) {
        systemCurrentLocal = local
    }

    fun saveSystemCurrentLanguage() {
        val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault()[0]
        } else {
            Locale.getDefault()
        }
        Log.d(TAG, locale.language)
        setSystemCurrentLocal(locale)
    }

    fun onConfigurationChanged(context: Context) {
        saveSystemCurrentLanguage()
        setLocal(context)
        setApplicationLanguage(context)
    }

    fun setLocal(context: Context): Context? {
        return updateResources(context, getSetLanguageLocale(context))
    }


    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, locale: Locale): Context? {
        var con = context
        val res = context.applicationContext.resources
        val config = res.configuration
        // 8.0需要使用createConfigurationContext处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
            config.locales = LocaleList(locale)
            con = context.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
        }
        return con
    }

    /**
     * 获取选择的语言设置
     *
     */
    fun getSetLanguageLocale(context: Context?): Locale {
        return when (SharedPreferencesUtil.getValue(Constant.Common.KEY_LANGUAGE, -1)) {
            Constant.DEFAULT -> getSystemLocale(context)
            Constant.CHINESE -> Locale.CHINA
            Constant.ENGLISH -> Locale.ENGLISH
            Constant.KOREAN -> Locale.KOREAN
            else -> Locale.CHINA
        }
    }

    /**
     * 设置语言类型
     */
    fun setApplicationLanguage(context: Context) {
        val resources =
            context.applicationContext.resources
        val dm = resources.displayMetrics
        val config = resources.configuration
        val locale = getSetLanguageLocale(context)
        config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            config.locales = localeList
            context.applicationContext.createConfigurationContext(config)
        }
        resources.updateConfiguration(config, dm)
        val language =
            if (locale.language == LOCALE_CHINESE.language) {
                Constant.CHINESE
            } else {
                Constant.ENGLISH
            }
        SharedPreferencesUtil.putValue(Constant.Common.KEY_LANGUAGE, language)
    }

    fun saveSelectLanguage(context: Context, select: Int) {
        if (select != -1) {
            SharedPreferencesUtil.putValue(Constant.Common.KEY_LANGUAGE, select)
        }
        setApplicationLanguage(context)
    }

}