package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.adapter.InfoAdapter
import com.example.demoapp.repo.MainDataRepo
import com.example.demoapp.repo.RetrofitService
import com.example.demoapp.viewmodel.InfoViewModel
import com.example.demoapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var viewModel: InfoViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, ViewModelFactory(MainDataRepo(retrofitService))).get(InfoViewModel::class.java)
        viewModel.infoList.observe(this, {
            Log.e(TAG, "Data: $it")
            val adapter = InfoAdapter(it)
            recyclerView.adapter = adapter
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        })
        viewModel.getAllUserInfo()
    }
}