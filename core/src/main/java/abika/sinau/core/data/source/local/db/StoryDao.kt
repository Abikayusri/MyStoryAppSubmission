package abika.sinau.core.data.source.local.db

import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * @author by Abika Chairul Yusri on 10/28/2022
 */

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: List<StoryListResponse>)

    @Query("SELECT * FROM story")
    fun getAllStory(): PagingSource<Int, StoryListResponse>

    @Query("DELETE FROM story")
    suspend fun deleteAll()
}