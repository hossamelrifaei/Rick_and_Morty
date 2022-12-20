package model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersModel(val info: InfoModel, val characters: List<Character>) : Parcelable

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginModel?,
    val location: LocationModel?,
    val image: String,
    val episode: ArrayList<String>,
    val url: String,
    val created: String
) : Parcelable

@Parcelize
data class LocationModel(val name: String?, val url: String?) : Parcelable

@Parcelize
data class OriginModel(val name: String?, val url: String?) : Parcelable

@Parcelize
data class InfoModel(
    val count: Int,
    val pages: Int,
) : Parcelable