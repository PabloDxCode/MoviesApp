package com.pablogd.moviesapp.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.pablogd.domain.models.Detail
import com.pablogd.moviesapp.R

class DetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setDetail(movie: Detail) = with(movie) {
        text = buildSpannedString {
            originalTitle?.let {
                bold { append(context.getString(R.string.detail_original_title)) }
                appendLine(it)
            }

            bold { append(context.getString(R.string.detail_original_language)) }
            appendLine(originalLanguage)

            releaseDate?.let {
                bold { append(context.getString(R.string.detail_release_date)) }
                appendLine(it)
            }

            bold { append(context.getString(R.string.detail_popularity)) }
            appendLine(popularity.toString())

            bold { append(context.getString(R.string.detail_vote_average)) }
            append(voteAverage.toString())
        }
    }

}