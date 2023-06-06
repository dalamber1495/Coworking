package com.issart.coworking.android

import android.app.Application
import com.issart.coworking.android.di.androidModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class CoworkingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(this.getString(R.string.yandex_map_key))
        startKoin {
            androidLogger()
            androidContext(this@CoworkingApplication)

            modules(
//                commonModule,
//                introModule,
                androidModule
//                mainScreenModule,
            )
        }
    }
}