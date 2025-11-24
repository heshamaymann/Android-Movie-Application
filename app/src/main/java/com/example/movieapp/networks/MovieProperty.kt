package com.example.movieapp.networks

import com.example.movieapp.features.fragment.ItemCharacter
import com.squareup.moshi.Json


data class CharResponse(
    @Json(name = "results") val list: List<ItemCharacter>
)

data class LocResponse(
    @Json(name = "name") val name: String,
    @Json(name  = "id")val id: Int,
    val type: String

)
