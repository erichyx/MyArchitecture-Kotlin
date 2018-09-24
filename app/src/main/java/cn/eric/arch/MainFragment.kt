package cn.eric.arch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.eric.arch.databinding.FragmentMainBinding
import cn.eric.arch.mvp.MvpFragment
import cn.eric.arch.mvvm.MovieFragment
import cn.eric.basicore.utils.replaceFragment
import cn.eric.basicore.utils.switchFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    private lateinit var fragments: Array<Fragment>
    private lateinit var lastFragment: Fragment
    private lateinit var currentFragment: Fragment
    private var selectedItemId: Int = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        savedInstanceState?.let {
            selectedItemId = it.getInt(KEY_SELECTED_ITEM_ID)
        }

        subscribeUi(binding)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentMainBinding) {
        val mvpFragment = MvpFragment.newInstance()
        val movieFragment = MovieFragment.newInstance()
        val otherFragment = OtherFragment.newInstance()
        fragments = arrayOf(mvpFragment, movieFragment, otherFragment)

        currentFragment = when (selectedItemId) {
            R.id.nav_mvp -> fragments[0]
            R.id.nav_movie -> fragments[1]
            R.id.nav_other -> fragments[2]
            else -> {
                selectedItemId = R.id.nav_movie
                fragments[1]
            }
        }
        lastFragment = currentFragment

        replaceFragment(childFragmentManager, R.id.tabs_content, currentFragment)
        binding.navigation.selectedItemId = selectedItemId

        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_mvp -> {
                    if (selectedItemId != R.id.nav_mvp) {
                        selectedItemId = R.id.nav_mvp
                        currentFragment = fragments[0]
                        switchFragment(childFragmentManager, R.id.tabs_content, currentFragment, lastFragment)
                        lastFragment = currentFragment
                    }
                    true
                }
                R.id.nav_movie -> {
                    if (selectedItemId != R.id.nav_movie) {
                        selectedItemId = R.id.nav_movie
                        currentFragment = fragments[1]
                        switchFragment(childFragmentManager, R.id.tabs_content, currentFragment, lastFragment)
                        lastFragment = currentFragment
                    }
                    true

                }

                R.id.nav_other -> {
                    if (selectedItemId != R.id.nav_other) {
                        selectedItemId = R.id.nav_other
                        currentFragment = fragments[2]
                        switchFragment(childFragmentManager, R.id.tabs_content, currentFragment, lastFragment)
                        lastFragment = currentFragment
                    }
                    true
                }
                else -> false
            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SELECTED_ITEM_ID, selectedItemId)
    }

    companion object {
        private const val KEY_SELECTED_ITEM_ID = "key_selected_item_id"
    }
}
