package abika.sinau.core.utils.custom_view

import abika.sinau.core.R
import abika.sinau.core.utils.StoryConst.TYPE_EMAIL
import abika.sinau.core.utils.StoryConst.TYPE_NAME
import abika.sinau.core.utils.StoryConst.TYPE_PASSWORD
import android.content.Context
import android.graphics.Canvas
import android.text.InputType
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
class MyEditText : AppCompatEditText {

    private var emailType = false
    private var editTextType: String = TYPE_NAME

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        addTextChangedListener(
            onTextChanged = { data, _, _, _ ->
                if (editTextType == TYPE_NAME && data.toString().length < 3) {
                    setErrorName()
                } else if (editTextType == TYPE_PASSWORD && (data.toString()
                        .isNotEmpty() && data.toString().length < 6)
                ) {
                    setErrorPassword()
                } else if (editTextType == TYPE_EMAIL && !isValidEmail(data.toString())) {
                    setErrorEmail()
                } else {
                    setDefault()
                }
            }
        )

        setDefault()
    }

    private fun isValidEmail(data: String): Boolean {
        return !TextUtils.isEmpty(data) && Patterns.EMAIL_ADDRESS.matcher(data).matches()
    }

    fun setErrorEmail() {
        error = context.getString(R.string.label_error_email)
        setBackgroundResource(R.drawable.bg_custom_edit_text_error)
        setPadding(32)
    }

    fun setErrorPassword() {
        error = context.getString(R.string.label_error_password)
        setBackgroundResource(R.drawable.bg_custom_edit_text_error)
        setPadding(32)
    }

    fun setErrorName() {
        error = context.getString(R.string.label_error_name)
        setBackgroundResource(R.drawable.bg_custom_edit_text_error)
        setPadding(32)
    }

    fun setDefault() {
        setBackgroundResource(R.drawable.bg_custom_edit_text)
        setPadding(32)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        maxLines = 1
        hint = when (editTextType) {
            TYPE_NAME -> {
                context.getString(R.string.label_fake_name)
            }
            TYPE_EMAIL -> {
                context.getString(R.string.label_fake_email)
            }
            else -> {
                context.getString(R.string.label_fake_password)
            }
        }
    }

    fun setEditTextType(type: String?) {
        when (type) {
            TYPE_EMAIL -> {
                editTextType = TYPE_EMAIL
                emailType = true
            }
            TYPE_PASSWORD -> {
                editTextType = TYPE_PASSWORD
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                transformationMethod = PasswordTransformationMethod.getInstance()
                text = null
            }
            else -> {
                editTextType = TYPE_NAME
            }
        }
    }

    fun isNotEmpty(): Boolean {
        return text.toString() != ""
    }

    fun isNotEmptyAndError(): Boolean {
        return error == null && text.toString() != ""
    }
}