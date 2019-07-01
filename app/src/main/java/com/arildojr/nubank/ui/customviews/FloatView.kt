package com.arildojr.nubank.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.Nullable
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.lifecycle.MutableLiveData
import com.arildojr.nubank.R

class FloatView : FrameLayout, View.OnTouchListener {
    private var topGuideline = 0
    private var footerGuideline = 0
    private var lastY = 0F
    val position: MutableLiveData<Int> = MutableLiveData()

    private companion object Params {
        const val STIFFNESS = SpringForce.STIFFNESS_LOW
        const val DAMPING_RATIO = SpringForce.DAMPING_RATIO_LOW_BOUNCY
    }

    private var downRawX = 0F
    private var downRawY = 0F
    private var dX = 0F
    private var dY = 0F

    private lateinit var yAnimation: SpringAnimation

    constructor(context: Context) : super(context)

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, @Nullable attrs: AttributeSet, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun init(
        context: Context,
        attributeSet: AttributeSet?,
        topGuideline: Int,
        footerGuideline: Int
    ) {
        this.topGuideline = topGuideline
        this.footerGuideline = footerGuideline - 130
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.custom_float_view, this)

        view.setOnTouchListener(this)
    }

    private fun calculateOpacity(position: Double, top: Double, bottom: Double): Int {
        val value = (position - top) / (bottom - top) * 100
        return value.toInt()
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downRawX = motionEvent.rawX
                downRawY = motionEvent.rawY
                dX = view.x - downRawX
                dY = view.y - downRawY

                return true
            }

            MotionEvent.ACTION_MOVE -> {
                lastY = motionEvent.y
                val newY = motionEvent.rawY + dY

                position.value =
                    calculateOpacity(newY.toDouble(), top.toDouble(), footerGuideline.toDouble())

                view.animate()
                    .y(newY)
                    .setDuration(0)
                    .start()

                return true
            }

            MotionEvent.ACTION_UP -> {
                if (lastY < motionEvent.y) {
                    position.value =
                        calculateOpacity(top.toDouble(), top.toDouble(), footerGuideline.toDouble())
                    yAnimation = createSpringAnimation(
                        view, SpringAnimation.Y, top.toFloat(), STIFFNESS, DAMPING_RATIO
                    )
                } else {
                    position.value = calculateOpacity(
                        footerGuideline.toDouble(),
                        top.toDouble(),
                        footerGuideline.toDouble()
                    )

                    yAnimation = createSpringAnimation(
                        view, SpringAnimation.Y, footerGuideline.toFloat(), STIFFNESS, DAMPING_RATIO
                    )
                }
                yAnimation.start()

                return false
            }
            else -> return super.onTouchEvent(motionEvent)
        }
    }

    private fun createSpringAnimation(
        view: View,
        property: DynamicAnimation.ViewProperty,
        finalPosition: Float,
        stiffness: Float,
        dampingRatio: Float
    ): SpringAnimation {
        val animation = SpringAnimation(view, property)
        val spring = SpringForce(finalPosition)
        spring.stiffness = stiffness
        spring.dampingRatio = dampingRatio
        animation.spring = spring
        return animation
    }
}