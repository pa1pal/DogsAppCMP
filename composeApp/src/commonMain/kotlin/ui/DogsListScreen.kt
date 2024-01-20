package ui

import DogsViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dto.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import remote.ApiResponse
import remote.DogsApi
import remote.DogsRemoteData
import repo.MainRepository

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val apiService = DogsApi()
        val remoteData = DogsRemoteData(apiService)
        val mainRepository = MainRepository(remoteData, Dispatchers.IO)
        val viewModel: DogsViewModel = remember { DogsViewModel(mainRepository) }
        val dogsList by viewModel.dogsList.collectAsState()
        DogsList(dogsList = dogsList) {
            if (navigator.level == 0) {
                navigator.push(DogsDetailScreen(it))
            }
        }
    }

    data class DogsDetailScreen(val dogBreed: String) : Screen {
        @Composable
        override fun Content() {
            val apiService = DogsApi()
            val remoteData = DogsRemoteData(apiService)
            val mainRepository = MainRepository(remoteData, Dispatchers.IO)
            val viewModel: DogsViewModel = remember { DogsViewModel(mainRepository) }
            val dogImageUrl by viewModel.dogsImageUrl.collectAsState()

            LaunchedEffect(dogBreed) {
                viewModel.fetchBreedDetails(dogBreed)
            }

            DogInfo(breedImageUrl = dogImageUrl, breedName = dogBreed)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsList(
    dogsList: ApiResponse<Breed>,
    showDogsDetails: (dogBreed: String) -> Unit
) {
    var loading by remember { mutableStateOf(false) }
    Column {
        CenterAlignedTopAppBar(
            title = { Text(text = "DogsApp CMP") }
        )

        when (dogsList) {
            is ApiResponse.Error -> {}
            is ApiResponse.Loading -> {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }

            is ApiResponse.Success -> {
                loading = false
                val list = mutableListOf<String>()
                dogsList.data?.message?.filterNotNull()?.let { list.addAll(it) }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 2.dp, vertical = 8.dp)
                ) {
                    items(list) {
                        DogsListItem(it) { dogBreed ->
                            showDogsDetails.invoke(dogBreed)
                        }
                    }
                }
            }
        }
    }
}