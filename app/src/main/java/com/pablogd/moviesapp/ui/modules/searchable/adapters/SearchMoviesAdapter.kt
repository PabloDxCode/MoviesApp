package com.pablogd.moviesapp.ui.modules.searchable.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pablogd.domain.models.Movie
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ItemSearchableBinding
import com.pablogd.moviesapp.ui.utils.inflate

class SearchMoviesAdapter(
    private val items: MutableList<Movie>,
    private val listener: (Movie, View) -> Unit
) : RecyclerView.Adapter<SearchMoviesAdapter.SearchMoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesHolder {
        val view = parent.inflate(R.layout.item_searchable, false)
        return SearchMoviesHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchMoviesHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SearchMoviesHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemSearchableBinding.bind(view)

        fun bind(movie: Movie) = with(binding) {
            ivPoster.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${movie.posterPath}")
                    .placeholder(R.drawable.ic_placeholder)
                    .into(this)
            }
            tvTitle.text = movie.title
            tvDetail.text = movie.overview
            llContainer.setOnClickListener { listener.invoke(movie, ivPoster) }
        }

    }

}