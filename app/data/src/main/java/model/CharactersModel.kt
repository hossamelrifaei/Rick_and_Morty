package model

data class CharactersModel(val info: InfoModel, val characters: List<Character>)

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
)

data class LocationModel(val name: String?, val url: String?)

data class OriginModel(val name: String?, val url: String?)

data class InfoModel(
    val count: Int,
    val pages: Int,
)