package com.golda.fitapp.ui.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

class CustomMaterialCardView(context: Context, attributeSet: AttributeSet) :
    MaterialCardView(context, attributeSet) {
    private val startPosition = 1f
    private val endPosition = 25f

    init {
        cardElevation = startPosition
    }

    fun isChecked(isChecked: Boolean) {
        if (isChecked) {
            animateCard(startPosition, endPosition)
        } else {
            animateCard(endPosition, startPosition)
        }
    }

    private fun animateCard(initialValue: Float, finalValue: Float) {
        val valueAnimator = ValueAnimator.ofFloat(initialValue, finalValue)
        valueAnimator.duration = 2000
        valueAnimator.addUpdateListener { animator ->
            cardElevation = animator.animatedValue.toString().toFloat()
        }
        valueAnimator.start()
    }
}