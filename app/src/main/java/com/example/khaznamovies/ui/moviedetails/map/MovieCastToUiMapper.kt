package com.example.khaznamovies.ui.moviedetails.map

import com.example.khaznamovies.domain.models.dto.Cast
import com.example.khaznamovies.ui.moviedetails.state.MovieCaseUiModel
import com.example.khaznamovies.utils.Utils
import javax.inject.Inject

class MovieCastToUiMapper @Inject constructor() {

    fun map(castList: List<Cast>?): List<MovieCaseUiModel> {
        return castList?.map { cast ->
            MovieCaseUiModel(
                id = cast.id,
                icon = Utils.getImageUrl(cast.profilePath),
                name = cast.name,
                character = cast.character,
                order = cast.order
            )
        }.orEmpty().sortedBy { it.order }
    }
}