package com.jjz.frameworkdemo.ui.viewbinding.kotlin

import android.os.Bundle
import android.view.View
import com.jjz.frameworkdemo.BaseFragment
import com.jjz.frameworkdemo.R
import com.jjz.frameworkdemo.bindView
import com.jjz.frameworkdemo.databinding.FragmentViewBindingKtBinding


class ViewBindingKtFragment : BaseFragment(R.layout.fragment_view_binding_kt) {

    private val binding: FragmentViewBindingKtBinding by bindView()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCenter.text = "class ViewBindingKtFragment : BaseFragment(R.layout.fragment_view_binding_kt) {\n" +
                "\n" +
                "    private val binding: FragmentViewBindingKtBinding by bindView()\n" +
                "    \n" +
                "\n" +
                "    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {\n" +
                "        super.onViewCreated(view, savedInstanceState)\n" +
                "        binding.tvCenter.text = \"\"\n" +
                "    }\n" +
                "}"
    }
}