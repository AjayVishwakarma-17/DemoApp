package com.example.demoapp.repo

class MainDataRepo constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllInfos() = retrofitService.getAllData()

}