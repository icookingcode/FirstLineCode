package com.guc.firstlinecode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guc.firstlinecode.R
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/4/29.
 * 描述：另一个右侧Fragment
 */
class AnotherRightFragment : Fragment() {
    companion object {
        const val TAG = "RightFragment"
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        LogG.loge(TAG, "onAttach")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogG.loge(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogG.loge(TAG, "onCreateView")
        return inflater.inflate(R.layout.another_right_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogG.loge(TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        LogG.loge(LeftFragment.TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        LogG.loge(LeftFragment.TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        LogG.loge(LeftFragment.TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        LogG.loge(LeftFragment.TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogG.loge(LeftFragment.TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogG.loge(LeftFragment.TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LogG.loge(LeftFragment.TAG, "onDetach")
    }
}