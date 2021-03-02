package com.jjz.frameworkdemo.ui.viewbinding

import android.os.Bundle
import android.view.View
import com.jjz.frameworkdemo.ui.BaseFragment
import com.jjz.frameworkdemo.R
import com.jjz.frameworkdemo.bindView
import com.jjz.frameworkdemo.databinding.FragmentViewBindingKtBinding


class ViewBindingKtFragment : BaseFragment(R.layout.fragment_view_binding_kt) {

    private val binding: FragmentViewBindingKtBinding by bindView()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCenter.text = "class ViewBindingKtFragment : BaseFragment() {\n" +
                "\n" +
                "\n" +
                "    private val binding: FragmentViewBindingKtBinding by bindView()\n" +
                "\n" +
                "    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {\n" +
                "        super.onViewCreated(view, savedInstanceState)\n" +
                "        binding.tvHelloWorld.text = \"Hello Android!\"\n" +
                "    }\n" +
                "}"
    }
}