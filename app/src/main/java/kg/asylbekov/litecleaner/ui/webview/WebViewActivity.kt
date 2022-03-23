package kg.asylbekov.litecleaner.ui.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import kg.asylbekov.litecleaner.R
import kg.asylbekov.litecleaner.ui.splash.SplashActivity

class WebViewActivity : AppCompatActivity() {

    private val google: String?
        get() = intent.getStringExtra("url")

    private val yandex: String?
        get() = intent.getStringExtra("url2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar!!.hide()
        val wv = findViewById<WebView>(R.id.web_view)

        if(google == null){
            wv.loadUrl(yandex!!)
        }else{
            wv.loadUrl(google!!)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SplashActivity::class.java)
        intent.putExtra("fromWebView",true)
        startActivity(intent)
    }
}