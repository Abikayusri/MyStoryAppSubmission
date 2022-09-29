package abika.sinau.mystoryapp.ui.story.add_story

import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/29/2022
 */

@HiltViewModel
class AddStoryViewModel @Inject constructor(
    private val usecase: StoryAppUsecase
): ViewModel() {
}