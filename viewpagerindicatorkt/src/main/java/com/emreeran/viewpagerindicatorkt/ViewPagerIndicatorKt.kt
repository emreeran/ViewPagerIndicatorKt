/*
 * Copyright (C) 2018 Emre Eran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emreeran.viewpagerindicatorkt

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import java.util.*

/**
 * Created by Emre Eran on 22.09.2018.
 */
class ViewPagerIndicatorKt constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
        LinearLayout(context, attrs, defStyleAttr) {

    @Suppress("unused") // Public API
    var viewPager: ViewPager?
        set(value) {
            _viewPager = value
            initialize()
        }
        get() = _viewPager

    @Suppress("unused") // Public API
    var selectedItem
        set(value) {
            _selectedItem = value
            changeSelection(value)
        }
        get() = _selectedItem

    @Suppress("unused") // Public API
    var itemHeight
        get() = _itemHeight
        set(value) {
            _itemHeight = value
            _viewPager?.run { initialize() }
        }

    @Suppress("unused") // Public API
    var itemWidth
        get() = _itemWidth
        set(value) {
            _itemWidth = value
            _viewPager?.run { initialize() }
        }

    @Suppress("unused") // Public API
    var itemMargin
        get() = _itemMargin
        set(value) {
            _itemMargin = value
            _viewPager?.run { initialize() }
        }

    @Suppress("unused") // Public API
    var isRtl
        get() = _isRtl
        set(value) {
            _isRtl = value
            _viewPager?.run { initialize() }
        }

    private var _viewPager: ViewPager? = null
    private var _itemHeight: Float = 0f
    private var _itemWidth: Float = 0f
    private var _itemMargin: Float = 0f
    private var _isRtl = false
    private var _selectedItem = 0

    private lateinit var viewArray: ArrayList<ImageView>
    private lateinit var defaultDrawable: Drawable
    private lateinit var selectedDrawable: Drawable

    constructor(context: Context?) : this(context, null, 0)
    constructor(context: Context?, attrs: AttributeSet) : this(context, attrs, 0)

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        context?.let { it ->
            val typedArray = it.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicatorKt, defStyleAttr, 0)
            _itemHeight = typedArray.getDimension(R.styleable.ViewPagerIndicatorKt_item_height, 0f)
            _itemWidth = typedArray.getDimension(R.styleable.ViewPagerIndicatorKt_item_width, 0f)
            _itemMargin = typedArray.getDimension(R.styleable.ViewPagerIndicatorKt_item_margin, 0f)
            _isRtl = typedArray.getBoolean(R.styleable.ViewPagerIndicatorKt_rtl, false)
            typedArray.getDrawable(R.styleable.ViewPagerIndicatorKt_default_drawable)?.let { drawable ->
                defaultDrawable = drawable
            }
            typedArray.getDrawable(R.styleable.ViewPagerIndicatorKt_selected_drawable)?.let { drawable ->
                selectedDrawable = drawable
            }

            typedArray.recycle()
        }
    }

    @Suppress("unused") // Public API
    fun setDrawables(defaultDrawable: Drawable, selectedDrawable: Drawable) {
        this.defaultDrawable = defaultDrawable
        this.selectedDrawable = selectedDrawable
    }

    @Suppress("unused") // Public API
    fun onResume() {
        _viewPager?.let {
            val position = it.currentItem
            changeSelection(position)
        }
    }

    private fun changeSelection(pos: Int) {
        var position = pos
        if (viewArray.size > position) {
            if (_isRtl) {
                position = viewArray.size - position - 1
            }

            val previousView = viewArray[_selectedItem]
            previousView.setImageDrawable(defaultDrawable)
            val view = viewArray[position]
            view.setImageDrawable(selectedDrawable)
            _selectedItem = position
        }
    }

    private fun initialize() {
        _viewPager?.let {
            removeAllViews()
            viewArray = ArrayList()
            val itemCount = it.adapter!!.count
            weightSum = itemCount.toFloat()

            val itemLayoutParams: LinearLayout.LayoutParams

            if (_itemHeight != 0f && _itemWidth != 0f) {
                itemLayoutParams = LinearLayout.LayoutParams(_itemWidth.toInt(), _itemHeight.toInt())
                itemLayoutParams.setMargins(0, 0, _itemMargin.toInt(), 0)
            } else {
                itemLayoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f)
                itemLayoutParams.setMargins(5, 0, 5, 0)
            }

            for (i in 0 until itemCount) {
                val view = ImageView(context)
                view.layoutParams = itemLayoutParams
                view.scaleType = ImageView.ScaleType.CENTER_INSIDE
                view.setImageDrawable(defaultDrawable)

                view.setOnClickListener { _ -> it.setCurrentItem(i, true) }
                viewArray.add(view)
                addView(view)
            }

            onResume()

            it.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    changeSelection(position)
                }
            })
        }
    }
}