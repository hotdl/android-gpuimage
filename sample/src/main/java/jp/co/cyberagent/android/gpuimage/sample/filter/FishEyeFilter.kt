package jp.co.cyberagent.android.gpuimage.sample.filter

import android.opengl.GLES20
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.co.cyberagent.android.gpuimage.sample.utils.ShaderUtil

class FishEyeFilter(private var intensity: Float = 1.0f) :
    GPUImageFilter(
        NO_FILTER_VERTEX_SHADER,
        ShaderUtil.loadShaderFromAssets("shader/fisheye_fs.glsl")
    ) {
    private var intensityLoc: Int = -1
    override fun onInit() {
        super.onInit()
        intensityLoc = GLES20.glGetUniformLocation(program, "uIntensity")
        setIntensity(intensity)
    }

    fun setIntensity(value: Float) {
        intensity = value
        setFloat(intensityLoc, intensity)
    }
}