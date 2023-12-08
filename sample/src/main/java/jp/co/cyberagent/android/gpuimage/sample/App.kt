package jp.co.cyberagent.android.gpuimage.sample

import android.app.Application
import jp.co.cyberagent.android.gpuimage.sample.utils.AppUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtil.app = this
    }
}