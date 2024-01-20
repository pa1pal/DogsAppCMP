package repo

import dto.Breed
import dto.BreedImages
import kotlinx.coroutines.flow.Flow
import remote.ApiResponse

interface RepoSource {
    suspend fun getAllDogsBreeds(): Flow<ApiResponse<Breed>>

    suspend fun getBreedImages(breed: String): Flow<ApiResponse<BreedImages>>
}