package abika.sinau.mystoryapp.ui.splash

import abika.sinau.core.utils.base.BaseActivity
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.visible
import abika.sinau.mystoryapp.databinding.ActivitySplashScreenBinding
import abika.sinau.mystoryapp.ui.authentication.login.LoginActivity
import abika.sinau.mystoryapp.ui.story.list_story.ListStoryActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun setupViews() {
        checkPrefs()
    }

    private fun checkPrefs() {
        val hasLogin = sessionPrefs.hasLogin

        showAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            if (hasLogin) {
                startActivity(Intent(this@SplashScreenActivity, ListStoryActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }

            hideAnimation()
            finishAffinity()
        }, 3000)
    }

    private fun showAnimation() {
        binding.lavAnimation.visible()
    }

    private fun hideAnimation() {
        binding.lavAnimation.gone()
    }
}