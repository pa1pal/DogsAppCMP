import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import dto.Breed
import dto.BreedImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import remote.ApiResponse
import remote.DogsApi
import repo.MainRepository

open class DogsViewModel(private val mainRepository: MainRepository) : KMMViewModel() {
    private val dogsApi = DogsApi()

    private val _dogsList =
        MutableStateFlow<ApiResponse<Breed>>(ApiResponse.Loading(Breed()))
    val dogsList = _dogsList.asStateFlow()

    private val _dogsImageUrl = MutableStateFlow<ApiResponse<BreedImages>>(
        ApiResponse.Loading(
            BreedImages()
        )
    )
    val dogsImageUrl = _dogsImageUrl.asStateFlow()

    init {
        fetchDogsList()
    }

    private fun fetchDogsList() {
        viewModelScope.coroutineScope.launch {
            mainRepository.getAllDogsBreeds().collect { res ->
                _dogsList.update { res }
            }
        }
    }

    fun fetchBreedDetails(breedName: String) {
        viewModelScope.coroutineScope.launch {
            mainRepository.getBreedImages(breedName).collect { apiResponse ->
                _dogsImageUrl.update { apiResponse }
            }
        }
    }
}