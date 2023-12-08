package jp.co.cyberagent.android.gpuimage.sample.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.co.cyberagent.android.gpuimage.sample.adjuster.AdjusterParam
import jp.co.cyberagent.android.gpuimage.sample.adjuster.FishEyeAdjuster
import jp.co.cyberagent.android.gpuimage.sample.adjuster.GammaAdjuster
import jp.co.cyberagent.android.gpuimage.sample.adjuster.IFilterAdjuster
import jp.co.cyberagent.android.gpuimage.sample.adjuster.LutAdjuster
import jp.co.cyberagent.android.gpuimage.sample.filter.FishEyeFilter
import jp.co.cyberagent.android.gpuimage.sample.filter.GammaFilter
import jp.co.cyberagent.android.gpuimage.sample.filter.LutFilter

object GPUImageFilterHelper {
    fun showDialog(
        context: Context,
        listener: (filter: GPUImageFilter) -> Unit
    ) {
        val names = listOf("Gamma", "FishEye", "LUT")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose a filter")
        builder.setItems(names.toTypedArray()) { _, item ->
            listener(createFilter(names[item]))
        }
        builder.create().show()
    }

    private fun createFilter(name: String): GPUImageFilter {
        return when (name) {
            "Gamma" -> GammaFilter()
            "FishEye" -> FishEyeFilter()
            "LUT" -> LutFilter().apply {
                bitmap =
                    BitmapFactory.decodeStream(AppUtil.app.assets.open("images/lut/kodakm532_filter.jpg"))
            }

            else -> GPUImageFilter()
        }
    }
}

class FilterAdjuster(filter: GPUImageFilter) {
    private var adjuster: IFilterAdjuster? = null

    init {
        adjuster = when (filter) {
            is GammaFilter -> GammaAdjuster(filter)
            is FishEyeFilter -> FishEyeAdjuster(filter)
            is LutFilter -> LutAdjuster(filter)
            else -> null
        }
    }

    fun params(): List<AdjusterParam>? {
        return adjuster?.params()
    }

    fun adjust(percentages: List<Float>) {
        adjuster?.adjust(percentages)
    }

    fun adjust(index: Int, percentage: Float) {
        adjuster?.adjust(index, percentage)
    }
}