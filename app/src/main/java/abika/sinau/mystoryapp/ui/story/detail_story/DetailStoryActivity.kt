package abika.sinau.mystoryapp.ui.story.detail_story

import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.utils.DateUtils.DD_MMMM_YYYY_HH_MM
import abika.sinau.core.utils.DateUtils.UTCT_FORMAT_4
import abika.sinau.core.utils.DateUtils.convertDateFromTo
import abika.sinau.core.utils.StoryConst
import abika.sinau.core.utils.base.BaseActivity
import abika.sinau.core.utils.loadImage
import abika.sinau.mystoryapp.R
import abika.sinau.mystoryapp.databinding.ActivityDetailStoryBinding
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStoryActivity :
    BaseActivity<ActivityDetailStoryBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityDetailStoryBinding {
        return ActivityDetailStoryBinding.inflate(layoutInflater)
    }

    override fun setupViews() {
        val userData =
            intent?.getParcelableExtra<StoryListResponse>(StoryConst.EXTRA_PARCELABLE_USER)
        binding.apply {
            inclAppBar.btnAppbarBack.setOnClickListener {
                finish()
            }

            ivDetailImage.loadImage(
                userData?.photoUrl.toString(),
                R.mipmap.img_placeholder,
                R.mipmap.img_placeholder
            )
            tvDetailName.text = userData?.name
            tvDetailCreateat.text =
                convertDateFromTo(userData?.createdAt, UTCT_FORMAT_4, DD_MMMM_YYYY_HH_MM)
            tvDetailDescription.text = userData?.description
        }
    }
}