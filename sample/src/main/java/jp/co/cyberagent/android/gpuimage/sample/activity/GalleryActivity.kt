/*
 * Copyright (C) 2018 CyberAgent, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.cyberagent.android.gpuimage.sample.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.co.cyberagent.android.gpuimage.GPUImageView
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.co.cyberagent.android.gpuimage.sample.R
import jp.co.cyberagent.android.gpuimage.sample.utils.FilterAdjuster
import jp.co.cyberagent.android.gpuimage.sample.utils.GPUImageFilterHelper
import jp.co.cyberagent.android.gpuimage.sample.widget.ParamSeekBar

class GalleryActivity : AppCompatActivity() {

    private var filterAdjuster: FilterAdjuster? = null
    private val gpuImageView: GPUImageView by lazy { findViewById<GPUImageView>(R.id.gpuimage) }
    private val seekBarCont: ViewGroup by lazy { findViewById<ViewGroup>(R.id.seek_bar_cont) }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

//        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                filterAdjuster?.adjust(progress)
//                gpuImageView.requestRender()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//        })

        findViewById<View>(R.id.button_choose_filter).setOnClickListener {
            GPUImageFilterHelper.showDialog(this) { filter ->
                switchFilterTo(filter)
                gpuImageView.requestRender()
            }
        }
        findViewById<View>(R.id.button_save).setOnClickListener { saveImage() }

        startPhotoPicker()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_PICK_IMAGE -> if (resultCode == RESULT_OK) {
                gpuImageView.setImage(data!!.data)
            } else {
                finish()
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun startPhotoPicker() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE)
    }

    private fun saveImage() {
        val fileName = System.currentTimeMillis().toString() + ".jpg"
        gpuImageView.saveToPictures("GPUImage", fileName) { uri ->
            Toast.makeText(this, "Saved: " + uri.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun switchFilterTo(filter: GPUImageFilter) {
        if (gpuImageView.filter == null || gpuImageView.filter.javaClass != filter.javaClass) {
            gpuImageView.filter = filter
            filterAdjuster = FilterAdjuster(filter)
            if (filterAdjuster?.params() != null) {
                seekBarCont.visibility = View.VISIBLE
                seekBarCont.removeAllViews()
                val adjusterParams = filterAdjuster?.params()!!
                val paramSeekBars = adjusterParams.mapIndexed { index, adjusterParam ->
                    ParamSeekBar(seekBarCont).also {
                        seekBarCont.addView(it.root)
                        it.tvName.text = adjusterParam.name
                        it.seekBar.progress = adjusterParam.defaultProgress
                        it.seekBar.setOnSeekBarChangeListener(object :
                            SeekBar.OnSeekBarChangeListener {
                            override fun onProgressChanged(
                                seekBar: SeekBar?,
                                progress: Int,
                                fromUser: Boolean
                            ) {
                                filterAdjuster?.adjust(index, progress / 100.0f)
                                gpuImageView.requestRender()
                            }

                            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                        })
                    }
                }
                filterAdjuster!!.adjust(paramSeekBars.map { it.seekBar.progress / 100.0f })
            } else {
                seekBarCont.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val REQUEST_PICK_IMAGE = 1
    }
}
