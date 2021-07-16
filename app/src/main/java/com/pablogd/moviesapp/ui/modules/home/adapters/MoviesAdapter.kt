package com.pablogd.moviesapp.ui.modules.home.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pablogd.domain.models.Movie
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ItemMovieBinding
import com.pablogd.moviesapp.ui.utils.addAnimation
import com.pablogd.moviesapp.ui.utils.basicDiffUtil
import com.pablogd.moviesapp.ui.utils.inflate

class MoviesAdapter(
    private val listener: (Movie, View) -> Unit
): RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    var items: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = parent.inflate(R.layout.item_movie, false)
        return MoviesHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewAttachedToWindow(holder: MoviesHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.addAnimation()
    }

    override fun onViewDetachedFromWindow(holder: MoviesHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    inner class MoviesHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemMovieBinding.bind(view)

        fun bind(movie: Movie) = with(binding) {
            ivPoster.apply {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${movie.posterPath}")
                    .placeholder(R.drawable.ic_placeholder)
                    .into(this)
            }
            tvTitle.text = movie.title
            cvMovie.setOnClickListener { listener.invoke(movie, ivPoster) }
        }

    }

}