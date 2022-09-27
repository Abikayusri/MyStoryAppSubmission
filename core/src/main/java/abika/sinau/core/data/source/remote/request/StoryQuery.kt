package abika.sinau.core.data.source.remote.request


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
data class StoryQuery(
    var page: Int = 1,
    var size: Int = 10,
    var location: Int = 1
){
    fun toMap(): Map<String, Any> {
        val queryMap = HashMap<String, Any>()
        queryMap["page"] = page
        queryMap["size"] = size
        queryMap["location"] = location

        return queryMap
    }
}