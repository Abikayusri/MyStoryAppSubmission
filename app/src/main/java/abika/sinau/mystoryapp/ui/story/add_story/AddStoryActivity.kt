package abika.sinau.mystoryapp.ui.story.add_story

import abika.sinau.core.data.Resource
import abika.sinau.core.utils.StoryConst.REQUEST_CAMERA_PERMISSION
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.createCustomTempFile
import abika.sinau.core.utils.getTextString
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.reduceFileImage
import abika.sinau.core.utils.rotateBitmap
import abika.sinau.core.utils.toastShort
import abika.sinau.core.utils.uriToFile
import abika.sinau.core.utils.visible
import abika.sinau.core.R
import abika.sinau.mystoryapp.databinding.ActivityAddStoryBinding
import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

@AndroidEntryPoint
class AddStoryActivity : BaseViewModelActivity<AddStoryViewModel, ActivityAddStoryBinding>() {

    private var getFile: File? = null
    private lateinit var currentPhotoPath: String

    private val cameraResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                true
            )

            binding.ivAddImage.setImageBitmap(result)
        }
    }

    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val myFile = uriToFile(uri, this@AddStoryActivity)
            getFile = myFile
            binding.ivAddImage.setImageURI(uri)
        }
    }

    override val viewModelClass: Class<AddStoryViewModel>
        get() = AddStoryViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityAddStoryBinding {
        return ActivityAddStoryBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: AddStoryViewModel) {
        viewModel.resultAddStory.observe(lifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideAnimation()
                    toastShort(getString(R.string.label_success_create_story))
                    finish()
                }
                is Resource.Error -> {
                    hideAnimation()
                    toastShort(getString(R.string.label_failed_create_story, response.message.toString()))
                }
                is Resource.Loading -> {
                    showAnimation()
                }
            }
        }
    }

    override fun setupViews() {
        binding.apply {
            inclAppBar.apply {
                btnAppbarBack.setOnClickListener { finish() }
                tvAppbarTitle.text = getString(R.string.label_add_story)
            }

            btnAddOpenCamera.setOnClickListener {
                if (hasCameraPermission()) {
                    startTakePhoto()
                } else {
                    EasyPermissions.requestPermissions(
                        this@AddStoryActivity, getString(R.string.title_camera_permission),
                        REQUEST_CAMERA_PERMISSION, Manifest.permission.CAMERA
                    )
                }
            }

            btnAddOpenGallery.setOnClickListener {
                galleryResult.launch("image/*")
            }

            btnAddUpload.setOnClickListener {
                if (getFile != null) {
                    if (etAddDescription.getTextString().isEmpty()) {
                        etAddDescription.error = getString(R.string.label_description_empty)
                    } else {
                        val file = reduceFileImage(getFile as File)
                        val description = etAddDescription.getTextString()
                            .toRequestBody("text/plain".toMediaTypeOrNull())
                        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                        val body =
                            MultipartBody.Part.createFormData("photo", file.name, requestFile)

                        viewModel.uploadImage(description, body)
                    }
                } else {
                    toastShort(getString(R.string.label_image_empty))
                }
            }
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        createCustomTempFile(application).also {
            val photoUri =
                FileProvider.getUriForFile(this, "abika.sinau.mystoryapp", it)
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            cameraResult.launch(intent)
        }
    }

    private fun hasCameraPermission(): Boolean {
        return EasyPermissions.hasPermissions(this@AddStoryActivity, Manifest.permission.CAMERA)
    }

    private fun showAnimation() {
        binding.lavAnimations.visible()
    }

    private fun hideAnimation() {
        binding.lavAnimations.gone()
    }
}