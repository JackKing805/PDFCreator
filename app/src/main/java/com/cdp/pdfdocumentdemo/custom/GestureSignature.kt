package com.cdp.pdfdocumentdemo.custom



import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.cdp.pdfdocumentdemo.R
import jerry.build.pdfcreator.utils.FileUtils
import java.io.FileOutputStream
import java.io.OutputStream


class GestureSignature(context: Context, private val attrs: AttributeSet?) : View(
    context,
    attrs,
    0
),View.OnTouchListener {
    private var w: Float = 0f
    private var h: Float = 0f

    private var paint: Paint?=null

    private var strokeWidth:Float?=0f

    private var cacheCanvas: Canvas? = null
    private var cacheBitmap: Bitmap? = null
    private var path: Path? = null


    init {
        initAttr()
        initPaint()
        setOnTouchListener(this)
    }

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        path = Path()
        paint = Paint()
        paint?.isDither = true
        paint?.isAntiAlias = true
        paint?.style = Paint.Style.STROKE
        paint?.strokeCap = Paint.Cap.ROUND
        paint?.strokeJoin = Paint.Join.ROUND
    }

    /**
     * 初始化参数配置
     */
    private fun initAttr() {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.GestureSignature)

        a.recycle()
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w.toFloat()
        this.h = h.toFloat()
        this.strokeWidth = w/40f

        cacheBitmap = Bitmap.createBitmap(
            (w - 2 * strokeWidth!!).toInt(),
            (h - 2 * strokeWidth!!).toInt(),
            Bitmap.Config.ARGB_8888
        )
        cacheCanvas = Canvas(cacheBitmap!!)
        cacheCanvas!!.drawColor(Color.TRANSPARENT)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, width / 2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawOutLine(canvas)
        drawPath(canvas)
    }

    private fun drawOutLine(canvas: Canvas?) {
        paint?.color = Color.parseColor("#1abc9c")
        paint?.strokeWidth = strokeWidth!!
        canvas!!.drawRect(
            strokeWidth!! / 2,
            strokeWidth!! / 2,
            w - strokeWidth!! / 2,
            h - strokeWidth!! / 2,
            paint!!
        )
    }

    private fun drawPath(canvas: Canvas?) {
        canvas?.save()
        canvas?.clipRect(
            strokeWidth!!,
            strokeWidth!!,
            w - strokeWidth!!,
            h - strokeWidth!!
        )

        paint?.color = Color.BLACK
        paint?.strokeWidth =strokeWidth!!/2
        canvas?.drawPath(path!!, paint!!)

        canvas?.restore()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)

        val touchX: Float = event!!.x
        val touchY: Float = event.y
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
//                path?.reset()
                path?.moveTo(touchX, touchY)
                invalidate()
                true
            }
            MotionEvent.ACTION_MOVE -> {
                path?.lineTo(touchX, touchY)
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                paint?.color = Color.BLACK
                paint?.strokeWidth = strokeWidth!! / 2
                cacheCanvas?.drawPath(path!!, paint!!)
                invalidate()
                val filePath = context.applicationContext.filesDir.absolutePath + "/img/sign.png"
                val file = FileUtils.createFile(filePath)
                val imageScale = imageScale(cacheBitmap!!, 200, 100)!!
                bitmap2Path(imageScale, file.absolutePath)
                true
            }
            else -> false
        }
    }
    private fun bitmap2Path(bitmap: Bitmap, path: String?): String? {
        try {
            val os: OutputStream = FileOutputStream(path)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
            os.flush()
            os.close()
        } catch (e: Exception) {
            Log.e("TAG", "", e)
        }
        return path
    }

    private fun imageScale(bitmap: Bitmap, dst_w: Int, dst_h: Int): Bitmap? {
        val src_w = bitmap.width
        val src_h = bitmap.height
        val scale_w = dst_w.toFloat() / src_w
        val scale_h = dst_h.toFloat() / src_h
        val matrix = Matrix()
        matrix.postScale(scale_w, scale_h)
        return Bitmap.createBitmap(
            bitmap, 0, 0, src_w, src_h, matrix,
            true
        )
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private fun Float.dip2px(): Int {
        val scale: Float = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private fun Float.px2dip(): Int {
        val scale: Float = resources.displayMetrics.density
        return (this / scale + 0.5f).toInt()
    }
}