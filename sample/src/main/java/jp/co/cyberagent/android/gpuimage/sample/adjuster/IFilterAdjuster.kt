package jp.co.cyberagent.android.gpuimage.sample.adjuster

interface IFilterAdjuster {
    fun adjust(percentages: List<Float>)

    fun adjust(index: Int, percentage: Float)

    fun params(): List<AdjusterParam>
}