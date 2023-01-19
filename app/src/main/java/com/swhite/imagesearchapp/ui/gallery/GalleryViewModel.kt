package com.swhite.imagesearchapp.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.swhite.imagesearchapp.data.UnsplashRepository

//View model for the gallery fragment.
class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted state: SavedStateHandle
    ) : ViewModel() {

        //Gets the data for the current query, falls back on the default query.
        private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

        //Assigns the photos from the value assigned as the search term, which will either be the
        //default value or the last cached search term performed by the user.
        val photos = currentQuery.switchMap { queryString ->
            repository.getSearchResults(queryString).cachedIn(viewModelScope)
        }

        //Sets search term to the user string entered.
        fun searchPhotos(query: String) {
            currentQuery.value = query
        }

    //Constants used in the view model.
    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "dogs"
    }

}