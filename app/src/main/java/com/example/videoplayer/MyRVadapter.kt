package com.example.videoplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRVadapter(val dataset : List<VideoObject>) : RecyclerView.Adapter<MyRVadapter.MyVH>() {

    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.videoTitle)
        val imageView : ImageView = itemView.findViewById(R.id.videoImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item , parent , false)
        return MyVH(view)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.textView.text = dataset[position].title
        holder.imageView.setImageBitmap(dataset[position].bitmap)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}