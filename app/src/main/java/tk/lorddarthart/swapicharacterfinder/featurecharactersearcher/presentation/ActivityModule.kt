package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}