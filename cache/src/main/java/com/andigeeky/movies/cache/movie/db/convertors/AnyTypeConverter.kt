package com.andigeeky.movies.cache.movie.db.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AnyTypeConverter {

    @TypeConverter
    fun fromString(value: String): Any? {
        val type = object: TypeToken<Any>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromArrayList(list: Any?): String {
        val type = object: TypeToken<Any>() {}.type
        return Gson().toJson(list, type)
    }
}
