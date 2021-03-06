package com.zo0okadev.carsdemo.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 5/12/20.
 * Have a nice day!
 */
public class BindingAdapters {

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.equals("")) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
