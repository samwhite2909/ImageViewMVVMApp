package com.swhite.imagesearchapp.data

import androidx.paging.PagingSource
import com.swhite.imagesearchapp.api.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

//Starting page index.
private const val UNSPLASH_STARTING_PAGE_INDEX = 1

//Handles the logic for searching for photos based on a provided search time and the pagination.
class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        //Try and use the current page, if not, e.g. first search, use the first page.
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            //Searches for photos and gets the results.
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            //Loads the data in using the returned result and handles the previous and next pages.
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}