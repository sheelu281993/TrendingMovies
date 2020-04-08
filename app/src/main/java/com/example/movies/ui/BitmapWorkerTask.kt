package com.example.movies.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.lang.ref.WeakReference

class BitmapWorkerTask(mContext: Context, imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

    private val imageViewReference: WeakReference<ImageView>?
    private val context: WeakReference<Context>?


    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        synchronized(MyCacheBitmap.lruCache){
            if(MyCacheBitmap.lruCache.get(fileName) != null){ return MyCacheBitmap.lruCache.get(fileName) }
        }

        val fileNameNew = fileName.split(".")[0] + ".jpg"
        val istr = context?.get()?.assets?.open(fileNameNew)
        return istr?.let {
             val bitmap =  BitmapFactory.decodeStream(istr)
             //compress bitmap size
            val compressBitmap =  Bitmap.createScaledBitmap(bitmap, 160, 160, true)
            synchronized(MyCacheBitmap.lruCache){ MyCacheBitmap.lruCache.put(fileName, compressBitmap) }
            compressBitmap
        }
    }

    // Decode image in background.
    override fun doInBackground(vararg params: String): Bitmap? {
        return getBitmapFromAssets(params[0])
    }

    // Once complete, see if ImageView is still around and set bitmap.
    override fun onPostExecute(bitmap: Bitmap?) {
        if (imageViewReference != null && bitmap != null) {
            val imageView = imageViewReference.get()
            imageView?.setImageBitmap(bitmap)
        }
    }

    init {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = WeakReference(imageView)
        context  = WeakReference(mContext)
    }

}