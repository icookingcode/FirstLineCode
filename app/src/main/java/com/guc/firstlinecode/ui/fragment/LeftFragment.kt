package com.guc.firstlinecode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guc.firstlinecode.R
import com.guc.firstlinecode.ui.FragmentDemo1Activity
import com.guc.firstlinecode.utils.LogG

/**
 * Created by guc on 2020/4/29.
 * 描述：左侧Fragment
 */
class LeftFragment : Fragment() {

    private lateinit var fragAty: FragmentDemo1Activity

    companion object {
        const val TAG = "LeftFragment"
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
//        if (activity!=null) fragAty = activity as FragmentDemo1Activity  //Fragment中获取Activity
        return inflater.inflate(R.layout.left_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogG.loge(TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        LogG.loge(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        LogG.loge(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        LogG.loge(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        LogG.loge(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogG.loge(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogG.loge(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LogG.loge(TAG, "onDetach")
    }
}