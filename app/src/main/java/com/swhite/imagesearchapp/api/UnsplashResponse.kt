package com.swhite.imagesearchapp.api

import com.swhite.imagesearchapp.data.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)
