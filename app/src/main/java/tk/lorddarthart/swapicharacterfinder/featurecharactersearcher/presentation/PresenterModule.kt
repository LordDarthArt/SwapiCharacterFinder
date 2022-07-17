package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation

import android.content.Context
import dagger.Module
import dagger.Provides
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info.CharacterInfoFragmentPresenter
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.MainFragmentPresenter
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.HistoryListFragmentPresenter
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search.OnlineSearchFragmentPresenter
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideMainActivityPresenter(): MainActivityPresenter = MainActivityPresenter()

    @Provides
    @Singleton
    fun provideMainFragmentPresenter(): MainFragmentPresenter = MainFragmentPresenter()

    @Provides
    @Singleton
    fun provideHistoryListPresenter(applicationContext: Context): HistoryListFragmentPresenter = HistoryListFragmentPresenter(applicationContext)

    @Provides
    @Singleton
    fun provideOnlineSearchPresenter(applicationContext: Context): OnlineSearchFragmentPresenter = OnlineSearchFragmentPresenter(applicationContext)

    @Provides
    @Singleton
    fun provideCharacterInfoPresenter(applicationContext: Context): CharacterInfoFragmentPresenter = CharacterInfoFragmentPresenter(applicationContext)
}