package abika.sinau.core.data.source.remote.paging

import abika.sinau.core.data.source.local.db.StoryDatabase
import abika.sinau.core.data.source.local.entity.RemoteKeysEntity
import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction


/**
 * @author by Abika Chairul Yusri on 10/28/2022
 */
@OptIn(ExperimentalPagingApi::class)
class ListStoryRemoteMediator(
    private val query: StoryQuery,
    private val database: StoryDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, StoryListResponse>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StoryListResponse>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        query.page = page

        try {
            val responseData = apiService.getStoriesPagination(query.toMap())
            var endOfPaginationReached: Boolean = false
            responseData.body()?.listStory?.let { listStory ->

                endOfPaginationReached = listStory.isEmpty()
//                val endOfPaginationReached: Boolean = listStory.isEmpty()

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.remoteKeysDao().deleteRemoteKeys()
                        database.storyDao().deleteAll()
                    }
                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    val keys = listStory.map {
                        RemoteKeysEntity(
                            id = it.id,
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    }
                    database.remoteKeysDao().insertAll(keys)
                    database.storyDao().insertStory(listStory)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, StoryListResponse>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            database.remoteKeysDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, StoryListResponse>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            database.remoteKeysDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, StoryListResponse>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getRemoteKeysId(id)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}