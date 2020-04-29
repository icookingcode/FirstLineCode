package com.guc.firstlinecode.customviews

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.guc.firstlinecode.R
import kotlinx.android.synthetic.main.layout_title.view.*

/**
 * Created by guc on 2020/4/28.
 * 描述：自定义标题栏
 */
class TitleLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs),
    View.OnClickListener {
    var onMoreClicked: ((View?) -> Unit)? = null

    var title: CharSequence = ""
        set(value) {
            titleText.setText(value)
            field = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_title, this)
        titleBack.setOnClickListener(this)
        titleMore.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.titleBack -> (context as Activity).finish()
            R.id.titleMore -> onMoreClicked?.let { it(v) }
        }
    }
}