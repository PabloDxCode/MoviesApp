package com.pablogd.moviesapp.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.R
import com.pablogd.moviesapp.ui.utils.notNull

class DetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setDetail(movie: Detail) = with(movie) {
        text = buildSpannedString {
            if(originalTitle.notNull().isNotEmpty()){
                bold { append(context.getString(R.string.detail_original_title)) }
                appendLine(originalTitle)
            }

            bold { append(context.getString(R.string.detail_original_language)) }
            appendLine(originalLanguage)

            if(releaseDate.notNull().isNotEmpty()){
                bold { append(context.getString(R.string.detail_release_date)) }
                appendLine(releaseDate)
            }

            bold { append(context.getString(R.string.detail_popularity)) }
            appendLine(popularity.toString())

            bold { append(context.getString(R.string.detail_vote_average)) }
            append(voteAverage.toString())
        }
    }

}