package com.example.movies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.ui.BitmapWorkerTask

class MovieListAdapter(private val movies: List<Movie>, private val mcontext: Context) : ArrayAdapter<Movie?>(mcontext, R.layout.item_movie, movies){

    // View lookup cache
    private class ViewHolder {
        var tvMovieName: TextView? = null
        var ivMoviePoster: ImageView?= null
        var tvMovieReleaseYear: TextView? = null
        var tvMoviePlan: TextView? = null
        var tvMovieType: TextView? = null
        var tvMovieDuration: TextView? = null
        var tvMovieCreatedOn: TextView?= null
        var tvMovieUpdatedOn: TextView? = null
        var tvMovieShortDescription: TextView?= null
        var tvMovieDescription: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val movie: Movie? = movies[position]

        val viewHolder: ViewHolder // view lookup cache stored in tag

        val view: View

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_movie, parent, false)
            viewHolder.tvMovieName = view.findViewById(R.id.tvMovieName)
            viewHolder.ivMoviePoster = view.findViewById(R.id.ivImage)
            viewHolder.tvMoviePlan = view.findViewById(R.id.tvPlan)
            viewHolder.tvMovieReleaseYear = view.findViewById(R.id.tvReleasePlan)
            viewHolder.tvMovieDuration = view.findViewById(R.id.tvMovieDuration)
            viewHolder.tvMovieType = view.findViewById(R.id.tvMovieType)
            viewHolder.tvMovieCreatedOn = view.findViewById(R.id.tvCreatedOn)
            viewHolder.tvMovieUpdatedOn = view.findViewById(R.id.tvUpdatedOn)
            viewHolder.tvMovieShortDescription = view.findViewById(R.id.tvMovieShortDescription)
            viewHolder.tvMovieDescription = view.findViewById(R.id.tvMovieDescription)

            view.tag = viewHolder
        }
        else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.run {
            tvMovieName?.text = movie?.name

            //imageview
            ivMoviePoster?.let {posterImageView -> movie?.posterLink?.let { BitmapWorkerTask(mcontext, posterImageView).execute(it) } }

            val na = mcontext.getString(R.string.na)
            //plan
            if(!movie?.payment_plan.isNullOrEmpty()) tvMoviePlan?.text = mcontext.getString(R.string.plan, movie?.payment_plan)
            else  tvMoviePlan?.text = mcontext.getString(R.string.plan, na)

            //release year
            tvMovieReleaseYear?.text = mcontext.getString(R.string.release_year, movie?.release_year)

            //video duration
            if(!movie?.video_duration.isNullOrEmpty()) tvMovieDuration?.text = mcontext.getString(R.string.movie_duration, movie?.video_duration)

            //movie type
           if(!movie?.type.isNullOrEmpty()) tvMovieType?.text = mcontext.getString(R.string.movie_type, movie?.type)

            //created on
            if(!movie?.created_on.isNullOrEmpty()) tvMovieCreatedOn?.text = mcontext.getString(R.string.created_on, movie?.created_on)
            else tvMovieCreatedOn?.text = mcontext.getString(R.string.created_on, na)

            //updated on
           if(!movie?.updated_on.isNullOrEmpty()) tvMovieUpdatedOn?.text = mcontext.getString(R.string.updated_on, movie?.updated_on)
           else tvMovieUpdatedOn?.text = mcontext.getString(R.string.updated_on, na)

            //short description
            tvMovieShortDescription?.text = movie?.shortDescription
            //description
            tvMovieDescription?.text = movie?.description
        }

        // Return the completed view to render on screen
        return view
    }

}
