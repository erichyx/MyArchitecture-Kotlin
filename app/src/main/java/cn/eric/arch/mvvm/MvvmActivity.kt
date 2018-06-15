package cn.eric.arch.mvvm


import cn.eric.arch.R
import cn.eric.arch.BR
import cn.eric.arch.databinding.ActivityMvvmBinding
import cn.eric.basicore.arch.mvvm.uicontroller.BaseActivity

class MvvmActivity : BaseActivity<ActivityMvvmBinding, MovieViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_mvvm

    override val viewModelId: Int
        get() = BR.viewModel

    override val viewModel: MovieViewModel
        get() {
            return provideViewModel(MovieViewModel::class.java)
        }
}
