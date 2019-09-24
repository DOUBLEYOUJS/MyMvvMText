package com.js.mymvvmtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.js.mymvvmtext.databinding.ActivityMainBinding
import com.js.mymvvmtext.vm.MyViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MyViewModel
    private var mIsRunning = false

    private val mPath = arrayOf(R.drawable.girl, R.drawable.girl2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mBinding.isShowImage = false
        mBinding.url = mPath[1]
        mBinding.btOpen.setOnClickListener {
            mBinding.isShowImage = true
            mIsRunning = true
            GlobalScope.launch {
                while (mIsRunning) {
                    delay(2000)
                    withContext(Dispatchers.Main) {
                        if (mViewModel.mChangeCount.value == 1) {
                            mViewModel.mChangeCount.value = 0
                        } else {
                            mViewModel.mChangeCount.value = 1
                        }
                    }
                }
            }
        }

        mViewModel.mChangeCount.observe(this@MainActivity, Observer<Int> {
            mBinding.url = mPath[it]
        })


    }

    override fun onStop() {
        mIsRunning = false
        super.onStop()
    }
}
