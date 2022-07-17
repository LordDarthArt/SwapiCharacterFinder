package tk.lorddarthart.swapicharacterfinder.util.helper

import android.graphics.Rect
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import tk.lorddarthart.swapicharacterfinder.BuildConfig
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.App
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Boolean that checks if current build is DEBUG build
 */
val isDebug = BuildConfig.DEBUG

object UtilFunctions {

    /**
     * Boolean that checks that preferred view is displayed to user
     */
    fun isDisplayedOnScreen(view: View?): Boolean {
        if (view == null) {
            return false
        }
        if (!view.isShown) {
            return false
        }
        val actualPosition = Rect()
        view.getGlobalVisibleRect(actualPosition)
        val screen = Rect(
            0,
            0,
            view.context.resources.displayMetrics.widthPixels,
            view.context.resources.displayMetrics.heightPixels
        )
        return actualPosition.intersect(screen)
    }

    fun animatedImgLoad(urlString: String?, imageView: ImageView) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.P) {
            val animPlaceholderPiePlus = ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_preload
            ) as AnimatedImageDrawable
            animPlaceholderPiePlus.start()
            Glide.with(imageView.context).load(urlString)
                .placeholder(animPlaceholderPiePlus)
                .error(R.drawable.ic_no_image_available)
                .into(imageView)
        } else {
            val animPlaceholderOreoMinus = ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_preload
            ) as AnimationDrawable
            animPlaceholderOreoMinus.start()
            Glide.with(imageView.context).load(urlString)
                .placeholder(animPlaceholderOreoMinus)
                .error(R.drawable.ic_no_image_available)
                .into(imageView)
        }
    }

    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).replace(",", ".").toDouble()
    }
}