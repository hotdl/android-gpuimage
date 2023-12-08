package jp.co.cyberagent.android.gpuimage.sample.utils

import android.util.Log

fun <T> Any.eLog(tips: String = "", tag: String = "hdl--"): T {
    Log.e(tag, "$tips $this")
    return this as T
}