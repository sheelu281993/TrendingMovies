package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.movies.R
import com.example.movies.adapters.MovieListAdapter
import com.example.movies.model.Movie
import com.example.movies.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {

    lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents(){
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        viewModel.getMovies()?.observe(this, Observer {
            updateUI(it)
        })
    }

    //update movies list
    private fun updateUI(movies: List<Movie>){
         listViewMovies.adapter = MovieListAdapter(movies, this)
    }
}
