package com.example.testtackunisafe.presentation.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.testtackunisafe.R
import com.example.testtackunisafe.domain.RetrofitClient
import com.example.testtackunisafe.databinding.FragmentEntranceBinding
import com.example.testtackunisafe.domain.`interface`.MainApi
import com.example.testtackunisafe.data.utils.APP_ACTIVITY
import com.example.testtackunisafe.data.utils.KEY_VALUE
import com.example.testtackunisafe.presentation.viewmodel.DownloadList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EntranceFragment : Fragment() {
    private var _binding: FragmentEntranceBinding? = null
    private val mBinding get() = _binding!!
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntranceBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Shopping List"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController = findNavController()
        val mainApi = RetrofitClient.mainApi

        _binding!!.downloadListBtn.setOnClickListener {
            val inputKey = _binding?.editTextKey?.text.toString()
            KEY_VALUE = inputKey
            navController.navigate(R.id.action_entrance_to_createListsProducts)//bundle

        }
        _binding!!.startBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val keyValue = createTestKey(mainApi)
                Log.i("Key value", "" + keyValue)
                KEY_VALUE = keyValue
                val successfulLogin: Boolean = authentication(mainApi, keyValue)
                Log.i("SuccessfulLogin", "Successful login: " + successfulLogin)
                APP_ACTIVITY.runOnUiThread {
                    navController.navigate(R.id.action_entrance_to_createListsProducts)
                }
            }
        }
        return mBinding.root
    }

    private suspend fun authentication(mainApi: MainApi, keyValue: String): Boolean {
        val success = mainApi.authentication(keyValue = keyValue)
        return success.success
    }

    private suspend fun createTestKey(mainApi: MainApi): String {
        val key = mainApi.createTestKey()
        return if (key.isSuccessful) {
            val keyData = key.body().toString()
            Log.i("String value", (keyData ?: "key").toString())
            return keyData
        } else {
            "missing key"
        }
    }
}

