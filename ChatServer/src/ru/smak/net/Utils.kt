package ru.smak.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Utils {
    private val gson = Gson()

    fun getMapFromJson(json: String) : Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
}