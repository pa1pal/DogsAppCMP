package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breed(
    @SerialName("message")
    val message: List<String?> = emptyList(),
    @SerialName("status")
    val status: String = ""
)