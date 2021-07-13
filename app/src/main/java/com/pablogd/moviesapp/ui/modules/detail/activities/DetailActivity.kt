package com.pablogd.moviesapp.ui.modules.detail.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.databinding.ActivityDetailBinding
import com.pablogd.moviesapp.ui.base.BaseActivity
import com.pablogd.moviesapp.ui.modules.detail.fragments.MovieDetailFragment

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {

        const val DETAIL_MODEL = "detailModel"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        fillDetailInfo()
    }

    private fun initViews() = with(binding) {
        //Empty method
    }

    private fun fillDetailInfo() {
        val detail = getDetail()
        binding.ivPoster.apply {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w780${detail.backdropPath}")
                .placeholder(R.drawable.ic_placeholder)
                .into(this)
        }
        binding.toolbar.title = detail.title

        val bundle = Bundle()
        bundle.putSerializable(DETAIL_MODEL, detail)
        loadFragmentDetail(MovieDetailFragment.newInstance(bundle))
    }

    private fun getDetail() = Detail(
        1, "La guerra del mañana",
        "Unos viajeros llegan desde el año 2051 para dar un mensaje: 30 años en el futuro, la humanidad estará perdiendo la guerra contra unos alienígenas. La única esperanza para sobrevivir es que los soldados y civiles vayan al futuro y se sumen a la batalla. Decidido a salvar el mundo por su hija, Dan Forester se une a una científica brillante y su distante padre para reescribir el destino del planeta.",
        "/yizL4cEKsVvl17Wc1mGEIrQtM2F.jpg", "The Tomorrow War", "English", "10-06-2021", 8.3, 200.0
    )

    private fun loadFragmentDetail(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

}