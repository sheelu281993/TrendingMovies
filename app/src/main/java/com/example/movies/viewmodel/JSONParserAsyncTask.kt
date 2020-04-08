package com.example.movies.viewmodel

import android.os.AsyncTask
import com.example.movies.model.Movie
import org.json.JSONException
import org.json.JSONObject

class JSONParserAsyncTask(private val jsonParserCallbackResponse: JsonParserCallbackResponse) : AsyncTask<String, Void, List<Movie>>() {

        private fun jsonParserForMovieObject(json: String): List<Movie>{
            val listOfMovies = arrayListOf<Movie>()

            try {
                val jsonObject = JSONObject(json)
                val jsonDataList = jsonObject.getJSONArray("TestData")
                for(i in 0 until jsonDataList.length()){
                    val movie = Movie(jsonDataList[i] as JSONObject)
                    listOfMovies.add(movie)
                }

            }catch (e: JSONException){}

            return listOfMovies
        }

        override fun doInBackground(vararg params: String): List<Movie>? {
            val json = params[0]
            return jsonParserForMovieObject(json)
        }

        override fun onPostExecute(result: List<Movie>?) {
            super.onPostExecute(result)
            jsonParserCallbackResponse.run { callbackResponse(result) }
        }
}