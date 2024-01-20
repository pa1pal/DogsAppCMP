package ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dto.BreedImages
import io.kamel.core.utils.URI
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import io.ktor.util.logging.Logger
import kotlinx.coroutines.coroutineScope
import remote.ApiResponse
import remote.DogsApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogInfo(
    breedImageUrl: ApiResponse<BreedImages>,
    breedName: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val navigator = LocalNavigator.currentOrThrow
        CenterAlignedTopAppBar(
            title = { Text(text = "DogsApp CMP") },
            navigationIcon = {
                IconButton(onClick = { navigator.pop()}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back press"
                    )
                }
            },
        )

        when (breedImageUrl) {
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
                Text(
                    modifier = Modifier
                        .padding(12.dp)
                        .wrapContentHeight(),
                    text = breedName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )

                KamelImage(
                    resource = asyncPainterResource(breedImageUrl.data?.imageUrl ?: ""),
                    contentDescription = "Dog Image",
                    contentScale = ContentScale.Fit,
                    animationSpec = tween(),
                    onLoading = { progress -> CircularProgressIndicator(progress) },
                    onFailure = { exception ->
                        exception.printStackTrace()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                )
            }
        }
    }
}