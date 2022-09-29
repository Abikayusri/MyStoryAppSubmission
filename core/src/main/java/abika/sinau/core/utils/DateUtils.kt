package abika.sinau.core.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * @author by Abika Chairul Yusri on 9/30/2022
 */
object DateUtils {

    const val YEAR_TIME = "yyyy-MM-dd HH:mm:ss"
    const val UTCT_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val UTCT_FORMAT_4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val YYYY_MM_DD_HH_MM_SS_DASH = "yyyy-MM-dd HH:mm:ss"
    const val YYYY_MM_DD_DASH = "yyyy-MM-dd"
    const val DD_MM_YYYY_DASH = "dd-MM-yyyy"
    const val DD_MM_YYYY_SLASH = "dd/MM/yyyy"
    const val DD_MM_YYYY_HH_MM_SLASH = "dd/MM/yyyy HH:mm"
    const val EEEE_DD_MMM_YYYY = "EEEE, dd MMMM yyyy"
    const val EEEE_DD_MM_YYYY = "EEEE, dd MMM yyyy"
    const val EE_DD_MMM_YYYY = "EE, dd MMM yyyy"
    const val DD_MMMM_YYYY = "dd MMMM yyyy"
    const val DD_MMM_YYYY = "dd MMM yyyy"
    const val DD_MMMM_YYYY_HH_MM = "dd MMMM yyyy | HH:mm"
    const val HH_MM = "HH:mm"
    const val MM_YYYY = "MM-yyyy"
    const val YYYY = "yyyy"

    fun convertDateFromTo(value: String?, fromFormat: String, toFromat: String): String? {
        val parsedDate = SimpleDateFormat(fromFormat, Locale("id", "ID")).parse(value)
        return SimpleDateFormat(toFromat, Locale("id", "ID")).format(parsedDate)
    }

}