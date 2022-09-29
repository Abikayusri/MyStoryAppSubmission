package abika.sinau.mystoryapp.ui.splash

import abika.sinau.core.data.source.local.SessionPrefsManager
import abika.sinau.core.utils.SessionPrefs
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.toastShort
import abika.sinau.core.utils.visible
import abika.sinau.mystoryapp.databinding.ActivitySplashScreenBinding
import abika.sinau.mystoryapp.ui.authentication.login.LoginActivity
import abika.sinau.mystoryapp.ui.story.list_story.ListStoryActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    @Inject
    @SessionPrefs
    lateinit var sessionPrefs: SessionPrefsManager

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPrefs()
    }

    private fun checkPrefs() {
        val hasLogin = sessionPrefs.hasLogin
        toastShort("hasLogin: $hasLogin")

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