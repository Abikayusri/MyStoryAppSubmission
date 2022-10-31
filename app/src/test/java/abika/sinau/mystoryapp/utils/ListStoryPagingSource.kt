package abika.sinau.mystoryapp.utils

import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState


/**
 * @author by Abika Chairul Yusri on 10/29/2022
 */
class ListStoryPagingSource : PagingSource<Int, LiveData<List<StoryListResponse>>>() {
    companion object {
        fun snapshot(items: List<StoryListResponse>): PagingData<StoryListResponse> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<StoryListResponse>>>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<StoryListResponse>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}