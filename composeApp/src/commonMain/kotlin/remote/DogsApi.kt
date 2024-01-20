package remote

import dto.Breed
import dto.BreedImages
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class DogsApi (
    private val baseUrl: String = "https://dog.ceo/api",
) {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { isLenient = true; ignoreUnknownKeys = true; explicitNulls = false })
        }
    }

    suspend fun fetchDogsList() = client.get("$baseUrl/breeds/list").body<Breed>()
    suspend fun fetchDogDetails(breed: String) = client.get("$baseUrl/breed/$breed/images/random").body<BreedImages>()
}