
import android.content.Context
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils

class FadeSlideAnim(private val context: Context) {

    fun applyAnimation(view: View, animIn: Int, animUp: Int, startOffset: Long) {
        // Load the animations
        val fadeInAnimation = AnimationUtils.loadAnimation(context, animIn)
        val slideUpAnimation = AnimationUtils.loadAnimation(context, animUp)

        // Set start offset for each animation
        fadeInAnimation.startOffset = startOffset
        slideUpAnimation.startOffset = startOffset

        // Create an AnimationSet to run both animations simultaneously
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(fadeInAnimation)
        animationSet.addAnimation(slideUpAnimation)

        // Apply the AnimationSet to the view
        view.startAnimation(animationSet)
    }
}