package remote

import dto.Breed
import dto.BreedImages

class DogsRemoteData (
    private val apiService: DogsApi
): RemoteDataSource {
    override suspend fun getAllDogsBreed(): Breed {
        return apiService.fetchDogsList()
    }

    override suspend fun getDogBreedImages(breed: String): BreedImages {
        return apiService.fetchDogDetails(breed)
    }
}