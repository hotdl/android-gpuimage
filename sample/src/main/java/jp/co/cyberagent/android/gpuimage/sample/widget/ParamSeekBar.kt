package jp.co.cyberagent.android.gpuimage.sample.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import jp.co.cyberagent.android.gpuimage.sample.R

class ParamSeekBar(parent: ViewGroup) {

    private var view: View

    init {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_seekbar, parent, false)
    }

    val root: View get() = view
    val tvName: TextView by lazy { root.findViewById<TextView>(R.id.tv_name) }
    val seekBar: SeekBar by lazy { root.findViewById<SeekBar>(R.id.seekBar) }
}