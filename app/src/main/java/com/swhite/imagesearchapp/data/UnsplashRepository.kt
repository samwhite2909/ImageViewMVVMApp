package com.swhite.imagesearchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.swhite.imagesearchapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

//Layer between the view model and API to handle non-UI logic away from UI
// and view model classes but allows for interaction between them.
@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {

    fun getSearchResults(query: String) =
        Pager(
            //Sets the configuration for pagination.
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            //Sets the source for getting the data to display.
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
        ).liveData //Turns it into a stream of live updating paging data.
}