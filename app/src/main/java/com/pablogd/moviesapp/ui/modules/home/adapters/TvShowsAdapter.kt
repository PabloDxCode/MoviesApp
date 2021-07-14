package com.pablogd.moviesapp.ui.modules.home.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pablogd.domain.models.TvShow
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ItemMovieBinding
import com.pablogd.moviesapp.ui.utils.addAnimation
import com.pablogd.moviesapp.ui.utils.basicDiffUtil
import com.pablogd.moviesapp.ui.utils.inflate

class TvShowsAdapter(
    private val listener: (TvShow, View) -> Unit
): RecyclerView.Adapter<TvShowsAdapter.TvShowsHolder>() {

    var items: List<TvShow> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsHolder {
        val view = parent.inflate(R.layout.item_movie, false)
        return TvShowsHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TvShowsHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewAttachedToWindow(holder: TvShowsHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.addAnimation()
    }

    override fun onViewDetachedFromWindow(holder: TvShowsHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    inner class TvShowsHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemMovieBinding.bind(view)

        fun bind(tvShow: TvShow) = with(binding) {
            ivPoster.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${tvShow.posterPath}")
                    .placeholder(R.drawable.ic_placeholder)
                    .into(this)
            }
            tvTitle.text = tvShow.overview
            cvMovie.setOnClickListener { listener.invoke(tvShow, ivPoster) }
        }

    }

}