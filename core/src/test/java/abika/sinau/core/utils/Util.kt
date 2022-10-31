package abika.sinau.core.utils

import abika.sinau.core.data.Resource
import abika.sinau.core.utils.DateUtils.DD_MMM_YYYY_DASH
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */

fun <RP : Any> responseToResources(response: Response<RP>): Resource<RP> {
    if (response.isSuccessful) {
        response.body()?.let { result ->
            return Resource.Success(result)
        }
    }

    return Resource.Error(response.message())
}
