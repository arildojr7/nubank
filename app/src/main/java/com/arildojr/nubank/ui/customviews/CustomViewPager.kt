package com.arildojr.nubank.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import androidx.annotation.Nullable
import androidx.core.view.VelocityTrackerCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.ViewPager
import com.arildojr.nubank.R
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CustomViewPager : ViewPager, View.OnTouchListener {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    private companion object Params {
        const val STIFFNESS = SpringForce.STIFFNESS_LOW * 3
        const val DAMPING_RATIO = SpringForce.DAMPING_RATIO_LOW_BOUNCY
    }

    private var topGuideline = 0
    private var footerGuideline = 0
    private var lastY = 0F

    val position: MutableLiveData<Int> = MutableLiveData()

    private var downRawX = 0F
    private var downRawY = 0F
    private var dX = 0F
    private var dY = 0F

    private var velocityX = 0F
    private var velocityY = 0F

    private var yAnimation: SpringAnimation? = null
    private var mVelocityTracker: VelocityTracker? = null

    private var lockX = false
    private var lockY = false
    private var first = true

    fun init(
        context: Context,
        topGuideline: Int,
        footerGuideline: Int
    ) {
        this.topGuideline = topGuideline
        this.footerGuideline = footerGuideline
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.custom_float_view, this)

        view.setOnTouchListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain()
                } else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker?.clear()
                }
                mVelocityTracker?.addMovement(motionEvent)

                downRawX = motionEvent.rawX
                downRawY = motionEvent.rawY
                dX = view.x - downRawX
                dY = view.y - downRawY

                return true
            }

            MotionEvent.ACTION_MOVE -> {
                if (first) {
                    first = false

                    mVelocityTracker?.addMovement(motionEvent)
                    mVelocityTracker?.computeCurrentVelocity(1000)

                    velocityX = VelocityTrackerCompat.getXVelocity(
                        mVelocityTracker,
                        motionEvent.getPointerId(motionEvent.actionIndex)
                    )
                    velocityY = VelocityTrackerCompat.getYVelocity(
                        mVelocityTracker,
                        motionEvent.getPointerId(motionEvent.actionIndex)
                    )

                    if (abs(velocityY) > abs(velocityX)) {
                        lockX = true
                    } else if (abs(velocityY) < abs(velocityX)) {
                        lockY = true
                    }
                }

                if (lockX) {
                    lastY = motionEvent.y
                    var newY = motionEvent.rawY + dY

                    newY = max(topGuideline.toFloat(), newY) // limit top position
                    newY = min((footerGuideline).toFloat(), newY) // limit bottom position

                    view.animate()
                        .y(newY)
                        .setDuration(0)
                        .start()

                    position.value =
                        calculateOpacity(
                            newY.toDouble(),
                            top.toDouble(),
                            footerGuideline.toDouble()
                        )

                    return true
                } else if (!lockY) {
                    view.animate()
                        .y(y)
                        .setDuration(0)
                        .start()
                    return true
                }

                return false
            }

            MotionEvent.ACTION_UP -> {
                if (!lockY) {
                    yAnimation = if (velocityY < 0) {
                        createSpringAnimation(
                            view, SpringAnimation.Y, top.toFloat(), STIFFNESS, DAMPING_RATIO
                        )
                    } else {
                        createSpringAnimation(
                            view,
                            SpringAnimation.Y,
                            footerGuideline.toFloat(),
                            STIFFNESS,
                            DAMPING_RATIO
                        )
                    }
                }

                yAnimation?.addUpdateListener { _, viewPosition, _ ->
                    position.value = calculateOpacity(
                        viewPosition.toDouble(),
                        top.toDouble(),
                        footerGuideline.toDouble()
                    )
                }?.start()
                yAnimation = null

                lockX = false
                lockY = false
                first = true

                return false
            }
            MotionEvent.ACTION_CANCEL -> {
                mVelocityTracker?.recycle()
                return true
            }
            else -> return super.onTouchEvent(motionEvent)
        }
    }

    private fun calculateOpacity(position: Double, top: Double, bottom: Double): Int {
        val value = (position - top) / (bottom - top) * 100
        return max(0.0, min(100.0, value)).toInt()
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