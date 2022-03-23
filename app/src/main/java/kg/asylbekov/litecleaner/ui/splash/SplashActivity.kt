package kg.asylbekov.litecleaner.ui.splash

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kg.asylbekov.litecleaner.R
import kg.asylbekov.litecleaner.app.AppModule.Companion.context
import kg.asylbekov.litecleaner.databinding.ActivitySplashBinding
import kg.asylbekov.litecleaner.ui.MainActivity
import kg.asylbekov.litecleaner.ui.webview.WebViewActivity
import kg.asylbekov.litecleaner.utils.extensions.getAcceptPV
import kg.asylbekov.litecleaner.utils.extensions.saveAcceptPV
import kg.asylbekov.litecleaner.utils.extensions.saveFirstOpen


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val fromWebView: Boolean
        get() = intent.getBooleanExtra("fromWebView", false)
    var mInterstitialAd: InterstitialAd? = null
    private var adIsLoaded: Boolean = false

    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler()
        initAds()
        initUI()
        checkFirstOpen()
    }

    private fun initAds() {
        val adRequest = AdRequest.Builder().build()

        MobileAds.initialize(this)

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    adIsLoaded = false

//                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    adIsLoaded = true

                    mInterstitialAd?.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                Log.e("onAdDismissedFullS", "onAdDismissedFullScreenContent")
                                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                Log.e("adError", "$adError")
                            }

                            override fun onAdShowedFullScreenContent() {
                                mInterstitialAd = null
                                Log.e("onAdShowedFullScre", "onAdShowedFullScreenContent")
                            }
                        }

                }

            })

    }

    private fun initUI() {
        binding.apply {
            btnContinue.setOnClickListener {
                if (mInterstitialAd != null && adIsLoaded) {
                    mInterstitialAd?.show(this@SplashActivity)
                } else {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    Toast.makeText(this@SplashActivity, "AD wasnt loaded", Toast.LENGTH_SHORT)
                        .show()
                }
                saveAcceptPV(this@SplashActivity, true)
            }

            binding.tvPrivacyTitleSecondPart.setOnClickListener {
                val browseintent = Intent(this@SplashActivity, WebViewActivity::class.java)
                browseintent.putExtra("url", "https://www.google.com")
                startActivity(browseintent)
            }
            binding.tvPrivacyTitleFourthPart.setOnClickListener {
                val browseintent = Intent(this@SplashActivity, WebViewActivity::class.java)
                browseintent.putExtra("url2", "https://yandex.ru")
                startActivity(browseintent)
            }
        }
    }

    private fun checkFirstOpen() {

        if (!getAcceptPV(this)) {
            if (fromWebView != null && fromWebView == true) {
                binding.clPrivacyMain.visibility = View.VISIBLE
                binding.clIndicator.visibility = View.GONE
            } else {
                binding.clIndicator.visibility = View.VISIBLE
                handler.postDelayed({
                    binding.clPrivacyMain.visibility = View.VISIBLE
                    binding.clIndicator.visibility = View.GONE
                }, 7500)
                handler.postDelayed({
                    binding.tvLoadingContent.text = resources.getString(R.string.loading_database)
                    handler.postDelayed({
                        binding.tvLoadingContent.text =
                            resources.getString(R.string.initializing_processes)
                    }, 2500)
                }, 2500)
                saveFirstOpen(this, false)
            }
        } else {
            binding.clIndicator.visibility = View.VISIBLE
            handler.postDelayed({
                if (mInterstitialAd != null && adIsLoaded) {
                    mInterstitialAd?.show(this@SplashActivity)
                } else {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    Toast.makeText(this, "AD wasnt loaded", Toast.LENGTH_SHORT).show()
                }
            }, 7500)

            handler.postDelayed({
                binding.tvLoadingContent.text = resources.getString(R.string.loading_database)
                handler.postDelayed({
                    binding.tvLoadingContent.text =
                        resources.getString(R.string.initializing_processes)
                }, 2500)
            }, 2500)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
        finishAndRemoveTask()
        handler.removeCallbacksAndMessages(null)
    }
}