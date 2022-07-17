package tk.lorddarthart.swapicharacterfinder.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.*
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class,
    PresenterModule::class
])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}