package com.golda.fitapp.ui.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet

class AnimatedTextView(context: Context, attr: AttributeSet) :
    androidx.appcompat.widget.AppCompatTextView(context, attr) {
    private var duration = 1500L

    fun setAnimatedText(finalValue: Int, duration: Long) {
        this.duration = duration
        setAnimatedText(finalValue)
    }

    fun setAnimatedText(finalValue: Int) {
        val startVal = text.toString().toIntOrNull() ?: 0
        val valueAnimator = ValueAnimator.ofInt(startVal, finalValue)
        valueAnimator.duration = duration
        valueAnimator.addUpdateListener { animator ->
            this.text = animator.animatedValue.toString()
        }
        valueAnimator.start()
    }
}