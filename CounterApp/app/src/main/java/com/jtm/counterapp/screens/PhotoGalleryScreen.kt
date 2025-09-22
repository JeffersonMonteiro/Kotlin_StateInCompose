package com.jtm.counterapp.screens


import android.R.attr.title
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jtm.counterapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoGalleryScreen(modifier: Modifier = Modifier) {
    val photos = listOf(
        R.drawable.foto3,
        R.drawable.foto2,
        R.drawable.iron_man,
        R.drawable.iron_man_2,
        R.drawable.iron_man_3
    )

    var index = remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Photo Gallery")
                },
                actions = {
                    Text(
                        text = "Showing ${index.value + 1} of ${photos.size}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                modifier = Modifier.weight(5f)
            ) {
                Crossfade(
                    targetState = index.value,
                    animationSpec = tween(
                        durationMillis = 500),
                    label = "Photo"
                    ) {
                        Image(
                            painter = painterResource(id = photos[it]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
            }
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Row {
                    OutlinedButton(
                        enabled = index.value > 0,
                        onClick = {
                            index.value--
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Previous")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedButton(
                        enabled = index.value < photos.size - 1,
                        onClick = {
                            index.value++
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Next")
                    }
                }
            }

        }

    }


}


@Preview
@Composable
private fun PhotoGalleryScreenPreview() {
    PhotoGalleryScreen()
}