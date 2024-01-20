package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedImages(
    @SerialName("message")
    val imageUrl: String = "",
    @SerialName("status")
    val status: String = ""
)