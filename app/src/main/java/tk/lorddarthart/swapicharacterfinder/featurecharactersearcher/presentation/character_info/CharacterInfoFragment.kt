package tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.character_info

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentCharacterInfoBinding
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.domain.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.featurecharactersearcher.presentation.MainActivity

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class CharacterInfoFragment : BaseFragment<FragmentCharacterInfoBinding, CharacterInfoFragmentPresenter>(),
    CharacterInfoFragmentView {

    override val presenter by moxyPresenter { presenterProvider.get() }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCharacterInfoBinding.inflate(
        inflater,
        container,
        false
    )

    override fun initialization(inflater: LayoutInflater, container: ViewGroup?) {
        binding = initializeBinding(inflater, container)
        start()
    }

    override fun start() {
        presenter.swapiCharName = (requireActivity() as? MainActivity)?.swapiCharName
        lifecycleScope.launch {
            presenter.loadDataFromDB()
        }

        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding?.aboutAlbumToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            supportActionBar?.title = presenter.swapiCharName
        }

        // to execute the case when swapiChar already initialized
        presenter.swapiChar?.let {
            triggerFragment(it)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun triggerFragment(targetSwapiChar: SwapiCharEntity) {
        binding?.apply {
            characterName.text = targetSwapiChar.swapiCharName
            characterHeight.text =
                "${getString(R.string.height)} ${targetSwapiChar.swapiCharHeight}"
            characterMass.text = "${getString(R.string.mass)} ${targetSwapiChar.swapiCharMass}"
            characterHairColor.text =
                "${getString(R.string.hair_color)} ${targetSwapiChar.swapiCharHairColor}"
            characterSkinColor.text =
                "${getString(R.string.skin_color)} ${targetSwapiChar.swapiCharSkinColor}"
            characterEyeColor.text =
                "${getString(R.string.eye_color)} ${targetSwapiChar.swapiCharEyeColor}"
            characterBirthYear.text =
                "${getString(R.string.birth_year)} ${targetSwapiChar.swapiCharBirthYear}"
            characterGender.text =
                "${getString(R.string.gender)} ${targetSwapiChar.swapiCharGender}"
        }
    }
}