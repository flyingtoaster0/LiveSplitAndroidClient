package com.flyingtoaster0.livesplitclient

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

fun getDownOutAnimation(sourceView: View, contentView: View) : AnimatorSet {
    val animatorDown = ObjectAnimator.ofFloat(sourceView, "translationY", 0f, contentView.height - sourceView.x)
    val animatorLeft = ObjectAnimator.ofFloat(sourceView, "translationX", 0f, -sourceView.width.toFloat())

    animatorDown.setDuration(250)
    animatorLeft.setDuration(250)
    animatorLeft.startDelay = 50

    val animatorSet = AnimatorSet()
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            sourceView.visibility = View.INVISIBLE;
        }

        override fun onAnimationStart(animation: Animator?) {
        }

    })
    animatorSet.playTogether(animatorDown, animatorLeft)

    return animatorSet
}

fun getUpInAnimation(sourceView: View, contentView: View) : AnimatorSet {
    val animatorDown = ObjectAnimator.ofFloat(sourceView, "translationY", 0f, contentView.height - sourceView.x)
    val animatorLeft = ObjectAnimator.ofFloat(sourceView, "translationX", 0f, -sourceView.width.toFloat())

    animatorDown.setDuration(250)
    animatorLeft.setDuration(250)
    animatorLeft.startDelay = 50

    val animatorSet = AnimatorSet()
    animatorSet.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
            sourceView.visibility = View.VISIBLE
        }

    })
    animatorSet.playTogether(animatorDown, animatorLeft)

    return animatorSet
}