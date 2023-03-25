package com.example.notepadapplication.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.notepadapplication.R
import com.example.notepadapplication.utils.dip
import kotlin.math.roundToInt

class AvatarImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAtr) {
    private val clipPath: Path = Path()
    private var drawableIcon: Drawable? = null
    private var text: String? = null
    private val textPaint: TextPaint by lazy {
        TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = 24f * resources.displayMetrics.scaledDensity
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            color = Color.TRANSPARENT
            alpha = 102
        }
    }
    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, R.color.accentSecondary)
        }
    }
    var shape = Shapes.CIRCLE
    var cornerRadius = dip(25).toFloat()
    var rectF: RectF = RectF()

    init {
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AvatarImageView,
            0, 0
        )
        try {
            val avatarShare: Int = a.getInt(R.styleable.AvatarImageView_avatar_shape, 0)
            shape = when (avatarShare) {
                1 -> Shapes.RECTANGLE
                else -> Shapes.CIRCLE
            }
        } finally {
            a.recycle()
        }
    }

    fun setImage(imagePath: String?, text: String) {
        this.text = text
        paint.color = Color.TRANSPARENT
        setDrawable()
        if (!imagePath.isNullOrEmpty()) {
            Glide.with(context)
                .load(imagePath)
                .placeholder(drawableIcon)
                .centerCrop()
                .into(this)
        } else {
            setImageDrawable(drawableIcon)
            invalidate()
        }
    }

    fun setDrawable() {
        drawableIcon = object : Drawable() {
            override fun draw(canvas: Canvas) {
                val centerX = (bounds.width() * 0.5).roundToInt()
                val centerY = (bounds.height() * 0.5).roundToInt()

                if (text != null) {
                    val textWidth: Float = textPaint.measureText(text) * 0.5f
                    val textBaseLineHeight: Float = textPaint.fontMetrics.ascent * -0.4f

                    if (shape == Shapes.RECTANGLE) {
                        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint)
                    } else {
                        canvas.drawCircle(
                            centerX.toFloat(),
                            centerY.toFloat(),
                            (bounds.height() / 2f).coerceAtLeast(textWidth / 2f),
                            paint
                        )
                    }

                    canvas.drawText(
                        text.orEmpty(),
                        centerX - textWidth,
                        centerY + textBaseLineHeight,
                        textPaint
                    )
                }
            }

            override fun setAlpha(alpha: Int) {}
            override fun setColorFilter(cf: ColorFilter?) {}
            override fun getOpacity(): Int = PixelFormat.UNKNOWN
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val screenWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        val screenHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        rectF.set(0f, 0f, screenWidth, screenHeight)
    }

    override fun onDraw(canvas: Canvas) {
        if (shape == Shapes.RECTANGLE) {
            clipPath.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)
        } else {
            clipPath.addCircle(
                rectF.centerX(),
                rectF.centerY(),
                rectF.height() / 2,
                Path.Direction.CW
            )
        }
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }

    enum class Shapes {
        CIRCLE,
        RECTANGLE
    }
}