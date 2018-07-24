package cn.eric.arch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment
import me.listenzz.navigation.AwesomeFragment


/**
 * A simple [Fragment] subclass.
 */
class OtherFragment : AwesomeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    companion object {
        fun newInstance(sceneId: Int): OtherFragment {
            val args = Bundle()
            val fragment = OtherFragment()
            args.putString(BottomTabFragment.ARGS_SCENE_ID, sceneId.toString())
            fragment.arguments = args
            return fragment
        }
    }
}
