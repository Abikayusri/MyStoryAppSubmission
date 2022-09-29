package abika.sinau.core.data.source.remote.request


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
data class StoryQuery(
    var page: Int = 1,
    var size: Int = 10,
    var location: Double? = null
) {
    fun toMap(): Map<String, Any> {
        val queryMap = HashMap<String, Any>()
        queryMap["page"] = page
        queryMap["size"] = size
        if (location != null) queryMap["location"] = location ?: 0.0

        return queryMap
    }
}