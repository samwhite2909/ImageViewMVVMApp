package com.swhite.imagesearchapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Model for a photo and all of it's meta-data.
//All set to parcelable so that the photos can be passed between different classes.
@Parcelize
data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    //Model for holding all of the different image sizes via URL for each image.
    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ) : Parcelable

    //Model for each user with the meta-data for naming and linking the image resource via URL.
    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ) : Parcelable {
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }

}