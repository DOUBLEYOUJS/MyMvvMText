package com.js.mymvvmtext

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.js.mymvvmtext.databinding.ActivityMainBinding
import com.js.mymvvmtext.vm.MyViewModel
import com.js.mymvvmtext.vm.MyViewModelFactory
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    //添加DataBinding
    private lateinit var mBinding: ActivityMainBinding
    //private lateinit var mViewModel: MyViewModel;
    //通过工厂方式懒加载,只有调用mViewMode.xxx才会创建
    //需要引入activity-ktx包，且需要java 1.8
    //viewModels是ComponentActivity的一个扩展方法
    private val mViewModel: MyViewModel by viewModels {
        MyViewModelFactory()
    }
    private var mIsRunning = false

    private val mPath = arrayOf(R.drawable.girl, R.drawable.girl2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //一般使用这种方式
        //mViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mBinding.isShowImage = false
        mBinding.url = mPath[0]
        mBinding.btOpen.setOnClickListener {
            mBinding.isShowImage = true
            mIsRunning = true
            //异步更新数据，测试界面更新
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
