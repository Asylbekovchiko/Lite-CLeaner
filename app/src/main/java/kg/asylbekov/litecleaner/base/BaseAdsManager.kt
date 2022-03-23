package kg.asylbekov.litecleaner.base

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdController private constructor(context: Context) {
    var mInterstitialAd: InterstitialAd? = null
    companion object {
        @Volatile
        private var INSTANCE: AdController? = null

        fun getInstance(context: Context): AdController {
            return INSTANCE ?: synchronized(this) {
                AdController(context).also {
                    INSTANCE = it
                }
            }
        }
    }

    var adRequest: AdRequest = AdRequest.Builder().build()



    init {
    }

    fun refreshInterstitialAd() {
        this.adRequest = AdRequest.Builder().build()
    }
}