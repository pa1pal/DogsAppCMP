package repo

import dto.Breed
import dto.BreedImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import remote.ApiResponse
import remote.DogsRemoteData
import kotlin.coroutines.CoroutineContext

class MainRepository(
    private val remoteData: DogsRemoteData,
    private val ioDispatcher: CoroutineContext
) : RepoSource {
    override suspend fun getAllDogsBreeds(): Flow<ApiResponse<Breed>> {
        return flow {
            try {
                emit(ApiResponse.Loading())
                val response = remoteData.getAllDogsBreed()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(
                    ApiResponse.Error(
                        e.message ?: "An unexpected error occured", Breed()
                    )
                )
            }

        }.flowOn(ioDispatcher)
    }

    override suspend fun getBreedImages(breed: String): Flow<ApiResponse<BreedImages>> {
        return flow {
            try {
                emit(ApiResponse.Loading())
                val response = remoteData.getDogBreedImages(breed)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(
                    ApiResponse.Error(
                        e.message ?: "An unexpected error occured", BreedImages()
                    )
                )
            }

        }.flowOn(ioDispatcher)
    }
}