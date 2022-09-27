package abika.sinau.core.data.source.remote.request


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
class AddStoryRequest(
    val name: String? = null,
    val description: String,
    val photoUrl: String,
    val createAt: String,
    val lat: String,
    val lon: String
)

//{
//    "name": "Bjorka",
//    "description": "black",
//    "photoUrl": "https://story-api.dicoding.dev/images/stories/photos-1663828973026_hAujRJ5U.jpg",
//    "createdAt": "2022-09-22T06:42:53.028Z",
//    "lat": -6.990990990990991,
//    "lon": 110.44272999572765
//}
//
//{
//    "description": "black",
//    "photoUrl": "https://story-api.dicoding.dev/images/stories/photos-1663828973026_hAujRJ5U.jpg",
//    "createdAt": "2022-09-22T06:42:53.028Z",
//    "lat": -6.990990990990991,
//    "lon": 110.44272999572765
//}