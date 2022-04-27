package com.example.sampleui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sampleui.BR
import com.example.sampleui.R
import com.example.sampleui.R.color.orange_red
import com.example.sampleui.RvClickListener
import com.example.sampleui.utils.Helper


/**
 *SampleUI
 *@author Anjali Yadav
 *@date 06/04/2022 12:54 AM
 */
object BindAdapters {

    @JvmStatic
    @BindingAdapter("layout", "list", "click")
    fun <T> setRecyclerView(
        view: RecyclerView,
        layout: Int,
        list: List<T>?,
        click: RvClickListener,
    ) {
        try {
            if (list != null) {
                val adapter = GlobalAdapter(
                    layout,
                    list.toMutableList(),
                    BR.model,
                    clickListener = click,
                    mapOf(BR.itemclick to click)
                )
                view.adapter = adapter
            }
        } catch (e: Exception) {
            Helper.print("${e.message}")
        }
    }

    @JvmStatic
    @BindingAdapter("sno")
    fun setSno(
        view: TextView,
        count: String?,
    ) {
        val temp: Int = (count?.toInt() ?: 0) + 1
        view.text = temp.toString()
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setSrc(
        view: ImageView,
        url: String?,
    ) {

        val options: RequestOptions = RequestOptions()
            .error(R.drawable.ic_error)
            .fitCenter()
            .centerCrop()

        view.scaleType = ImageView.ScaleType.FIT_CENTER
        Glide.with(view.context)
            .load(url)
            .apply(options)
            .into(view)
    }


    @JvmStatic
    @BindingAdapter("promoted")
    fun setPromoted(
        view: TextView,
        isPromoted: Boolean,
    ) {
        if (isPromoted) {
            view.setBackgroundResource(R.drawable.gray_rect)
            view.setText(R.string.promoted)
        }
    }

    @JvmStatic
    @BindingAdapter("bookmarked")
    fun setBookmark(
        view: ImageView,
        isBookmarked: Boolean,
    ) {
        view.setBackgroundResource(R.drawable.white_circle)
        if (isBookmarked) {
            view.setImageResource(R.drawable.ic_color_bookmark)
        } else {
            view.setImageResource(R.drawable.ic_bookmark_border)
        }
    }

    @JvmStatic
    @BindingAdapter("offers")
    fun setOffers(
        view: TextView,
        data: String?,
    ) {
        if (data != null && data != "") {
            view.text = data
            view.setBackgroundResource(R.drawable.blue_rect)
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_offers,
                0,
                0,
                0)
            view.setTextColor(Color.WHITE)
        }
    }

    @JvmStatic
    @BindingAdapter("ratingBG")
    fun setBackgroundRating(
        view: TextView,
        rating: String,
    ) {
        view.text = rating
        val rate = rating.toDouble()
        if (rate >= 3.5) {
            view.setBackgroundResource(R.drawable.green_rect)
        } else if (rate > 2.5 && rate < 3.5) {
            view.setBackgroundResource(R.drawable.yellow_rect)
        } else if (rate > 0 && rate < 2.5) {
            view.setBackgroundResource(R.drawable.red_rect)
        } else {
            view.setBackgroundResource(R.drawable.gray_rect)
        }

    }

    @JvmStatic
    @BindingAdapter("rates", "currency")
    fun setRates(
        view: TextView,
        rates: String,
        currency: String? = "AED",
    ) {
        val display = "$rates $currency"
        view.text = display
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("amount")
    fun setAmount(
        view: TextView,
        amount: Double,
    ) {

        val temp = "%,.2f".format(amount)
        view.text = "$temp AED"
    }


}