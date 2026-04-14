package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    var currentArtwork by remember { mutableIntStateOf(1) }

    // URLs de imágenes reales de internet
    val imageUrl = when (currentArtwork) {
        1 -> "https://m.media-amazon.com/images/I/910j2-AGEiL._AC_UF894,1000_QL80_.jpg"
        2 -> "https://www.infobae.com/resizer/v2/https%3A%2F%2Fs3.amazonaws.com%2Farc-wordpress-client-uploads%2Finfobae-wp%2Fwp-content%2Fuploads%2F2019%2F03%2F20204717%2Fmunch-grito-copia.jpeg?auth=40962e1cf543bed47a4e080ab370aeb29e595195cfcf4d3da875c0b31ee8c6d6&smart=true&width=1200&height=900&quality=85"
        else -> "https://cdn.shopify.com/s/files/1/0054/2887/1268/files/2_1_c43f7713-6e1c-40a8-9ce7-8c670df586f4.jpg?v=1726558719"
    }

    val title = when (currentArtwork) {
        1 -> "La Noche Estrellada"
        2 -> "El Grito"
        else -> "El Beso"
    }

    val artist = when (currentArtwork) {
        1 -> "Vincent van Gogh"
        2 -> "Edvard Munch"
        else -> "Gustav Klimt"
    }

    val year = when (currentArtwork) {
        1 -> "1889"
        2 -> "1893"
        else -> "1907"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. Muro con la obra de arte (Usando AsyncImage)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shadowElevation = 8.dp,
            color = Color.White
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.padding(20.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Descriptor
        Surface(
            modifier = Modifier.fillMaxWidth(0.9f),
            color = Color(0xFFECEFF1),
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Light)
                Row {
                    Text(text = artist, fontWeight = FontWeight.Bold)
                    Text(text = " ($year)", color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { if (currentArtwork > 1) currentArtwork-- else currentArtwork = 3 }) {
                Text("Anterior")
            }
            Button(onClick = { if (currentArtwork < 3) currentArtwork++ else currentArtwork = 1 }) {
                Text("Siguiente")
            }
        }
    }
}
