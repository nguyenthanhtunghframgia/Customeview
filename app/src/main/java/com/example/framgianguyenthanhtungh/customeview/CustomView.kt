package com.example.framgianguyenthanhtungh.customeview

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class CustomView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var drawable: Int = 0
    private var radius = 10
    private var rotate = 0
    private var pX = 0
    private var pY = 0
    private var pAlpha = 0
    private var bitmap: Bitmap? = null

    init {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomView, 0, 0).apply {
            try {
                drawable = getResourceId(R.styleable.CustomView_drawable, 0)
                if (drawable != 0) {
                    bitmap = BitmapFactory.decodeResource(context.resources, drawable)
                }
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        //
        val animation = ValueAnimator.ofFloat(0F, 100F)
        animation.duration = 1000L //one second
        animation.addUpdateListener {
            ValueAnimator.AnimatorUpdateListener { valueAnimator ->
            }
        }
        animation.start()

        //
        paint.color = Color.parseColor("#583248")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5F

        val viewWidth = width / 2
        val viewHeight = height / 2

        val leftTopX = viewWidth - 150
        val leftTopY = viewHeight - 150

        val rightBotX = viewWidth + 150
        val rightBotY = viewHeight + 150

        //Rotate and draw
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.rotate(rotate.toFloat(), viewWidth.toFloat(), viewHeight.toFloat())

            canvas?.drawRoundRect(
                leftTopX.toFloat(),
                leftTopY.toFloat(),
                rightBotX.toFloat(),
                rightBotY.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                paint
            )
        }

        //
        canvas?.drawCircle(50F, 200F, 30F, paint)
        canvas?.drawCircle(150F, 100F, 30F, paint)
        canvas?.drawCircle(300F, 150F, 30F, paint)
        canvas?.drawLine(80F, 200F, 120F, 100F, paint)
        canvas?.drawLine(180F, 100F, 270F, 150F, paint)
    }

    fun anim() {
        val propertyRadius = PropertyValuesHolder.ofInt(PROPERTY_RADIUS, 0, 150)
        val propertyRotate = PropertyValuesHolder.ofInt(PROPERTY_ROTATE, 0, 360)

        val animator = ValueAnimator()
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.setValues(propertyRadius, propertyRotate)
        animator.duration = 2000
        animator.addUpdateListener { animation ->
            radius = animation.getAnimatedValue(PROPERTY_RADIUS) as Int
            rotate = animation.getAnimatedValue(PROPERTY_ROTATE) as Int
            invalidate()
        }
        animator.start()

        //
//        val animatorSp = ValueAnimator.ofInt(0, 100)
//        animatorSp.duration = 2000
//        animatorSp.addUpdateListener { animation ->
//            val value = animation.animatedValue as Int
//        }
//        animatorSp.start()
    }

    private fun createAnimator(): ValueAnimator {
        val propertyX = PropertyValuesHolder.ofInt(PROPERTY_X, 100, 300)
        val propertyY = PropertyValuesHolder.ofInt(PROPERTY_Y, 100, 300)
        val propertyAlpha = PropertyValuesHolder.ofInt(PROPERTY_ALPHA, 0, 255)

        val animator = ValueAnimator()
        animator.setValues(propertyX, propertyY, propertyAlpha)
        animator.duration = 2000
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addUpdateListener { animation ->
            pX = animation.getAnimatedValue(PROPERTY_X) as Int
            pY = animation.getAnimatedValue(PROPERTY_Y) as Int
            pAlpha = animation.getAnimatedValue(PROPERTY_ALPHA) as Int
        }

        return animator
    }
}
