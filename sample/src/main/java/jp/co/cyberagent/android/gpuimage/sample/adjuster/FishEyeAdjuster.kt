package jp.co.cyberagent.android.gpuimage.sample.adjuster

import jp.co.cyberagent.android.gpuimage.sample.filter.FishEyeFilter

class FishEyeAdjuster(private val filter: FishEyeFilter) : AbstractFilterAdjuster() {
    override fun adjust(index: Int, percentage: Float) {
        when (index) {
            0 -> filter.setIntensity(linear(percentage, -1.0f, 1.0f))
        }
    }

    override fun params(): List<AdjusterParam> = listOf(AdjusterParam("Intensity", 100))
}