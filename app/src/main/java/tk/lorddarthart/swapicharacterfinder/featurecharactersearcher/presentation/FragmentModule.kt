package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info.CharacterInfoFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.MainFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.history_list.HistoryListFragment
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.main.online_search.OnlineSearchFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterInfoFragment(): CharacterInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeHistoryListFragment(): HistoryListFragment

    @ContributesAndroidInjector
    abstract fun contributeOnlineSearchFragment(): OnlineSearchFragment
}