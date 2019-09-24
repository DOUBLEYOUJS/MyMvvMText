package com.js.mymvvmtext.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author: double
 * @date: 19-9-24
 * @description:
 */

class MyViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel() as T
    }
}