package kg.asylbekov.litecleaner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import kg.asylbekov.litecleaner.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mInterstitialAd: InterstitialAd? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        initBottomNav()
        initBanners()
    }

    private fun initBottomNav() {
        navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.boostFragment,
                R.id.optimizeFragment,
                R.id.coolerFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.boostFragment -> bottomNavigationView.visibility = View.INVISIBLE
//                R.id.optimizeFragment -> bottomNavigationView.visibility = View.INVISIBLE
//                R.id.coolerFragment -> bottomNavigationView.visibility = View.VISIBLE
//                R.id.cleanFragment -> bottomNavigationView.visibility = View.VISIBLE
//                R.id.notificationsFragment -> bottomNavigationView.visibility = View.INVISIBLE
//            }
//            when(destination.id){
////                R.id.guideFragment -> supportActionBar?.show()
//            }
//
//
//        }
    }

    private fun initBanners() {
        val adRequest = AdRequest.Builder().build()
        val mAdView = findViewById<AdView>(R.id.adView)

        mAdView.loadAd(adRequest)

        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }


}