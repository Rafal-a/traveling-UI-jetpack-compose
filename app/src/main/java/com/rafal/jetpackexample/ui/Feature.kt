package com.rafal.jetpackexample.ui

import androidx.annotation.DrawableRes

data class Feature(
    @DrawableRes val photoId:Int,
    val location:String,
    val rating: Double,
)
