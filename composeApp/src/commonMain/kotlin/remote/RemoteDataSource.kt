package remote

import dto.Breed
import dto.BreedImages

internal interface RemoteDataSource {
    suspend fun getAllDogsBreed(): Breed
    suspend fun getDogBreedImages(breed: String): BreedImages
}