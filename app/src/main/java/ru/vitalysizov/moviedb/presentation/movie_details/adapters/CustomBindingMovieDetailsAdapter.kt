package ru.vitalysizov.moviedb.presentation.movie_details.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vitalysizov.moviedb.R
import ru.vitalysizov.moviedb.model.domain.GenreItem
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CastItem
import ru.vitalysizov.moviedb.model.domain.castAndCrew.CrewItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieDetailsItem
import ru.vitalysizov.moviedb.model.domain.movies.MovieImageItem
import ru.vitalysizov.moviedb.model.domain.production.ProductionCountriesItem
import ru.vitalysizov.moviedb.presentation.movie_details.adapters.backDrop.BackDropAdapter
import ru.vitalysizov.moviedb.presentation.movie_details.adapters.castAndCrew.CastAndCrewAdapter
import ru.vitalysizov.moviedb.presentation.movie_details.adapters.posterAndDescription.MovieGenresAdapter
import ru.vitalysizov.moviedb.utils.gone
import ru.vitalysizov.moviedb.utils.loadCastPhoto

const val DEPARTMENT_DIRECTING = "Directing"
const val DEPARTMENT_WRITING = "Writing"
const val JOB_DIRECTOR = "Director"

@BindingAdapter(value = ["loadCastPhoto"])
fun loadCastPhoto(imageView: ImageView, url: String) {
    imageView.loadCastPhoto(url)
}

@BindingAdapter(value = ["setBackDrop"])
fun RecyclerView.setRowBackDropImages(items: List<MovieImageItem>) {
    val backDropAdapter = BackDropAdapter()
    backDropAdapter.submitList(items)
    adapter = backDropAdapter
}

@BindingAdapter(value = ["setOriginalTitleAndYear"])
fun TextView.setOriginalTitleAndYear(movieDetails: MovieDetailsItem) {
    this.text = resources.getString(
        R.string.title_and_year_mask, movieDetails.originalTitle,
        movieDetails.releaseDate.year.toString()
    )
}

@BindingAdapter(value = ["setProductionCountries"])
fun TextView.setProductionCountries(productionCountries: List<ProductionCountriesItem>) {
    this.text = productionCountries.joinToString { it.name }
}

@BindingAdapter(value = ["setRunTime"])
fun TextView.setRunTime(movieDetails: MovieDetailsItem) {
    this.text = resources.getString(
        R.string.hours_and_minutes_mask,
        movieDetails.runTime.hour,
        movieDetails.runTime.minute
    )
}

@BindingAdapter(value = ["setTagLine"])
fun TextView.setTagLine(tagLine: String) {
    if (tagLine.isEmpty()) {
        this.gone()
    } else {
        this.text = tagLine
    }
}

@BindingAdapter(value = ["setMovieGenres"])
fun RecyclerView.setRowMovieGenres(items: List<GenreItem>) {
    val movieGenreAdapter = MovieGenresAdapter()
    movieGenreAdapter.submitList(items)
    adapter = movieGenreAdapter
}

@BindingAdapter(value = ["setCast"])
fun RecyclerView.setRowCast(items: List<CastItem>) {
    val castAdapter = CastAndCrewAdapter()
    castAdapter.submitList(items)
    adapter = castAdapter
}

@BindingAdapter(value = ["setDirectors"])
fun TextView.setDirectors(items: List<CrewItem>) {
    val directors =
        items.filter { it.department.contains(DEPARTMENT_DIRECTING).and(it.job == JOB_DIRECTOR) }
    val directorsNames = directors.map { it.name }.joinToString { it }
    this.text = directorsNames
}

@BindingAdapter(value = ["setWrites"])
fun TextView.setWrites(items: List<CrewItem>) {
    val writers = items.filter { it.department.contains(DEPARTMENT_WRITING) }
    val writersNames = writers.map { it.name }.joinToString { it }
    this.text = writersNames
}

@BindingAdapter(value = ["setActorNameAndCharacter"])
fun TextView.setActorNameAndCharacter(item: CastItem) {
    val castName = item.name
    val castCharacter = item.character

    this.text = SpannableString(resources.getString(R.string.cast_mask, castName, castCharacter))
        .apply {
            setSpan(
                ForegroundColorSpan(Color.BLACK),
                0,
                castName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                castName.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

}