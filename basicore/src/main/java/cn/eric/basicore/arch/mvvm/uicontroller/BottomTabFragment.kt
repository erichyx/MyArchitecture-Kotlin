package cn.eric.basicore.arch.mvvm.uicontroller


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.eric.basicore.R
import kotlinx.android.synthetic.main.fragment_bottom_tab.*
import me.listenzz.navigation.AwesomeFragment
import me.listenzz.navigation.FragmentHelper
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class BottomTabFragment : AwesomeFragment() {

    private var fragments: MutableList<AwesomeFragment> = mutableListOf()
    private var fragmentTags: ArrayList<String> = ArrayList()
    private var navResId: Int = 0
    private var selectedIndex: Int = 0
        private set(value) {
            field = value
            scheduleTaskAtStarted {
                val fragmentManager = childFragmentManager
                fragmentManager.executePendingTransactions()
                val previous = fragmentManager.primaryNavigationFragment
                val current = fragments[field]
                val transaction = fragmentManager.beginTransaction()
                transaction.setPrimaryNavigationFragment(current)
                transaction.hide(previous)
                transaction.show(current)
                transaction.commit()
            }
        }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        for (i in fragments.indices) {
            val fragment = fragments[i]
            if (fragment.sceneId == item.itemId.toString() + "") {
                selectedIndex = i
                break
            }
        }
        true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_tab, container, false)
    }

    override fun isParentFragment(): Boolean {
        return true
    }

    override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
        super.onViewCreated(root, savedInstanceState)

        val arguments = FragmentHelper.getArguments(this)
        navResId = arguments.getInt(ARGS_NAV_RES_ID)

        navigation.inflateMenu(navResId)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState != null) {
            fragmentTags = savedInstanceState.getStringArrayList(SAVED_FRAGMENT_TAGS)
            val fragmentManager = childFragmentManager

            fragments.clear()
            for (tag in fragmentTags) {
                fragments.add(fragmentManager.findFragmentByTag(tag) as AwesomeFragment)
            }

            selectedIndex = savedInstanceState.getInt(SAVED_SELECTED_INDEX)
        } else {
            if (fragments.size == 0) {
                throw IllegalArgumentException("必须使用 `setChildFragments` 设置 childFragments ")
            }
            setChildFragmentsInternal(fragments)

            val selectedItemId = arguments.getInt(ARGS_NAV_SELECTED_ITEM_ID)
            navigation.selectedItemId = selectedItemId
        }
    }

    fun setChildFragments(vararg fragments: AwesomeFragment) {
        setChildFragments(Arrays.asList(*fragments))
    }

    fun setChildFragments(fragments: MutableList<AwesomeFragment>) {
        if (isAdded) {
            throw IllegalStateException(javaClass.simpleName + " 已经处于 added 状态，不能再设置 childFragments")
        }
        this.fragments = fragments
    }

    private fun setChildFragmentsInternal(fragments: List<AwesomeFragment>) {
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()

        for (i in fragments.indices) {
            val fragment = fragments[i]
            fragmentTags.add(fragment.sceneId)
            transaction.add(R.id.tabs_content, fragment, fragment.sceneId)
            if (i == selectedIndex) {
                transaction.setPrimaryNavigationFragment(fragment)
            } else {
                transaction.hide(fragment)
            }
        }
        transaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(SAVED_FRAGMENT_TAGS, fragmentTags)
        outState.putInt(SAVED_SELECTED_INDEX, selectedIndex)
    }

    companion object {

        private val SAVED_FRAGMENT_TAGS = "nav_fragment_tags"
        private val SAVED_SELECTED_INDEX = "nav_selected_index"

        private val ARGS_NAV_RES_ID = "nav_res_id"
        private val ARGS_NAV_SELECTED_ITEM_ID = "nav_selected_item_id"
        val ARGS_SCENE_ID = "nav_scene_id"

        fun newInstance(navMenuId: Int, selectedItemId: Int): BottomTabFragment {

            val args = Bundle()

            val fragment = BottomTabFragment()
            args.putInt(ARGS_NAV_RES_ID, navMenuId)
            args.putInt(ARGS_NAV_SELECTED_ITEM_ID, selectedItemId)
            fragment.arguments = args
            return fragment
        }
    }
}
