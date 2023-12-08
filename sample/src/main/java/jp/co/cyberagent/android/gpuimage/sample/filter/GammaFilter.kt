package jp.co.cyberagent.android.gpuimage.sample.filter

import android.opengl.GLES20
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.co.cyberagent.android.gpuimage.sample.utils.ShaderUtil

class GammaFilter(private var gamma: Float = 1.2f) : GPUImageFilter(
    NO_FILTER_VERTEX_SHADER,
    ShaderUtil.loadShaderFromAssets("shader/gamma_fs.glsl")
) {
    private var gammaLoc: Int = -1
    override fun onInit() {
        super.onInit()
        gammaLoc = GLES20.glGetUniformLocation(program, "uGamma")
        setGamma(gamma)
    }

    fun setGamma(value: Float) {
        gamma = value
        setFloat(gammaLoc, gamma)
    }
}