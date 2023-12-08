package jp.co.cyberagent.android.gpuimage.sample.filter

import android.opengl.GLES20
import jp.co.cyberagent.android.gpuimage.filter.GPUImageTwoInputFilter
import jp.co.cyberagent.android.gpuimage.sample.utils.ShaderUtil

class LutFilter(private var intensity: Float = 1f) :
    GPUImageTwoInputFilter(ShaderUtil.loadShaderFromAssets("shader/lut_fs.glsl")) {
    private var intensityLoc: Int = -1
    override fun onInit() {
        super.onInit()
        intensityLoc = GLES20.glGetUniformLocation(program, "intensity")
        setIntensity(intensity)
    }

    fun setIntensity(value: Float) {
        intensity = value
        setFloat(intensityLoc, intensity)
    }
}