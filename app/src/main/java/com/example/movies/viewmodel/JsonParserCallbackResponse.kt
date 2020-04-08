package com.example.movies.viewmodel

import com.example.movies.model.Movie

interface JsonParserCallbackResponse{
        fun callbackResponse(result: List<Movie>?)
    }
