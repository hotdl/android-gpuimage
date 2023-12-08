package jp.co.cyberagent.android.gpuimage.sample.adjuster

import jp.co.cyberagent.android.gpuimage.sample.filter.GammaFilter

class GammaAdjuster(private val filter: GammaFilter) : AbstractFilterAdjuster() {
    override fun adjust(index: Int, percentage: Float) {
        when (index) {
            0 -> filter.setGamma(linear(percentage, 0.0f, 3.0f))
        }
    }

    override fun params(): List<AdjusterParam> = listOf(AdjusterParam("Gamma", 40))
}