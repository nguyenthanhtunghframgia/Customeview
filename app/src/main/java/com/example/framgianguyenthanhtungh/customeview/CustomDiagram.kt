package com.example.framgianguyenthanhtungh.customeview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomDiagram(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    val paint: Paint = Paint()
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
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10F
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = borderColor
        paint.textSize = 100F
        paint.textAlign = Paint.Align.CENTER

        //
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 200F, paint)
        canvas?.drawBitmap(
            bitmap,
            (width - bitmap.width) / 2.toFloat(),
            (height - bitmap.height) / 2.toFloat(),
            paint
        )

        //
        if (isPercent) {
            canvas?.drawText("Halo", (width / 2).toFloat(), 100F, paint)
        }
    }

    fun changeColor() {
        borderColor = if (paint.color == Color.MAGENTA) {
            resources.getColor(R.color.colorPrimary)
        } else {
            Color.MAGENTA
        }
        invalidate()
    }
}
