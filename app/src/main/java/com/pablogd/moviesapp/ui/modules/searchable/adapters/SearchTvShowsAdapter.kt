package com.pablogd.moviesapp.ui.modules.searchable.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pablogd.domain.models.TvShow
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ItemSearchableBinding
import com.pablogd.moviesapp.ui.utils.inflate

class SearchTvShowsAdapter(
    private val items: MutableList<TvShow>,
    private val listener: (TvShow, View) -> Unit
) : RecyclerView.Adapter<SearchTvShowsAdapter.SearchTvShowsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTvShowsHolder {
        val view = parent.inflate(R.layout.item_searchable, false)
        return SearchTvShowsHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchTvShowsHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SearchTvShowsHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemSearchableBinding.bind(view)

        fun bind(tvShow: TvShow) = with(binding) {
            ivPoster.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${tvShow.posterPath}")
                    .placeholder(R.drawable.ic_placeholder)
                    .into(this)
            }
            tvTitle.text = tvShow.name
            tvDetail.text = tvShow.overview
            llContainer.setOnClickListener { listener.invoke(tvShow, ivPoster) }
        }

    }

}