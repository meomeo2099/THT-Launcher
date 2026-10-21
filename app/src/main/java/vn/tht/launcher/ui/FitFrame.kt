package vn.tht.launcher.ui

import android.content.Context
import android.widget.FrameLayout

/**
 * Khung chứa một view con có kích thước tự nhiên cố định (cụm điều hoà, cụm quạt): nếu khung nhỏ
 * hơn nội dung thì thu nhỏ đều nội dung cho vừa (không bị cắt), luôn đặt ở giữa.
 */
class FitFrame(c: Context) : FrameLayout(c) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val unspecified = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        for (i in 0 until childCount) getChildAt(i).measure(unspecified, unspecified)
        setMeasuredDimension(w, h)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val w = r - l
        val h = b - t
        for (i in 0 until childCount) {
            val v = getChildAt(i)
            val cw = v.measuredWidth.coerceAtLeast(1)
            val ch = v.measuredHeight.coerceAtLeast(1)
            val left = (w - cw) / 2
            val top = (h - ch) / 2
            v.layout(left, top, left + cw, top + ch)
            val s = minOf(1f, (w - paddingLeft - paddingRight).toFloat() / cw, (h - paddingTop - paddingBottom).toFloat() / ch)
            v.pivotX = cw / 2f
            v.pivotY = ch / 2f
            v.scaleX = s
            v.scaleY = s
        }
    }
}
