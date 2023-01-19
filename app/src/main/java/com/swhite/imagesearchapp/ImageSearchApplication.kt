package com.swhite.imagesearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Required for dependency injection. Used to wrap the whole application.
@HiltAndroidApp
class ImageSearchApplication: Application() {
}