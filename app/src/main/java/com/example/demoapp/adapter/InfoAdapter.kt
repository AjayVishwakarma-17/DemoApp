package com.example.demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.model.Info


class InfoAdapter(val infos: List<Info>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = infos[position]
        holder.textView.text = info.id
        val webViewSettings = holder.webView.settings
        webViewSettings.javaScriptEnabled = true
        webViewSettings.builtInZoomControls = true
        holder.webView.loadUrl(info.github_profile.toString())
        //Glide.with(holder.itemView.context).load(info.github_profile).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return infos.size
    }
}

class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    val webView: WebView = itemView.findViewById(R.id.webview)
    var textView: TextView = itemView.findViewById(R.id.name)
}