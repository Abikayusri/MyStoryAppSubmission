package abika.sinau.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author by Abika Chairul Yusri on 10/28/2022
 */
@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)
