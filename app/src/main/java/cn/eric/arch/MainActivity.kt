package cn.eric.arch

import android.os.Bundle
import cn.eric.arch.mvp.MvpFragment
import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment
import cn.eric.arch.mvvm.MovieFragment
import me.listenzz.navigation.AwesomeActivity

class MainActivity : AwesomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootFragment = BottomTabFragment.newInstance(R.menu.navigation, R.id.navigation_mvvm)
        val mvpFragment = MvpFragment.newInstance(R.id.navigation_mvp)
        val movieFragment = MovieFragment.newInstance(R.id.navigation_mvvm)
        val otherFragment = OtherFragment.newInstance(R.id.navigation_other)
        rootFragment.setChildFragments(mvpFragment, movieFragment, otherFragment)
        setActivityRootFragment(rootFragment)
    }
}
