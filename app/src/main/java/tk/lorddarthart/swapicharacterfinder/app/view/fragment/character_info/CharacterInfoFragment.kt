package tk.lorddarthart.swapicharacterfinder.app.view.fragment.character_info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import tk.lorddarthart.swapicharacterfinder.R
import tk.lorddarthart.swapicharacterfinder.app.model.entity.SwapiCharEntity
import tk.lorddarthart.swapicharacterfinder.app.view.base.BaseFragment
import tk.lorddarthart.swapicharacterfinder.databinding.FragmentCharacterInfoBinding

/**
 * Created by LordDarthArt on 10.11.2019.
 */
class CharacterInfoFragment: BaseFragment(), CharacterInfoFragmentView {

    private lateinit var characterInfoFragmentBinding: FragmentCharacterInfoBinding

    @InjectPresenter
    lateinit var characterInfoFragmentPresenter: CharacterInfoFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterInfoFragmentBinding = FragmentCharacterInfoBinding.inflate(
            inflater,
            container,
            false
        )

        initialization()

        return characterInfoFragmentBinding.root
    }

    private fun initialization() {
        start()
    }

    private fun start() {
        characterInfoFragmentPresenter.swapiCharName = swapiCharName
        characterInfoFragmentPresenter.loadDataFromDB()

        activity.setSupportActionBar(characterInfoFragmentBinding.aboutAlbumToolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activity.supportActionBar?.title = characterInfoFragmentPresenter.swapiCharName

        triggerFragment(characterInfoFragmentPresenter.swapiChar)
    }

    @SuppressLint("SetTextI18n")
    override fun triggerFragment(targetSwapiChar: SwapiCharEntity?) {
        targetSwapiChar?.let { char ->
            with (characterInfoFragmentBinding) {
                characterName.text = char.swapiCharName
                characterHeight.text = "${getString(R.string.height)} ${char.swapiCharHeight}"
                characterMass.text = "${getString(R.string.mass)} ${char.swapiCharMass}"
                characterHairColor.text = "${getString(R.string.hair_color)} ${char.swapiCharHairColor}"
                characterSkinColor.text = "${getString(R.string.skin_color)} ${char.swapiCharSkinColor}"
                characterEyeColor.text = "${getString(R.string.eye_color)} ${char.swapiCharEyeColor}"
                characterBirthYear.text = "${getString(R.string.birth_year)} ${char.swapiCharBirthYear}"
                characterGender.text = "${getString(R.string.gender)} ${char.swapiCharGender}"
            }
        }
    }

    companion object {
        var swapiCharName: String? = null

        fun newInstance(swapiChar: SwapiCharEntity): CharacterInfoFragment {
            this.swapiCharName = swapiChar.swapiCharName
            return CharacterInfoFragment()
        }
    }
}