package com.example.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movies.model.Movie
import java.io.IOException
import java.io.InputStream

class MovieListViewModel(application: Application) : AndroidViewModel(application), JsonParserCallbackResponse {

    private var mMovieLiveData: MutableLiveData<List<Movie>>? = null

    fun getMovies(): MutableLiveData<List<Movie>>? {
        mMovieLiveData = MutableLiveData()
        val json = loadMoviesJSONFromAsset()
        if(json!= null && json.isNotEmpty()) {
            val task = JSONParserAsyncTask(this)
            task.execute(json)
            return mMovieLiveData
        }
        return null
    }

    //read json as string format
    private fun loadMoviesJSONFromAsset(): String? {
        return try {
            val inputStream: InputStream = getApplication<Application>().applicationContext.assets.open("test_json.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun callbackResponse(result: List<Movie>?)  {
          result?.run { mMovieLiveData?.value = this }
    }


}