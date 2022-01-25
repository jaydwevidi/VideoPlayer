package com.example.videoplayer

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyRVadapter(val dataset : MutableList<VideoObject> ,val  context: Context) : RecyclerView.Adapter<MyRVadapter.MyVH>() {

    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.videoTitle)
        val imageView : ImageView = itemView.findViewById(R.id.videoImageView)
        val cardView : CardView = itemView.findViewById(R.id.videoCardView)
    }

    fun newData(dataset: List<VideoObject>){
        this.dataset.addAll(dataset)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item , parent , false)
        return MyVH(view)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.textView.text = dataset[position].title
        //val thum = ThumbnailUtils.createVideoThumbnail(dataset[position].path , MediaStore.Images.Thumbnails.FULL_SCREEN_KIND )
        //holder.imageView.setImageBitmap(thum)

        Glide.with(context)
            .load(dataset[position].path)
            .centerCrop()
            .into(holder.imageView)

        holder.cardView.setOnClickListener {

            val intent = Intent(context , PlayerActivity::class.java)
            intent.putExtra("myPath" , dataset[position].path)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}