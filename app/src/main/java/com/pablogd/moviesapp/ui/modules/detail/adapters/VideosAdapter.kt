package com.pablogd.moviesapp.ui.modules.detail.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pablogd.domain.models.Video
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ItemVideoBinding
import com.pablogd.moviesapp.ui.utils.basicDiffUtil
import com.pablogd.moviesapp.ui.utils.inflate

class VideosAdapter(
    private val listener: (Video) -> Unit
): RecyclerView.Adapter<VideosAdapter.VideoHolder>() {

    var items: List<Video> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view = parent.inflate(R.layout.item_video, false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class VideoHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemVideoBinding.bind(view)

        fun bind(video: Video, position: Int) = with(binding) {
            llVideo.apply {
                if (position.rem(2) == 0) {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray))
                } else {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                }
            }
            tvName.text = video.name
            btSee.setOnClickListener {
                listener.invoke(video)
            }
        }

    }

}