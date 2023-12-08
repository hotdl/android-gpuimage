package jp.co.cyberagent.android.gpuimage.sample.utils

object ShaderUtil {
    fun loadShaderFromAssets(path: String): String {
        return AppUtil.app.assets.open(path).bufferedReader().readText()
    }
}