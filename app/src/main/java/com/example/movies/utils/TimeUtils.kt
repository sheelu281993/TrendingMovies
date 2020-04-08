package com.example.movies.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    var TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    var DAY_DATE = "dd MMM, yyyy"

    private fun getCurrentTimeZoneFormat(timeFormat: String): SimpleDateFormat {
        val sdf = SimpleDateFormat(timeFormat)
        val currentDate = Date()
        val tz = Calendar.getInstance().timeZone
        val name = TimeZone.getDefault().getDisplayName(tz.inDaylightTime(currentDate), TimeZone.SHORT)
        sdf.timeZone = TimeZone.getTimeZone("\"" + name + "\"")
        return sdf
    }

    fun getTime(time: String?, inputTimeFormat: String, outputTimeFormat: String): String? {
        val sdf: SimpleDateFormat = getCurrentTimeZoneFormat(inputTimeFormat)
        val sdfo = SimpleDateFormat(outputTimeFormat)
        val calendar = Calendar.getInstance()
        try { calendar.time = sdf.parse(time) } catch (e: java.lang.Exception) { e.printStackTrace() }
        return sdfo.format(calendar.time)
    }


}