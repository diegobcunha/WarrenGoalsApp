package com.diegocunha.warrengoalsapp.helper

import androidx.lifecycle.LiveData
import org.junit.Assert

fun <T> assertLiveDataEquals(expected: T?, liveData: LiveData<T>, doAfterSubscribe: (() -> Unit)? = null) {
    var value: T? = null
    liveData.observeForever { value = it }
    doAfterSubscribe?.invoke()
    Assert.assertEquals(expected, value)
}