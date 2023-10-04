package com.example.testtackunisafe.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testtackunisafe.R
import com.example.testtackunisafe.databinding.MainActivityBinding
import com.example.testtackunisafe.utils.APP_ACTIVITY

class MainActivity : AppCompatActivity() {
    lateinit var mToolbar: Toolbar
    lateinit var mNavConverter: NavController
    private var _binding: MainActivityBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mToolbar = mBinding.toolbar
        mNavConverter = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title = getString(R.string.name_app_custom)
        APP_ACTIVITY = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}