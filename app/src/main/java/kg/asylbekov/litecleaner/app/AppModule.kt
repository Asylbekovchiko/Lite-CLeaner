package kg.asylbekov.litecleaner.app

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppModule: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        FirebaseApp.initializeApp(this)

        val config = YandexMetricaConfig.newConfigBuilder("b76262e1-db51-4ab1-ace7-858cd9495386").build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)

//        startKoin {
//            androidLogger()
//            androidContext(this@AppModule)
//            modules(listOf(
//
//            ))
//        }
    }
}