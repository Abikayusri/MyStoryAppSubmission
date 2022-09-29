package abika.sinau.core.utils

import abika.sinau.core.data.Resource
import android.app.Activity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response


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

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.toastShort(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun snackBarShort(view: View, msg: String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
}

fun snackBarLong(view: View, msg: String) {
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
}

fun ImageView.loadImage(url: String, placeholder: Int, error: Int) {
    Glide
        .with(this)
        .load(url)
        .error(error)
        .placeholder(placeholder)
        .into(this)
}

fun EditText.getTextString(): String {
    return text.toString()
}