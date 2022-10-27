package abika.sinau.core.data.source.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
@Parcelize
@Entity(tableName = "story")
data class StoryListResponse(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lon")
    val lon: Double? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("photoUrl")
    val photoUrl: String? = null
): Parcelable