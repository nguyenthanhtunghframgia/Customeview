package com.example.framgianguyenthanhtungh.customeview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomDiagram(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val paintInner: Paint = Paint()
    val paintOutta: Paint = Paint()
    val paintProgress: Paint = Paint()
    private var borderColor: Int
    private var isPercent: Boolean = false
    private var drawable: Int = 0
    private lateinit var bitmap: Bitmap

    init {
        context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomDiagram, 0, 0).apply {

            try {
                borderColor = getColor(R.styleable.CustomDiagram_border_color, Color.GRAY)
                isPercent = getBoolean(R.styleable.CustomDiagram_is_percent, false)
                drawable = getResourceId(R.styleable.CustomDiagram_drawable, 0)
                if (drawable != 0) {
                    bitmap = BitmapFactory.decodeResource(resources, drawable)
                }
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        //
        paintInner.isAntiAlias = true
        paintInner.style = Paint.Style.STROKE
        paintInner.strokeWidth = 20F
        paintInner.strokeCap = Paint.Cap.ROUND
        paintInner.color = borderColor
        paintInner.textSize = 100F
        paintInner.textAlign = Paint.Align.CENTER

        //
        paintOutta.isAntiAlias = true
        paintOutta.style = Paint.Style.STROKE
        paintOutta.strokeWidth = 20F
        paintOutta.strokeCap = Paint.Cap.ROUND
        paintOutta.color = borderColor
        paintOutta.textAlign = Paint.Align.CENTER
        //
        paintProgress.isAntiAlias = true
        paintProgress.style = Paint.Style.STROKE
        paintProgress.strokeWidth = 30F
        paintProgress.strokeCap = Paint.Cap.ROUND
        paintProgress.color = Color.RED
        paintProgress.textAlign = Paint.Align.CENTER

        //
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 180F, paintInner)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 200F, paintProgress)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 220F, paintOutta)
//        canvas?.drawBitmap(
//            bitmap,
//            (width - bitmap.width) / 2.toFloat(),
//            (height - bitmap.height) / 2.toFloat(),
//            paint
//        )

        //
        if (borderColor == resources.getColor(R.color.colorPrimary)) {
            canvas?.drawText("PRIMARY", (width / 2).toFloat(), 100F, paintInner)
        } else {
            canvas?.drawText("MAGNETA", (width / 2).toFloat(), 100F, paintInner)
        }
    }

    fun changeColor() {
        borderColor = if (paintInner.color == Color.MAGENTA) {
            resources.getColor(R.color.colorPrimary)
        } else {
            Color.MAGENTA
        }
        invalidate()
    }
}
