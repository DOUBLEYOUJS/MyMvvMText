package com.js.mymvvmtext.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author: double
 * @date: 19-9-24
 * @description:
 */

class MyViewModel : ViewModel() {
    val mChangeCount = MutableLiveData<Int>()
}