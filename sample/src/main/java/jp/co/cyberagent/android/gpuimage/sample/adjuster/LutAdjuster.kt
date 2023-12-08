package jp.co.cyberagent.android.gpuimage.sample.adjuster

import jp.co.cyberagent.android.gpuimage.sample.filter.LutFilter

class LutAdjuster(private val filter: LutFilter) : AbstractFilterAdjuster() {
    override fun adjust(index: Int, percentage: Float) {
        when (index) {
            0 -> filter.setIntensity(linear(percentage, 0.0f, 1.0f))
        }
    }

    override fun params(): List<AdjusterParam> = listOf(AdjusterParam("Intensity", 100))
}