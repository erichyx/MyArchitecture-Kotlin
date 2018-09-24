package cn.eric.basicore.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by eric on 2018/9/24
 */

fun replaceFragment(fragmentManager: FragmentManager,
                    @IdRes frameId: Int, fragment: Fragment) {
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    transaction.commit()
}

fun switchFragment(fragmentManager: FragmentManager, @IdRes frameId: Int, current: Fragment, last: Fragment) {
    val transaction = fragmentManager.beginTransaction()
    transaction.hide(last)
    if (!current.isAdded) {
        transaction.add(frameId, current)
    }
    transaction.show(current).commitNow()
}