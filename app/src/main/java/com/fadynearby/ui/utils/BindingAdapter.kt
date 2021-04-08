package com.fadynearby.ui.utils

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.ShimmerFrameLayout
import com.fadynearby.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setNetworkImage")
    fun setNetworkImage(view: ImageView, image: String?) {
        if (image == null) return
        val options = RequestOptions()
            .override(650, 375)
            .placeholder(view.context.glidePlaceHolder())

        Glide.with(view.context)
            .load(image)
            .apply(options)
            .into(view)
    }



    @JvmStatic
    @BindingAdapter("setCircleNetworkImage")
    fun setCircleNetworkImage(view: ImageView, image: String?) {
        if (image == null) return
        Glide.with(view.context).load(image).apply(RequestOptions.circleCropTransform()).into(view)
    }

    @JvmStatic
    @BindingAdapter("setCircleProfileImage")
    fun setCircleProfileImage(view: ImageView, image: String?) {
        if (image == null) return
        Glide.with(view.context).load(image).apply(RequestOptions.circleCropTransform()).into(view)
    }


    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, image: Int?) {
        if (image == null) return
        Glide.with(view.context).load(image).apply(RequestOptions.circleCropTransform()).into(view)
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("invisibleGone")
    fun invisibleGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }


    @JvmStatic
    @BindingAdapter("startShimmer")
    fun startShimmer(view: ShimmerFrameLayout, start: Boolean) {
        if (start) view.startShimmer() else view.stopShimmer()
    }

    @JvmStatic
    @BindingAdapter("htmlText")
    fun htmlText(view: TextView, text: String?) {
        if (text == null)
            return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            view.text = Html.fromHtml(text)
        }
    }

    @JvmStatic
    @BindingAdapter("underLine")
    fun underLine(view: TextView, b: Boolean) {
        if (b)
            view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    @JvmStatic
    @BindingAdapter("middleLine")
    fun middleLine(view: TextView, b: Boolean) {
        if (b)
            view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)
    fun Context.glidePlaceHolder(): CircularProgressDrawable {
        return CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            setColorSchemeColors(getColorCompat(R.color.colorPrimary))
            start()
        }
    }




}