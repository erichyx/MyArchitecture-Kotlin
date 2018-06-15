package cn.eric.arch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import cn.eric.arch.mvp.MvpFragment
import cn.eric.arch.mvvm.MovieFragment
import cn.eric.basicore.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mvpFragment: MvpFragment? = null
    private var movieFragment: MovieFragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_mvp -> {
                if (mvpFragment == null) {
                    mvpFragment = MvpFragment.newInstance()
                }
                ActivityUtils.replaceFragmentToActivity(supportFragmentManager, mvpFragment!!, R.id.container)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mvvm -> {
                if (movieFragment == null) {
                    movieFragment = MovieFragment.newInstance()
                }
                ActivityUtils.replaceFragmentToActivity(supportFragmentManager, movieFragment!!, R.id.container)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_other -> {
                val otherFragment = OtherFragment.newInstance()
                ActivityUtils.replaceFragmentToActivity(supportFragmentManager, otherFragment, R.id.container)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieFragment = MovieFragment.newInstance()
        ActivityUtils.replaceFragmentToActivity(supportFragmentManager, movieFragment!!, R.id.container)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_mvvm
    }
}
