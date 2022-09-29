package abika.sinau.mystoryapp.ui.story.detail_story

import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/30/2022
 */

@HiltViewModel
class DetailStoryViewModel @Inject constructor(
    private val usecase: StoryAppUsecase
): ViewModel() {
}