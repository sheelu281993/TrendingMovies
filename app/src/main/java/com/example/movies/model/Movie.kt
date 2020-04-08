package com.example.movies.model

import com.example.movies.utils.TimeUtils
import com.example.movies.utils.TimeUtils.DAY_DATE
import com.example.movies.utils.TimeUtils.TIME_FORMAT
import org.json.JSONException
import org.json.JSONObject

class Movie(jsonObject: JSONObject) {
    var created_on: String?= null
    var description: String?= null
    var id: Int?= null
    var name: String?= null
    var payment_plan: String?= null
    var posterLink: String?= null
    var release_year: Int?= null
    var shortDescription: String?= null
    var type: String?= null
    var updated_on: String?= null
    var video_duration: String?= null

    init{
        try {
            this.id = jsonObject.getInt("id")
            this.name = jsonObject.getString("name")
            this.payment_plan = jsonObject.getString("payment_plan")
            this.release_year = jsonObject.getInt("release_year")
            this.posterLink = jsonObject.getString("posterLink")
            this.type = jsonObject.getString("type")
            this.video_duration = jsonObject.getString("video_duration")
            this.created_on = TimeUtils.getTime(jsonObject.getString("created_on"), TIME_FORMAT, DAY_DATE)
            this.updated_on = TimeUtils.getTime(jsonObject.getString("updated_on"), TIME_FORMAT, DAY_DATE)
            this.shortDescription = jsonObject.getString("shortDescription")
            this.description = jsonObject.getString("description")
        }catch (e: JSONException){}
    }
}