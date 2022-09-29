package abika.sinau.core.data.source.remote.paging

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.PagingSource
import androidx.paging.PagingState
import timber.log.Timber


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
class ListStoryPagingSource(
    private val query: StoryQuery,
    private val apiService: ApiService
) : PagingSource<Int, StoryListResponse>() {

    override fun getRefreshKey(state: PagingState<Int, StoryListResponse>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoryListResponse> {
        query.page = params.key.takeIf { it != 0 } ?: 1
        val currentPage = query.page

        return try {
            val response = apiService.getStoriesPagination(query.toMap())
            val result = response.body()?.loginResult

            Timber.e("Result: $result")
            Timber.d("Result: $result")

            LoadResult.Page(
                data = result!!,
                prevKey = null,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}