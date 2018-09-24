package cn.eric.basicore.arch.mvp.uicontroller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by eric on 2018/1/6.
 */

abstract class BaseMvpActivity : AppCompatActivity(), MvpControlBehavior {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)

        initView()
        onPrepare()
    }
}
