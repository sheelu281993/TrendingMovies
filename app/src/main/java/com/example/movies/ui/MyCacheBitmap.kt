package com.example.movies.ui

import android.graphics.Bitmap
import androidx.collection.LruCache

object  MyCacheBitmap {
    private var cacheSize = 4 * 1024 * 1024 // 4MiB
    val lruCache = LruCache<String, Bitmap>(cacheSize)
}