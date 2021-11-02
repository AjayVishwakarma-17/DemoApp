package com.example.demoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapp.model.Info
import com.example.demoapp.repo.MainDataRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoViewModel constructor(private val repository: MainDataRepo)  : ViewModel() {

    val infoList = MutableLiveData<List<Info>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUserInfo() {

        val response = repository.getAllInfos()
        response.enqueue(object : Callback<List<Info>> {

            override fun onResponse(call: Call<List<Info>>, response: Response<List<Info>>) {
                Log.e("response",response.toString())
                infoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Info>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}