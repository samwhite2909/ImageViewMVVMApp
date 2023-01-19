package com.swhite.imagesearchapp.api

import com.swhite.imagesearchapp.data.UnsplashPhoto

//Handles the response which contains the Unsplash photo objects to be displayed.
data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)
