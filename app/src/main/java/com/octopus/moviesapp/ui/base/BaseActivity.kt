package com.octopus.moviesapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.BR

abstract class BaseActivity<VDB : ViewDataBinding>() :
    AppCompatActivity() {

    private var _binding: VDB? = null
    protected val binding: VDB get() = _binding!!

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = DataBindingUtil.setContentView(this@BaseActivity, getLayoutId())
        binding.run {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}