package jp.co.cyberagent.android.gpuimage.sample.adjuster

abstract class AbstractFilterAdjuster : IFilterAdjuster {

    override fun adjust(percentages: List<Float>) {
        percentages.forEachIndexed { index, percentage ->
            adjust(index, percentage)
        }
    }

    fun linear(percentage: Float, start: Float, end: Float): Float {
        return (end - start) * percentage + start
    }
}