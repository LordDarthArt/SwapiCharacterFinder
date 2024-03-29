package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import tk.lorddarthart.swapicharacterfinder.di.DaggerAppComponent
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> =
        dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .applicationContext(this.applicationContext)
            .build()
            .inject(this)
    }
}